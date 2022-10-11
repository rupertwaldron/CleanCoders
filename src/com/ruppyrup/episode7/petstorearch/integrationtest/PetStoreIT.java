package com.ruppyrup.episode7.petstorearch.integrationtest;

import com.ruppyrup.episode7.petstorearch.boundaries.OutputPort;
import com.ruppyrup.episode7.petstorearch.boundaries.input.PetShopInput;
import com.ruppyrup.episode7.petstorearch.entities.PetFactory;
import com.ruppyrup.episode7.petstorearch.interactors.PetStoreInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PetStoreIT {

  private PetStoreInteractor petStore;
  private MockOutputPort port;
  private PetFactory petFactory;
  private PetShopInput petShopInput;
  private Scanner scanner;

  @BeforeEach
  void setup() throws FileNotFoundException {
    port = new MockOutputPort();
    petFactory = new PetFactory();
    petStore = new PetStoreInteractor(port, petFactory);
    File inputText = new File("src/com/ruppyrup/episode7/petstorearch/integrationtest/inputs.txt");
    scanner = new Scanner(inputText);
    petShopInput = new PetShopInput(petStore, scanner);
  }


  @Test
  void canStockAPet() {
    petShopInput.fetchFromKeyboard();
    Assertions.assertEquals("New pet added to stock : Dog with name Jake", port.getInputArg());
  }

  @Test
  void canListPetsInStock() {
    petShopInput.fetchFromKeyboard();
    petShopInput.fetchFromKeyboard();
    petShopInput.fetchFromKeyboard();
    String expected = """
        Dog with name Jake
        Snake with name Simon
        """;
    Assertions.assertEquals(expected, port.getInputArg());
  }

  @Test
  void canSendPetsToFarm() {
    petShopInput.fetchFromKeyboard();
    petShopInput.fetchFromKeyboard();
    petShopInput.fetchFromKeyboard();
    petShopInput.fetchFromKeyboard();
    Assertions.assertEquals("All pets sent to the \"Farm\"", port.getInputArg());
  }

  static class MockOutputPort implements OutputPort {
    String inputArg;
    @Override
    public void writeToOuput(final String output) {
      inputArg = output;
    }

    public String getInputArg() {
      return inputArg;
    }
  }
}
