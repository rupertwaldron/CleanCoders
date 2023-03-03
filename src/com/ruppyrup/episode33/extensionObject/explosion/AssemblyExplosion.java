package com.ruppyrup.episode33.extensionObject.explosion;

import com.ruppyrup.episode33.extensionObject.Part;

public class AssemblyExplosion implements PartExplosionExtension {

  private final Part part;

  public AssemblyExplosion(Part part) {
    this.part = part;
  }

  @Override
  public void generateExplosionReport() {
    System.out.println("\n--- Assembly Explosion-----\n");
    System.out.println(" - " + part.getInfo());
  }
}
