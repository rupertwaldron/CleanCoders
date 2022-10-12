package com.ruppyrup.episode7.petstorearch.interactors;


import com.ruppyrup.episode7.petstorearch.boundaries.OutputPort;
import com.ruppyrup.episode7.petstorearch.boundaries.PetShopOwnerRequest;
import com.ruppyrup.episode7.petstorearch.entities.Pet;
import com.ruppyrup.episode7.petstorearch.entities.PetFactory;

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
  public void getPetsInStock() {
    StringBuilder sb = new StringBuilder();

    petsInStock.stream()
        .map(Object::toString)
        .forEach(sentence -> sb.append(sentence).append("\n"));

    port.writeToOuput(sb.toString());
  }

  // use-case owner sells pet if pet is available
  @Override
  public void sellPet(String petType) {
    Optional<Pet> optionalPet = petsInStock.stream()
                                    .filter(pet -> pet.isPetType(petType))
                                    .findFirst();

    optionalPet.ifPresentOrElse(pet -> {
      petsInStock.remove(pet);
      port.writeToOuput("You have sold " + pet);
    }, () -> port.writeToOuput("You don't have any " + petType + " to sell."));
  }

  // use-case get rid of pets
  @Override
  public void sendPetsToTheFarm() {
    port.writeToOuput("All pets sent to the \"Farm\"");
    petsInStock.clear();
  }
}

