package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.FastMath;

public class MassRandom
    implements ParticlePlugin {

  protected float massMin;
  protected float massRange;

  public MassRandom(float newMassMin, float newMassMax) {

    this.massMin = newMassMin;
    this.massRange = newMassMax - newMassMin;
  }

  @Override
  public void initializePlugin(ParticleEmitter particleEmitter) {
    //
  }

  @Override
  public byte getPluginCode() {

    return PluginCode.INITIALIZE;
  }

  @Override
  public void initializeParticle(Particle p) {

    p.mass = FastMath.nextRandomFloat() * this.massRange + this.massMin;
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
