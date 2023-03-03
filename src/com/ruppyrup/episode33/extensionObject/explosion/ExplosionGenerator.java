package com.ruppyrup.episode33.extensionObject.explosion;


import com.ruppyrup.episode33.extensionObject.Part;
import com.ruppyrup.episode33.extensionObject.Product;
import com.ruppyrup.episode33.extensionObject.ProductCatalogue;

public class ExplosionGenerator {

  private final ProductCatalogue productCatalogue;

  public ExplosionGenerator(ProductCatalogue productCatalogue) {
    this.productCatalogue = productCatalogue;
  }

  public void runExtension() {
    for (Product product : productCatalogue.getCatalogue()) {
      for (Part part: product.getParts()) {
        ((PartExplosionExtension) part.getExtension("explosion")).generateExplosionReport();
      }
    }
  }
}
