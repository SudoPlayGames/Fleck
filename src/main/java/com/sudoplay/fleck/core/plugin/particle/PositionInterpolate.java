package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.Interpolator;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.Vector3f;

public class PositionInterpolate
    implements ParticlePlugin {

  protected Vector3f endPosition;
  protected Interpolator interpolator;

  public PositionInterpolate(final Vector3f newEndPosition, Interpolator newInterpolator) {

    this.endPosition = new Vector3f(newEndPosition);
    this.interpolator = newInterpolator;
  }

  @Override
  public byte getPluginCode() {

    return PluginCode.UPDATE;
  }

  @Override
  public void initializePlugin(ParticleEmitter particleEmitter) {
    //
  }

  @Override
  public void initializeParticle(Particle p) {
    //
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {

    this.interpolator.apply(p.initialPosition, this.endPosition, 1.0f - particleLifePercentage, p.position);
  }

}