package com.ruppyrup.episode11.type;

public class Point {
  public double x;
  public double y;

  public void translate(double dx, double dy) {
    x += dx;
    y += dy;
  }
}
