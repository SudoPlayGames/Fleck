package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.Vector3f;

public class VelocityExact
    implements ParticlePlugin {

  public static final VelocityExact ZERO = new VelocityExact(Vector3f.UNIT_Y, 0);

  protected Vector3f velocity;

  public VelocityExact(Vector3f newDirection, float newMagnitude) {

    this.velocity = new Vector3f(newDirection).normalizeLocal().multLocal(newMagnitude);
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

    p.velocity.set(this.velocity);
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
