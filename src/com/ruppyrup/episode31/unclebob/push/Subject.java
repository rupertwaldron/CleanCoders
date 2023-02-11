package com.ruppyrup.episode31.unclebob.push;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject<T> {
  protected List<Observer<T>> observers = new ArrayList<>();

  public void register(Observer<T> observer) {
    observers.add(observer);
  }

  protected void notifyObservers(T pushedData) {
    observers.forEach(observer -> observer.update(pushedData));
  }

  public void remove(Observer<T> observer) {
    observers.remove(observer);
  }

  public void clear() {
    observers = new ArrayList<>();
  }
}
