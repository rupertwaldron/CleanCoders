package com.ruppyrup.episode1.goodcode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GoodPetStore {

    private final PetFactory petFactory;

    private final List<Pet> petsInStock = new ArrayList<>();

    public GoodPetStore(final PetFactory petFactory) {
        this.petFactory = petFactory;
    }

    public void stockAPet(String petType, String petName) {
        Pet newPet = petFactory.orderPet(petType, petName);
        petsInStock.add(newPet);
    }

    public List<Pet> getPetsInStock() {
        return List.copyOf(petsInStock);
    }

    public Optional<Pet> sellPet(String petType) {
        Optional<Pet> optionalPet = petsInStock.stream()
                .filter(pet -> pet.isPetType(petType))
                .findFirst();

        optionalPet.ifPresent(petsInStock::remove);

        return optionalPet;
    }

    public void sendPetsToTheFarm() {
        petsInStock.clear();
    }

    public void exercisePets() {
        petsInStock.stream()
                .filter(Exerciseable.class::isInstance)
                .map(Exerciseable.class::cast)
                .forEach(Exerciseable::exercise);
    }
}


class TestGoodPetStore {

    private GoodPetStore petStore;
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
        petStore.stockAPet("Dog", "Fido");
        Pet pet = petStore.sellPet("Dog").get();
        Assertions.assertTrue(pet instanceof Dog);
        Assertions.assertEquals("Fido", pet.getPetName());
    }

    @Test
    void canStockTwoPets() {
        petStore.stockAPet("Dog", "Fido");
        petStore.stockAPet("Snake", "Simon");
        Pet dog = petStore.sellPet("Dog").get();
        Pet snake = petStore.sellPet("Snake").get();
        Assertions.assertTrue(dog instanceof Dog);
        Assertions.assertEquals("Fido", dog.getPetName());
        Assertions.assertTrue(snake instanceof Snake);
        Assertions.assertEquals("Simon", snake.getPetName());
    }

    @Test
    void canClearThePetShop() {
        petStore.stockAPet("Dog", "Fido");
        petStore.stockAPet("Snake", "Simon");
        petStore.sendPetsToTheFarm();
        Assertions.assertTrue(petStore.getPetsInStock().isEmpty());
    }

    @Test
    void buyingAPetRemovesItFromThePetShop() {
        petStore.stockAPet("Dog", "Fido");
        petStore.sellPet("Dog").get();
        Assertions.assertTrue(petStore.getPetsInStock().isEmpty());
    }


    @Test
    void canExercisePets() {
        petStore.stockAPet("Dog", "Fido");
        petStore.stockAPet("Snake", "Simon");
        petStore.exercisePets();
    }


}
