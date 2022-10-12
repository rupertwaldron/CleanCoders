package com.ruppyrup.episode7.petstorearch.boundaries;

import com.ruppyrup.episode7.petstorearch.entities.Pet;

import java.util.List;
import java.util.Optional;

public interface PetShopOwnerRequest {
  // use-case Petshop owner stocks a pet
  void stockAPet(String petType, String petName);

  // use-case owners lists the pets in stock
  void getPetsInStock();

  // use-case owner sells pet if pet is available
  void sellPet(String petType);

  // use-case get rid of pets
  void sendPetsToTheFarm();
}
