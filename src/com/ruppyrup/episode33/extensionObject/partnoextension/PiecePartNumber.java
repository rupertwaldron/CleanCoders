package com.ruppyrup.episode33.extensionObject.partnoextension;

import com.ruppyrup.episode33.extensionObject.Part;

public class PiecePartNumber implements PartNumberExtension {

  private final Part part;

  public PiecePartNumber(Part part) {
    this.part = part;
  }

  @Override
  public void addToPartNumber(int addition) {
    part.setPartId(part.getPartId() + addition);
  }
}
