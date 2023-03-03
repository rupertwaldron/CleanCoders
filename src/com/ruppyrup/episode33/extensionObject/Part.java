package com.ruppyrup.episode33.extensionObject;



import com.ruppyrup.episode33.extensionObject.explosion.PartExtension;

import java.util.HashMap;
import java.util.Map;

public abstract class Part {
  private final Map<String, PartExtension> extensions = new HashMap<>();
  protected String partName;
  protected int partId;

  protected Part(String partName, int partId) {
    this.partName = partName;
    this.partId = partId;
  }

  public abstract String getInfo();

  public void addExtension(String key, PartExtension value) {
    extensions.put(key, value);
  }

  public PartExtension getExtension(String key) {
    return extensions.get(key);
  }
}
