package com.sudoplay.fleck.core.plugin;

import com.sudoplay.fleck.integration.spi.plugin.EmissionPlugin;

public class EmissionBurstContinuousPlugin
    implements EmissionPlugin {

  protected float delay;
  protected int particlesPerBurst;

  protected float counter;
  protected int particlesToSpawn;

  public EmissionBurstContinuousPlugin(int particlesPerBurst) {

    this(particlesPerBurst, 0);
  }

  public EmissionBurstContinuousPlugin(int particlesPerBurst, float delay) {

    this.delay = delay;
    this.particlesPerBurst = particlesPerBurst;
  }

  @Override
  public void update(float tpf) {

    this.counter -= tpf;
    if (this.counter <= 0) {
      this.counter += this.delay;
      this.particlesToSpawn = this.particlesPerBurst;
    } else {
      this.particlesToSpawn = 0;
    }
  }

  @Override
  public int getCount() {

    return this.particlesToSpawn;
  }

  @Override
  public boolean isActive() {

    return true;
  }

}
