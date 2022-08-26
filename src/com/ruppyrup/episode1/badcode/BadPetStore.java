package com.ruppyrup.episode1.badcode;

import com.ruppyrup.episode1.goodcode.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.lazyTom.hackerLib.StealMyCode;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.lazyTom.hackerLib.StealMyCode.prettyPrint;

public class BadPetStore {

    public BadPetStore() {
        PetStock.badPets.add(new BadPet("Fido", "Dog"));
        PetStock.badPets.add(new BadPet("Simon", "Snake"));
    }

    /**
     * Do stuff in pet shop
     *
     * @param petToSell
     * @param exercisePets
     * @param goOnHoliday
     */
    public void runShop(String petToSell, boolean sellPet, boolean exercisePets, boolean stockAPet, String petToStock, boolean goOnHoliday) {

        if (exercisePets) {
            PetStock.badPets.forEach(pet -> {
                switch (pet.getType()) {
                    case "Dog":
                        if (pet.getExerciseDate().isBefore(LocalDate.now().minusDays(1))) {
                            prettyPrint("Walk the dog called " + pet.getName());
                            pet.setExerciseDate(LocalDate.now());
                        }
                    case "Snake":
                        if (pet.getExerciseDate().isBefore(LocalDate.now().minusDays(1))) {
                            prettyPrint("Let the snake called " + pet.getName() + " out of the cage");
                            pet.setExerciseDate(LocalDate.now());
                        }
                    default:
                        prettyPrint("This isn't the pet you are looking for!");
                }
            });
        }

        // sellPet
        if (sellPet) {
            PetStock.badPets.forEach(pet -> {
                if (pet.getType() == petToSell) {
                    prettyPrint("Sold pet -> " + pet.getName());
                    PetStock.badPets.remove(pet);
                }
            });
        }

        if (stockAPet) {
            PetStock.badPets.add(new BadPet("Bob", petToStock));
        }

        if (goOnHoliday) {
            PetStock.badPets.clear();
        }

    }
}

class TestBadPetStore {

    BadPetStore badPetStore;

    @BeforeEach
    void setup() {
        PetStock.badPets.clear();
        badPetStore = new BadPetStore();
    }

    @ParameterizedTest
    @CsvSource({
            "Dog, false, false, false, Monkey, false, 2",
            "Dog, true, false, false, Monkey, false, 1",
            "Dog, false, true, false, Monkey, false, 2",
            "Dog, false, true, true, Car, false, 3",
            "Dog, false, true, true, Car, true, 0",
    })
    void testBadPetStore(String petToSell, boolean sellPet, boolean exercisePets, boolean stockAPet, String petToStock, boolean goOnHoliday, int expectedCount) {
        badPetStore.runShop(petToSell, sellPet, exercisePets, stockAPet, petToStock, goOnHoliday);
        Assertions.assertEquals(expectedCount, PetStock.badPets.size());
    }

}


