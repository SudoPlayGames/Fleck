package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.core.StaticTransformBinding;
import com.sudoplay.fleck.integration.spi.TransformBinding;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.Vector3f;

public class SpecialDeadZone
    implements ParticlePlugin {

  protected TransformBinding positionBinding;
  protected float radiusSquared;

  protected Vector3f store;

  public SpecialDeadZone(
      float newRadius
  ) {

    this(StaticTransformBinding.ORIGIN, newRadius);
  }

  public SpecialDeadZone(
      Vector3f newPosition,
      float newRadius
  ) {

    this(new StaticTransformBinding(newPosition), newRadius);
  }

  public SpecialDeadZone(
      TransformBinding newPositionBinding,
      float newRadius
  ) {

    this.positionBinding = newPositionBinding;
    this.radiusSquared = newRadius * newRadius;
    this.store = new Vector3f();
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

    this.positionBinding.getPosition(this.store);
    if (this.store.distanceSquared(p.position) < this.radiusSquared) {
      p.life = 0;
    }

  }

}
