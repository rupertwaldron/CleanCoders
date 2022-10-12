package com.ruppyrup.episode7.petstorearch.entities;

import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * Pet is an abstract class rather than an interface because there is common state and methods
 * The increased coupling of an inheritance relationship doens't matter so much here.
 * Use of an interface would lead to duplicate code.
 */
public abstract class Pet {

    /**
     * Only need one logger so is static and can be used over the subclasses
     */
    protected static final Logger LOGGER = Logger.getLogger(Pet.class.getName());

    /**
     * private fields are not inherited, but accesses via public getters and setters.
     */
    private final String name;

    protected Pet(final String name) {
        this.name = name;
    }

    public String getPetName() {
        return name;
    }

    public abstract boolean isPetType(String petType);

    @Override
    public String toString() {
        return " with name " + name;
    }
}
