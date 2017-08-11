package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.FastMath;

public class LifeRandom
    implements ParticlePlugin {

  protected float min;
  protected float max;
  protected float diff;

  /**
   * @param newMin
   * @param newMax
   */
  public LifeRandom(float newMin, float newMax) {

    newMin = Math.max(0, newMin);
    newMax = Math.max(0, newMax);
    min = Math.min(newMin, newMax);
    max = Math.max(newMin, newMax);
    diff = max - min;
  }

  @Override
  public byte getPluginCode() {

    return PluginCode.INITIALIZE | PluginCode.UPDATE;
  }

  @Override
  public void initializePlugin(ParticleEmitter particleEmitter) {
    //
  }

  @Override
  public void initializeParticle(Particle p) {

    p.life = p.maxLife = FastMath.nextRandomFloat() * diff + min;
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {

    p.life -= dt;
  }

}
