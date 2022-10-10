package com.ruppyrup.episode7.petsorearch.interactors;


import com.ruppyrup.episode7.petsorearch.boundaries.PetShopOwnerRequest;
import com.ruppyrup.episode7.petsorearch.entities.Dog;
import com.ruppyrup.episode7.petsorearch.entities.Pet;
import com.ruppyrup.episode7.petsorearch.entities.PetFactory;
import com.ruppyrup.episode7.petsorearch.entities.Snake;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GoodPetStore implements PetShopOwnerRequest {

  private final PetFactory petFactory;

  private final List<Pet> petsInStock = new ArrayList<>();

  /**
   * Pet factory is injected for ease of testing and can operate with multiple petFactories
   *
   * @param petFactory
   */
  public GoodPetStore(final PetFactory petFactory) {
    this.petFactory = petFactory;
  }

  // use-case Petshop owner stocks a pet
  @Override
  public void stockAPet(String petType, String petName) {
    Pet newPet = petFactory.orderPet(petType, petName);
    petsInStock.add(newPet);
  }

  // use-case owners lists the pets in stock
  @Override
  public List<Pet> getPetsInStock() {
    return List.copyOf(petsInStock);
  }

  // use-case owner sells pet if pet is available
  @Override
  public Optional<Pet> sellPet(String petType) {
    Optional<Pet> optionalPet = petsInStock.stream()
                                    .filter(pet -> pet.isPetType(petType))
                                    .findFirst();

    optionalPet.ifPresent(petsInStock::remove);

    return optionalPet;
  }

  // use-case get rid of pets
  @Override
  public void sendPetsToTheFarm() {
    petsInStock.clear();
  }

  // use-case exercise pets
  @Override
  public void exercisePets() {
    petsInStock.stream()
        .filter(Pet::doesPetNeedExercisingToday)
        .forEach(Pet::exercise);
  }
}

/**
 * This is a unit test, to test the public api of the GoodPetStore
 * Internal implementation is NOT tested explicitly
 * There is no mocking
 * The Pet classes and Factory don't have individual "unit" tests
 * The code coverage is 98%
 */
class TestGoodPetStore {

  private static final String SNAKE = "Snake";
  private static final String DOG = "Dog";
  private static final String FIDO = "Fido";
  private static final String SIMON = "Simon";
  private PetShopOwnerRequest petStore;
  private PetFactory petFactory;

  @BeforeEach
  void setup() {
    petFactory = new PetFactory();
    petStore = new GoodPetStore(petFactory);
  }

  @AfterEach
  void tidyUp() {
    petStore.sendPetsToTheFarm();
  }

  @Test
  void canCreatePetStore() {
    Assertions.assertNotNull(petStore);
  }

  @Test
  void canStockAPet() {
    petStore.stockAPet(DOG, FIDO);
    Pet pet = petStore.sellPet(DOG).orElseThrow();
    Assertions.assertTrue(pet instanceof Dog);
    Assertions.assertEquals(FIDO, pet.getPetName());
  }

  @Test
  void canStockTwoPets() {
    petStore.stockAPet(DOG, FIDO);
    petStore.stockAPet(SNAKE, SIMON);
    Pet dog = petStore.sellPet(DOG).orElseThrow();
    Pet snake = petStore.sellPet(SNAKE).orElseThrow();
    Assertions.assertTrue(dog instanceof Dog);
    Assertions.assertEquals(FIDO, dog.getPetName());
    Assertions.assertTrue(snake instanceof Snake);
    Assertions.assertEquals(SIMON, snake.getPetName());
  }

  @Test
  void canClearThePetShop() {
    petStore.stockAPet(DOG, FIDO);
    petStore.stockAPet(SNAKE, SIMON);
    petStore.sendPetsToTheFarm();
    Assertions.assertTrue(petStore.getPetsInStock().isEmpty());
  }

  @Test
  void buyingAPetRemovesItFromThePetShop() {
    petStore.stockAPet(DOG, FIDO);
    petStore.sellPet(DOG);
    Assertions.assertTrue(petStore.getPetsInStock().isEmpty());
  }

  @Test
  void canNotModifyPetShopStock() {
    petStore.stockAPet(DOG, FIDO);
    Assertions.assertThrows(UnsupportedOperationException.class,
        () -> petStore.getPetsInStock().add(petFactory.orderPet(SNAKE, "Pete")));
  }


  @Test
  void canExercisePets() {
    petStore.stockAPet(DOG, FIDO);
    petStore.stockAPet(SNAKE, SIMON);
    petStore.exercisePets();
    petStore.getPetsInStock().forEach(pet -> Assertions.assertFalse(pet.doesPetNeedExercisingToday()));
  }

  @Test
  void canCheckIfPetsHaveBeenExercisedToday() {
    petStore.stockAPet(DOG, FIDO);
    petStore.stockAPet(SNAKE, SIMON);
    petStore.getPetsInStock().forEach(pet -> pet.setExerciseDay(LocalDate.now().minusDays(2)));
    petStore.getPetsInStock().forEach(pet -> Assertions.assertTrue(pet.doesPetNeedExercisingToday()));
  }

}
