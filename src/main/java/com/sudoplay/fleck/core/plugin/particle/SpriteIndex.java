package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;

public class SpriteIndex
    implements ParticlePlugin {

  public static final SpriteIndex ZERO = new SpriteIndex(0);

  protected int index;

  /**
   * Sets all particles initial sprite index.
   */
  public SpriteIndex(int index) {

    this.index = index;
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

    p.spriteIndex = this.index;
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
