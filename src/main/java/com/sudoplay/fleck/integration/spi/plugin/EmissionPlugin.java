package com.sudoplay.fleck.integration.spi.plugin;

public interface EmissionPlugin {

  void update(float tpf);

  int getCount();

  boolean isActive();

}
