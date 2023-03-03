package com.ruppyrup.episode33.extensionObject;

import java.util.LinkedList;
import java.util.List;

public class Product {

  private final String productName;

  public Product(String productName) {
    this.productName = productName;
  }

  private final List<Part> parts = new LinkedList<>();

  public void addPart(Part part) {
    parts.add(part);
  }

  public boolean removePart(Part part) {
    return parts.remove(part);
  }

  public List<Part> getParts() {
    return List.copyOf(parts);
  }
}
