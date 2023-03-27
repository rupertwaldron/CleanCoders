package com.ruppyrup.episode34.interpreter;

public class AdditionExpression implements Expression {
  private Expression firstExpression;
  private Expression secondExpression;

  public AdditionExpression(Expression firstExpression, Expression secondExpression) {
    this.firstExpression = firstExpression;
    this.secondExpression = secondExpression;
  }

  @Override
  public int interpret() {
    return this.firstExpression.interpret() + this.secondExpression.interpret();
  }

  @Override
  public String toString(){
    return "+";
  }
}
