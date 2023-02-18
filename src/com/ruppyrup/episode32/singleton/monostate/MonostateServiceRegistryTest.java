package com.ruppyrup.episode32.singleton.monostate;



import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MonostateServiceRegistryTest {
  @Test
  public void addedServiceCanBeRetrieved() throws Exception {
    Service s = new Service();
    MonostateServiceRegistry r1 = new MonostateServiceRegistry();
    MonostateServiceRegistry r2 = new MonostateServiceRegistry();

    r1.register("ServiceName", s);
    assertEquals(s, r2.getService("ServiceName"));
  }
}

class MonostateServiceRegistry {
  private static Map<String, Service> services;
  private static boolean initialized = false;

  public MonostateServiceRegistry() {
    if (!initialized) {
      services = new HashMap<>();
      initialized = true;
    }
  }

  public void register(String name, Service service) {
    services.put(name, service);
  }

  public Service getService(String name) {
    return services.get(name);
  }

}

class Service {}
