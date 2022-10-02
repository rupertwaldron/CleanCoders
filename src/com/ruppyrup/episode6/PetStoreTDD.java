package com.ruppyrup.episode6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PetStoreTDD {

  /**
   * Stocks a pet from the petStore
   * @param petType
   * @param petName
   */
  public void stockAPet(String petType, String petName) {

  }

  /**
   * Pet stock report will report on each pet in stock by name and how long they have been in the store
   * @return Stock report
   */
  public String getPetsInStockReport() {
    return null;
  }

  /**
   * Sells a pet from the shop
   * @param petType is the type of pet
   */
  public void sellPet(String petType) {

  }

  /**
   * Removes all the pets from stock
   */
  public void sendPetsToTheFarm() {

  }


}

class PetStoreTDDTest {

  @Test
  void canCreatePetStore() {
    PetStoreTDD petStoreTDD = new PetStoreTDD();
    Assertions.assertNotNull(petStoreTDD);
  }

}
