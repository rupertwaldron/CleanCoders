package com.ruppyrup.episode7.petsorearch.boundaries.input;


import com.ruppyrup.episode7.petsorearch.boundaries.PetShopOwnerRequest;
import com.ruppyrup.episode7.petsorearch.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


/**
 *  Uses interfaces closer to the core so control in correct
 */
public class KeyboardInput {
  private final PetShopOwnerRequest petShopRequest;
  private final Scanner scanner;

  public KeyboardInput(final PetShopOwnerRequest petShopRequest, final Scanner scanner) {
    this.petShopRequest = petShopRequest;
    this.scanner = scanner;
  }
//  private final Scanner scanner = new Scanner(System.in);

  public void fetchFromKeyboard() {
    String input = scanner.nextLine();
    String[] split = input.split(",");
    switch (split[0]) {
      case "stockAPet" : petShopRequest.stockAPet(split[1], split[2]); break;
      case "getPetsInStock" : petShopRequest.getPetsInStock(); break;
      default : petShopRequest.sendPetsToTheFarm();
    }
  }
}

class TestKeyboardInput {

  private KeyboardInput keyboardInput;
  private MockPetShopRequest mockPetShopRequest;
  private Scanner mockScanner;

  @BeforeEach
  void setup() throws FileNotFoundException {
    mockPetShopRequest = new MockPetShopRequest();
    File inputText = new File("src/com/ruppyrup/episode7/petsorearch/boundaries/input/test.txt");
    mockScanner = new Scanner(inputText);
    keyboardInput = new KeyboardInput(mockPetShopRequest, mockScanner);
  }

  @Test
  void canStockAPet() {
    keyboardInput.fetchFromKeyboard();
    Assertions.assertEquals("Dog", mockPetShopRequest.petType);
    Assertions.assertEquals("Jake", mockPetShopRequest.petName);
  }

  @Test
  void getPetsInStock() {
    keyboardInput.fetchFromKeyboard();
    keyboardInput.fetchFromKeyboard();
    Assertions.assertEquals(1, mockPetShopRequest.listPetsCounter);
  }

  @Test
  void sendPetsToTheFarm() {
    keyboardInput.fetchFromKeyboard();
    keyboardInput.fetchFromKeyboard();
    keyboardInput.fetchFromKeyboard();
    Assertions.assertEquals(1, mockPetShopRequest.petsToFarmCounter);
  }
}

class MockPetShopRequest implements PetShopOwnerRequest {

  String petType;
  String petName;
  int listPetsCounter;
  int petsToFarmCounter;

  @Override
  public void stockAPet(final String petType, final String petName) {
    this.petType = petType;
    this.petName = petName;
  }

  @Override
  public List<Pet> getPetsInStock() {
    listPetsCounter++;
    return null;
  }

  @Override
  public Optional<Pet> sellPet(final String petType) {
    return Optional.empty();
  }

  @Override
  public void sendPetsToTheFarm() {
    petsToFarmCounter++;
  }

  @Override
  public void exercisePets() {

  }
}
