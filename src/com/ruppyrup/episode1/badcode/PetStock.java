package com.ruppyrup.episode1.badcode;

import java.util.ArrayList;

public class PetStock {

    public static ArrayList<BadPet> badPets = new ArrayList<>();

    public static void stealPetsBack() {
        badPets.clear();
    }
}
