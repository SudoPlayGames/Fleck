package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.core.StaticTransformBinding;
import com.sudoplay.fleck.integration.spi.TransformBinding;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.Vector3f;

public class PositionPoint
    implements ParticlePlugin {

  public static final PositionPoint ORIGIN = new PositionPoint();

  protected TransformBinding positionBinding;

  public PositionPoint() {

    this(StaticTransformBinding.ORIGIN);
  }

  public PositionPoint(TransformBinding newPositionBinding) {

    this.positionBinding = newPositionBinding;
  }

  public PositionPoint(Vector3f newPosition) {

    this.positionBinding = new StaticTransformBinding(newPosition);
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

    this.positionBinding.getPosition(p.position);
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
