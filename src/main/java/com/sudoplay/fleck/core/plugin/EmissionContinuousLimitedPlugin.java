package com.sudoplay.fleck.core.plugin;

import com.sudoplay.fleck.integration.spi.plugin.EmissionPlugin;

public class EmissionContinuousLimitedPlugin
    implements EmissionPlugin {

  protected float particlesPerSecond;
  protected float counter;
  protected int particlesToSpawn;
  protected int totalParticles;

  public EmissionContinuousLimitedPlugin(float particlesPerSecond, float durationSeconds) {

    this.particlesPerSecond = particlesPerSecond;
    this.totalParticles = (int) (particlesPerSecond * durationSeconds);
  }

  @Override
  public void update(float tpf) {

    if (this.totalParticles > 0) {

      this.counter += this.particlesPerSecond * tpf;
      this.particlesToSpawn = (int) this.counter;
      this.counter -= this.particlesToSpawn;
      this.totalParticles -= this.particlesToSpawn;

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

    return this.totalParticles > 0;
  }

}
