package com.ruppyrup.episode11.lists;

import java.util.ArrayList;
import java.util.List;

public class ListTypes {

  public static void main(String[] args) {
    List<Circle> circles = new ArrayList<>();
    circles.add(new Circle());
    produceStuff(circles);

    List<Blob> blobs = new ArrayList<>();
    blobs.add(new Circle());
    consumeStuff(blobs);
  }

  private static void produceStuff(List<? extends Shape> l) {
    l.forEach(s -> System.out.println(s.whatAmI()));
  }

  private static void consumeStuff(List<? super Shape> l) {
    l.add(new Square());
  }
}

abstract class Blob {}

abstract class Shape extends Blob {
  abstract String whatAmI();
}

class Circle extends Shape {
  @Override
  String whatAmI() {
    return "circle";
  }
}

class Square extends Shape {
  @Override
  String whatAmI() {
    return "square";
  }
}
