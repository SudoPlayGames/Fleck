package com.sudoplay.fleck.core.plugin;

import com.sudoplay.fleck.integration.spi.plugin.EmissionPlugin;

public class EmissionContinuousPlugin
    implements EmissionPlugin {

  public float particlesPerSecond;
  protected float counter;
  protected int particlesToSpawn;

  public EmissionContinuousPlugin(float particlesPerSecond) {

    this.particlesPerSecond = particlesPerSecond;
  }

  @Override
  public void update(float tpf) {

    this.counter += this.particlesPerSecond * tpf;
    this.particlesToSpawn = (int) this.counter;
    this.counter -= this.particlesToSpawn;
  }

  @Override
  public int getCount() {

    return particlesToSpawn;
  }

  @Override
  public boolean isActive() {

    return true;
  }

}
