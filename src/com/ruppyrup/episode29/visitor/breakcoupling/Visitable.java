package com.ruppyrup.episode29.visitor.breakcoupling;

public interface Visitable {

  void accept(Visitor visitor);

  default String getName() {
    return this.getClass().getSimpleName();
  }
}
