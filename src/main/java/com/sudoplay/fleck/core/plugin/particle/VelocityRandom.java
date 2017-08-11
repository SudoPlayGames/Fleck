package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.FastMath;
import com.sudoplay.math.Vector3f;

public class VelocityRandom
    implements ParticlePlugin {

  protected Vector3f direction;
  protected float minVelocity;
  protected float velocityDiff;

  public VelocityRandom(Vector3f newDirection, float newMinVelocity, float newMaxVelocity) {

    this.direction = new Vector3f(newDirection).normalizeLocal();
    this.minVelocity = newMinVelocity;
    this.velocityDiff = newMaxVelocity - newMinVelocity;
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

    float velocity = this.minVelocity + this.velocityDiff * FastMath.nextRandomFloat();
    this.direction.mult(velocity, p.velocity);
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
