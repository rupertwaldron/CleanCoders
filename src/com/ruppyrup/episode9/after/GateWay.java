package com.ruppyrup.episode9.after;

import java.util.HashMap;
import java.util.Map;

public interface GateWay {
  void save(Employee employee);
  Employee getEmployeeById(long id);
}

class HashMapGateway implements GateWay {
  private static Map<Long, Employee> employees = new HashMap<>();

  @Override
  public void save(Employee employee) {
    employees.put(employee.getId(), employee);
  }

  @Override
  public Employee getEmployeeById(final long id) {
    return employees.get(id);
  }
}

class TreeMapGateway implements GateWay {
  private static Map<Long, Employee> employees = new HashMap<>();

  @Override
  public void save(Employee employee) {
    employees.put(employee.getId(), employee);
  }

  @Override
  public Employee getEmployeeById(final long id) {
    return employees.get(id);
  }
}


