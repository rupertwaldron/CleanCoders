package com.ruppyrup.episode33.extensionObject;

import java.util.LinkedList;
import java.util.List;

public class ProductCatalogue {
  private final List<Product> catalogue = new LinkedList<>();

  public void addProduct(Product product) {
    catalogue.add(product);
  }

  public boolean removeProduct(Product product) {
    return catalogue.remove(product);
  }

  public List<Product> getCatalogue() {
    return List.copyOf(catalogue);
  }
}
