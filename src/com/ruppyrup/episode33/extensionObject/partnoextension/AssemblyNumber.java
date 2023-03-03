package com.ruppyrup.episode33.extensionObject.partnoextension;

import com.ruppyrup.episode33.extensionObject.Part;

public class AssemblyNumber implements PartNumberExtension {

  private final Part part;

  public AssemblyNumber(Part part) {
    this.part = part;
  }

  @Override
  public void addToPartNumber(int addition) {
    part.setPartId(part.getPartId() + addition);
  }
}
