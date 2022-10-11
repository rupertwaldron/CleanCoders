package com.ruppyrup.episode7.petstorearch.interactors;


import com.ruppyrup.episode7.petstorearch.boundaries.PetShopOwnerRequest;
import com.ruppyrup.episode7.petstorearch.boundaries.OutputPort;
import com.ruppyrup.episode7.petstorearch.entities.Dog;
import com.ruppyrup.episode7.petstorearch.entities.Pet;
import com.ruppyrup.episode7.petstorearch.entities.PetFactory;
import com.ruppyrup.episode7.petstorearch.entities.Snake;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PetStoreInteractor implements PetShopOwnerRequest {

  private final OutputPort port;
  private final PetFactory petFactory;

  private final List<Pet> petsInStock = new ArrayList<>();

  /**
   * Pet factory is injected for ease of testing and can operate with multiple petFactories
   *
   * @param port
   * @param petFactory
   */
  public PetStoreInteractor(final OutputPort port, final PetFactory petFactory) {
    this.port = port;
    this.petFactory = petFactory;
  }

  // use-case Petshop owner stocks a pet
  @Override
  public void stockAPet(String petType, String petName) {
    Pet newPet = petFactory.orderPet(petType, petName);
    petsInStock.add(newPet);
    port.writeToOuput("New pet added to stock : " + newPet);
  }

  // use-case owners lists the pets in stock
  @Override
  public List<Pet> getPetsInStock() {
    StringBuilder sb = new StringBuilder();

    petsInStock.stream()
        .map(Object::toString)
        .forEach(sentence -> sb.append(sentence).append("\n"));

    port.writeToOuput(sb.toString());

    return List.copyOf(petsInStock);
  }

  // use-case owner sells pet if pet is available
  @Override
  public Optional<Pet> sellPet(String petType) {
    Optional<Pet> optionalPet = petsInStock.stream()
                                    .filter(pet -> pet.isPetType(petType))
                                    .findFirst();

    optionalPet.ifPresentOrElse(petsInStock::remove, () -> port.writeToOuput("You don't have any " + petType + " to sell."));

    return optionalPet;
  }

  // use-case get rid of pets
  @Override
  public void sendPetsToTheFarm() {
    port.writeToOuput("All pets sent to the \"Farm\"");
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
  private OutputPort port;

  @BeforeEach
  void setup() {
    port = new MockOutputPort();
    petFactory = new PetFactory();
    petStore = new PetStoreInteractor(port, petFactory);
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

  static class MockOutputPort implements OutputPort {
    String inputArg;
    @Override
    public void writeToOuput(final String output) {
      inputArg = output;
    }
  }

}

