package com.ruppyrup.episode7.petsorearch.entities;



/**
 * A nice simple factory, only of the few places it is ok to see a switch statement
 * PetType is a string rather than class or enum to reduce any coupling between libraries etc
 */
public class PetFactory {

    /**
     * Latest version of switch is used. Don't need break statements
     * @param petType
     * @param petName
     * @return
     */
    public Pet orderPet(String petType, String petName) {
        return switch(petType) {
            case "Dog" -> new Dog(petName);
            case "Snake" -> new Snake(petName);
            default -> throw new IllegalStateException("Unexpected value: " + petType);
        };
    }
}
