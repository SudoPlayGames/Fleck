package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.TransformBinding;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.FastMath;

public class VelocityPointBindingRandom
    implements ParticlePlugin {

  protected TransformBinding positionBinding;
  protected float minVelocity;
  protected float velocityDiff;

  public VelocityPointBindingRandom(
      TransformBinding newPositionBinding,
      float newMinVelocity,
      float newMaxVelocity
  ) {

    this.positionBinding = newPositionBinding;
    this.minVelocity = newMinVelocity;
    this.velocityDiff = newMaxVelocity - newMinVelocity;
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

    this.positionBinding.getPosition(p.velocity)
        .subtractLocal(p.position)
        .normalizeLocal()
        .multLocal(this.minVelocity + FastMath.nextRandomFloat() * this.velocityDiff);
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
