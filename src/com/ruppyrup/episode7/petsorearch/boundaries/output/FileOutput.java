package com.ruppyrup.episode7.petsorearch.boundaries.output;



import java.io.FileWriter;
import java.io.IOException;

/**
 *  Implements Output interface which is polymorhic so control is passed back up the hierarchy
 */
//public class FileOutput implements WriteToFile {
//
//  @Override
//  public void sendToFile(String output) {
//    try (FileWriter myWriter = new FileWriter("src/com/ruppyrup/hexagonalarch/romanout.txt")){
//      myWriter.write("Roman value is :: " + output);
//      System.out.println("Successfully wrote to the file.");
//    } catch (IOException e) {
//      System.out.println("An error occurred.");
//      e.printStackTrace();
//    }
//  }
//}
