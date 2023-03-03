package com.ruppyrup.episode33.extensionObject;

import com.ruppyrup.episode33.extensionObject.explosion.AssemblyExplosion;
import com.ruppyrup.episode33.extensionObject.explosion.ExplosionGenerator;
import com.ruppyrup.episode33.extensionObject.explosion.PiecePartExplosion;
import com.ruppyrup.episode33.extensionObject.partnoextension.AssemblyNumber;
import com.ruppyrup.episode33.extensionObject.partnoextension.PartNumberUpdater;
import com.ruppyrup.episode33.extensionObject.partnoextension.PiecePartNumber;


public class CatalogueMain {

  public static void main(String[] args) {
    PartFactory partFactory = new PartFactory();
    AssemblyFactory assemblyFactory = new AssemblyFactory();

    PiecePart frame = partFactory.createPart("bikeFrame", 001, "explosion", "partNumberUpdate");
    PiecePart saddle = partFactory.createPart("bikeSaddle", 002, "explosion", "partNumberUpdate");
    Assembly wheel = assemblyFactory.createAssembly("bikeWheel", 003, "explosion", "partNumberUpdate");
    PiecePart hub = partFactory.createPart("wheelhub", 004, "explosion", "partNumberUpdate");
    PiecePart spokes = partFactory.createPart("bikespokes", 005, "explosion", "partNumberUpdate");

    ProductCatalogue productCatalogue = new ProductCatalogue();

    Product bike = new Product("Mountain Bike");


    wheel.addPart(hub);
    wheel.addPart(spokes);

    bike.addPart(frame);
    bike.addPart(saddle);
    bike.addPart(wheel);
    bike.addPart(wheel);

    productCatalogue.addProduct(bike);

    System.out.println("Calalogue :: " + productCatalogue);
    System.out.println("*********************************");

    ExplosionGenerator explosion = new ExplosionGenerator(productCatalogue);
    explosion.runExtension();

    PartNumberUpdater numberUpdater = new PartNumberUpdater(productCatalogue);
    numberUpdater.updateNumbersBy(1000);

    System.out.println("********************** Numbers updated ******************");

    explosion.runExtension();

  }

  static class PartFactory {
    PiecePart createPart(String name, int partId, String... extensions) {
      PiecePart piecePart = new PiecePart(name, partId);
      for (String ext : extensions) {
        addExtension(ext, piecePart);
      }
      return piecePart;
    }

    void addExtension(String extension, PiecePart part) {
      switch(extension) {
        case "explosion" -> part.addExtension("explosion", new PiecePartExplosion(part));
        case "partNumberUpdate" -> part.addExtension("partNumberUpdate", new PiecePartNumber(part));
        default -> throw new RuntimeException("Invalid extension");
      }
    }
  }

  static class AssemblyFactory {
    Assembly createAssembly(String name, int partId, String... extensions) {
      Assembly assembly = new Assembly(name, partId);
      for (String ext : extensions) {
        addExtension(ext, assembly);
      }
      return assembly;
    }

    void addExtension(String extension, Assembly assembly) {
      switch(extension) {
        case "explosion" -> assembly.addExtension("explosion", new AssemblyExplosion(assembly));
        case "partNumberUpdate" -> assembly.addExtension("partNumberUpdate", new AssemblyNumber(assembly));
        default -> throw new RuntimeException("Invalid extension");
      }
    }
  }

}
