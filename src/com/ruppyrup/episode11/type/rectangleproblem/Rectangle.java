package com.ruppyrup.episode11.type.rectangleproblem;

import com.ruppyrup.episode11.type.Point;

public class Rectangle {
  private double height;
  private double width;
  private Point topLeft;

  public double area() {
    return height * width;
  }

  public double perimeter() {
    return 2 * (height + width);
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(final double height) {
    this.height = height;
  }

  public double getWidth() {
    return width;
  }

  public void setWidth(final double width) {
    this.width = width;
  }
}

class Square extends Rectangle {

  @Override
  public void setHeight(final double height) {
    super.setHeight(height);
    super.setWidth(height);
  }

  @Override
  public void setWidth(final double width) {
    setHeight(width);
  }
}
