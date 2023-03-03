package com.ruppyrup.episode33.extensionObject;

import java.util.LinkedList;
import java.util.List;

public class Assembly extends Part {

  private final List<Part> parts = new LinkedList<>();

  public Assembly(String partName, int partId) {
    super(partName, partId);
  }

  @Override
  public String getInfo() {
    StringBuilder sb = new StringBuilder();
    parts.forEach(part -> sb.append(part.getInfo()));
    return sb.toString();
  }

  public void addPart(Part part) {
    parts.add(part);
  }

  public boolean removePart(Part part) {
    return parts.remove(part);
  }

  public List<Part> getAssembly() {
    return List.copyOf(parts);
  }


}
