package com.ruppyrup.episode9.after;


import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
  private static AtomicInteger nextId = new AtomicInteger();
  private final long id;
  private final String name;
  private double salary;

  public Employee(final String name, final double salary) {
    this.id = nextId.getAndIncrement();
    this.name = name;
    this.salary = salary;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setSalary(final double salary) {
    this.salary = salary;
  }

  public double getSalary() {
    return salary;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Employee employee = (Employee) o;

    if (id != employee.id) return false;
    if (Double.compare(employee.salary, salary) != 0) return false;
    return Objects.equals(name, employee.name);
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = (int) (id ^ (id >>> 32));
    result = 31 * result + (name != null ? name.hashCode() : 0);
    temp = Double.doubleToLongBits(salary);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }


}

