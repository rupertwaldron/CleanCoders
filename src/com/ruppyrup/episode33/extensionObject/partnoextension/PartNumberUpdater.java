package com.ruppyrup.episode33.extensionObject.partnoextension;


import com.ruppyrup.episode33.extensionObject.Assembly;
import com.ruppyrup.episode33.extensionObject.Part;
import com.ruppyrup.episode33.extensionObject.Product;
import com.ruppyrup.episode33.extensionObject.ProductCatalogue;

import java.util.HashSet;
import java.util.Set;

public class PartNumberUpdater {

  private final ProductCatalogue productCatalogue;

  public PartNumberUpdater(ProductCatalogue productCatalogue) {
    this.productCatalogue = productCatalogue;
  }

  public void updateNumbersBy(int update) {
    Set<Part> allUniqueParts = new HashSet<>();
    for (Product product : productCatalogue.getCatalogue()) {
      for (Part part: product.getParts()) {
        allUniqueParts.add(part);
        if (part instanceof Assembly assembly) {
          allUniqueParts.addAll(assembly.getAssembly());
        }
      }

      allUniqueParts
              .forEach(part -> ((PartNumberExtension) part.getExtension("partNumberUpdate")).addToPartNumber(update));
    }
  }
}
