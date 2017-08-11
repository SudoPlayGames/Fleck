package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.FastMath;

public class SpriteIndexRandom
    implements ParticlePlugin {

  protected int min;
  protected int max;

  /**
   * Randomly picks a sprite between the min (inclusive) and max (inclusive).
   *
   * @param min min
   * @param max max
   */
  public SpriteIndexRandom(int min, int max) {

    this.min = min;
    this.max = max;
  }

  @Override
  public byte getPluginCode() {

    return PluginCode.INITIALIZE;
  }

  @Override
  public void initializePlugin(ParticleEmitter particleEmitter) {
    //
  }

  @Override
  public void initializeParticle(Particle p) {

    p.spriteIndex = FastMath.nextRandomInt(this.min, this.max);
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
