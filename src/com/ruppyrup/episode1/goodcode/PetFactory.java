package com.ruppyrup.episode1.goodcode;

public class PetFactory {

    public Pet orderPet(String petType, String petName) {
        return switch(petType) {
            case "Dog" -> new Dog(petName);
            case "Snake" -> new Snake(petName);
            default -> throw new IllegalStateException("Unexpected value: " + petType);
        };
    }
}
