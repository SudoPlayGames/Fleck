package com.sudoplay.fleck.core.plugin;

import com.sudoplay.fleck.integration.spi.plugin.EmissionPlugin;
import com.sudoplay.math.FastMath;

public class EmissionBurstContinuousRandom
    implements EmissionPlugin {

  protected float counter;

  public int particlesMin;
  public int particlesMax;
  protected int particlesToSpawn;

  public float delayMin;
  public float delayMax;
  protected float delayRng;

  public EmissionBurstContinuousRandom(
      int newParticlesMin,
      int newParticlesMax,
      float newDelayMin,
      float newDelayMax
  ) {

    this.particlesMin = Math.max(0, newParticlesMin);
    this.particlesMax = Math.max(newParticlesMin, newParticlesMax);

    this.delayMin = Math.max(0, newDelayMin);
    this.delayMax = Math.max(newDelayMin, newDelayMax);
    this.delayRng = Math.max(1, this.delayMax - this.delayMin);
  }

  @Override
  public void update(float tpf) {

    this.counter -= tpf;

    if (this.counter <= 0) {

      this.counter += FastMath.nextRandomFloat() * this.delayRng + this.delayMin;
      this.particlesToSpawn = FastMath.nextRandomInt(this.particlesMin, this.particlesMax);

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
