package com.ruppyrup.episode7.petstorearch.boundaries.output;


import com.ruppyrup.episode7.petstorearch.boundaries.OutputPort;

/**
 *  Implements Output interface which is polymorhic so control is passed back up the hierarchy
 */
public class TerminalOutput implements OutputPort {

  @Override
  public void writeToOuput(String output) {
    System.out.println(output);
  }
}
