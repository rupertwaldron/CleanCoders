package com.ruppyrup.episode7.petstorearch;

import com.ruppyrup.episode7.petstorearch.boundaries.OutputPort;
import com.ruppyrup.episode7.petstorearch.boundaries.PetShopOwnerRequest;
import com.ruppyrup.episode7.petstorearch.boundaries.input.PetShopInput;
import com.ruppyrup.episode7.petstorearch.boundaries.output.TerminalOutput;
import com.ruppyrup.episode7.petstorearch.entities.PetFactory;
import com.ruppyrup.episode7.petstorearch.interactors.PetStoreInteractor;

import java.util.Scanner;

public class PetStoreMain {

  public static void main(String[] args) {
    OutputPort port = new TerminalOutput();
    PetFactory petFactory = new PetFactory();
    PetShopOwnerRequest petStore = new PetStoreInteractor(port, petFactory);
    Scanner scanner = new Scanner(System.in);
    PetShopInput petShopInput = new PetShopInput(petStore, scanner);

    System.out.print("PetShopOwner>");
    while (!"quit".equals(petShopInput.fetchFromKeyboard())) {
      System.out.print("PetShopOwner>");
    }
  }
}
