package com.ruppyrup.episode7.petsorearch.boundaries.input;

import com.ruppyrup.episode7.petsorearch.boundaries.PetShopOwnerRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  Uses interfaces closer to the core so control in correct
 */
public class FileInput {

  private final PetShopOwnerRequest petShopRequest;

  public FileInput(final PetShopOwnerRequest petShopRequest) {
    this.petShopRequest = petShopRequest;
  }

  public void fetchFromfile() {
    File myObj = new File("src/com/ruppyrup/episode7/petsorearch/boundaries/input/test.txt");
    try (Scanner scanner = new Scanner(myObj)){
      while (scanner.hasNextLine()) {
        String data = scanner.nextLine();
        System.out.println("Data read from file :: " + data);
        String[] split = data.split(",");
        switch (split[0]) {
          case "stockAPet" : petShopRequest.stockAPet(split[1], split[2]); break;
          case "getPetsInStock" : petShopRequest.getPetsInStock(); break;
          default : petShopRequest.sendPetsToTheFarm();
        }

      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
