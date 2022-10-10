package com.ruppyrup.episode7.petsorearch.entities;

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

    /**
     * exerciseDay could be private to increase the encapulsation because I don't want the
     * public api to access it directly. It is packgage private to aid with testing - so can be changed for certain tests
     * The subclasses don't need to access it directly
     */
    private LocalDate exerciseDay = LocalDate.now().minusDays(2);

    protected Pet(final String name) {
        this.name = name;
    }

    public String getPetName() {
        return name;
    }

    public abstract boolean isPetType(String petType);

    public void exercise() {
        exerciseDay = LocalDate.now();
    }

    /**
     * This is an example of "Tell Don't Ask" because I "tell" the class to do something
     * Alternatively I would have extracted the date via a getter and performed the business logic
     * in the other class which breaks encapsulation
     * @return boolean if pet has gone a day without exercising
     */
    public boolean doesPetNeedExercisingToday() {
        return LocalDate.now().minusDays(1L).isAfter(exerciseDay);
    }

    public void setExerciseDay(final LocalDate exerciseDay) {
        this.exerciseDay = exerciseDay;
    }
}
