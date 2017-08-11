package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;

public class Life
    implements ParticlePlugin {

  public static final Life ONE_THOUSAND = new Life(1000);

  protected float life;

  public Life(float life) {

    this.life = life;
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

    p.life = p.maxLife = life;
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {

    p.life -= dt;
  }

}
