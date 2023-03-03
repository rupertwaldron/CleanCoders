package com.ruppyrup.episode33.extensionObject;

import com.ruppyrup.episode33.extensionObject.explosion.AssemblyExplosion;
import com.ruppyrup.episode33.extensionObject.explosion.ExplosionGenerator;
import com.ruppyrup.episode33.extensionObject.explosion.PiecePartExplosion;

public class CatalogueMain {

  public static void main(String[] args) {
    ProductCatalogue productCatalogue = new ProductCatalogue();

    Product bike = new Product("Mountain Bike");

    PiecePart frame = new PiecePart("bikeFrame", 001);
    PiecePart saddle = new PiecePart("bikeSaddle", 002);
    Assembly wheel = new Assembly("bikeWheel", 003);
    PiecePart hub = new PiecePart("wheelhub", 004);
    PiecePart spokes = new PiecePart("bikespokes", 005);

    wheel.addPart(hub);
    wheel.addPart(spokes);

    bike.addPart(frame);
    bike.addPart(saddle);
    bike.addPart(wheel);
    bike.addPart(wheel);

    productCatalogue.addProduct(bike);

    System.out.println("Calalogue :: " + productCatalogue);
    System.out.println("*********************************");

    PiecePartExplosion frameExplosion = new PiecePartExplosion(frame);
    PiecePartExplosion saddleExplosion = new PiecePartExplosion(saddle);
    PiecePartExplosion hubExplosion = new PiecePartExplosion(hub);
    PiecePartExplosion spokesExplosion = new PiecePartExplosion(spokes);
    AssemblyExplosion wheelAssemblyExplosion = new AssemblyExplosion(wheel);

    frame.addExtension("explosion", frameExplosion);
    saddle.addExtension("explosion", saddleExplosion);
    wheel.addExtension("explosion", wheelAssemblyExplosion);
    hub.addExtension("explosion", hubExplosion);
    spokes.addExtension("explosion", spokesExplosion);

    ExplosionGenerator explosion = new ExplosionGenerator(productCatalogue);
    explosion.runExtension();

  }

}
