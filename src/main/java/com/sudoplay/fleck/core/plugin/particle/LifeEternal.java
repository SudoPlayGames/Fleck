package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;

public class LifeEternal
    implements ParticlePlugin {

  public static final LifeEternal INSTANCE = new LifeEternal();

  private LifeEternal() {
    //
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

    p.life = p.maxLife = 1;
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
