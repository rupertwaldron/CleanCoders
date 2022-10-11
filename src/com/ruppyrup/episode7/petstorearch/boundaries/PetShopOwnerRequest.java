package com.ruppyrup.episode7.petstorearch.boundaries;

import com.ruppyrup.episode7.petstorearch.entities.Pet;

import java.util.List;
import java.util.Optional;

public interface PetShopOwnerRequest {
  // use-case Petshop owner stocks a pet
  void stockAPet(String petType, String petName);

  // use-case owners lists the pets in stock
  List<Pet> getPetsInStock();

  // use-case owner sells pet if pet is available
  Optional<Pet> sellPet(String petType);

  // use-case get rid of pets
  void sendPetsToTheFarm();

  // use-case exercise pets
  void exercisePets();
}
