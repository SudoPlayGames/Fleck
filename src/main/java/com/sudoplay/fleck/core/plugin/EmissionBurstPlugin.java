package com.sudoplay.fleck.core.plugin;

import com.sudoplay.fleck.integration.spi.plugin.EmissionPlugin;

public class EmissionBurstPlugin
    implements EmissionPlugin {

  protected float delay;
  protected int bursts;
  protected int particlesPerBurst;

  protected float counter;
  protected int burstsRemaining;
  protected int particlesToSpawn;

  public EmissionBurstPlugin(int particlesPerBurst) {

    this(particlesPerBurst, 1, 0);
  }

  public EmissionBurstPlugin(int particlesPerBurst, int bursts, float delay) {

    this.delay = delay;
    this.bursts = bursts;
    this.burstsRemaining = bursts;
    this.particlesPerBurst = particlesPerBurst;
  }

  @Override
  public void update(float tpf) {

    if (this.burstsRemaining == 0) {
      this.particlesToSpawn = 0;
      return;
    }

    this.counter -= tpf;

    if (this.counter <= 0) {

      this.counter += this.delay;
      this.burstsRemaining -= 1;
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

    return this.burstsRemaining > 0;
  }

}
