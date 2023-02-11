package com.ruppyrup.episode31.unclebob.pull;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
  protected List<Observer> observers = new ArrayList<>();

  public void register(Observer observer) {
    observers.add(observer);
  }

  protected void notifyObservers() {
    observers.forEach(observer -> observer.update());
  }

  public void remove(Observer observer) {
    observers.remove(observer);
  }

  public void clear() {
    observers = new ArrayList<>();
  }
}
