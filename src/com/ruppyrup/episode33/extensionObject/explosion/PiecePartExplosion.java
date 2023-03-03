package com.ruppyrup.episode33.extensionObject.explosion;

import com.ruppyrup.episode33.extensionObject.Part;

public class PiecePartExplosion implements PartExplosionExtension {

  private final Part part;

  public PiecePartExplosion(Part part) {
    this.part = part;
  }

  @Override
  public void generateExplosionReport() {
    System.out.println("\n---PiecePart Explosion-----\n");
    System.out.println(" - " + part.getInfo());
  }
}
