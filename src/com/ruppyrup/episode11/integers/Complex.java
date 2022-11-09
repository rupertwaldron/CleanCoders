package com.ruppyrup.episode11.integers;

public class Complex {
  private Real real;
  private Real imaginary;

  public Complex(final Real real, final Real imaginary) {
    this.real = real;
    this.imaginary = imaginary;
  }

}

class Real extends Complex {
  private int characteristic;
  private int mantissa;

  public Real(final int characteristic) {
    super(new Real(1), new Real(0));
    this.characteristic = characteristic;
  }
}

class Integer extends Real {

  public Integer(final int characteristic) {
    super(characteristic);
  }

  public static void main(String[] args) {
    Complex c = new Complex(new Real(1), new Real(0));
    System.out.println(c);
  }

}
