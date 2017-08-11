package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.core.StaticTransformBinding;
import com.sudoplay.fleck.integration.spi.TransformBinding;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.FastMath;
import com.sudoplay.math.Vector3f;

public class PositionCylinderRandom
    implements ParticlePlugin {

  private static final float MAX_ANGLE_RADIANS = 359.9999f * FastMath.DEG_TO_RAD;

  protected TransformBinding positionBinding;
  protected float minRadius;
  protected float radiusDiff;
  protected float height;
  protected float halfHeight;

  public PositionCylinderRandom(
      float newRadius
  ) {

    this(StaticTransformBinding.ORIGIN, newRadius, newRadius, 0);
  }

  public PositionCylinderRandom(
      float newRadius,
      float newHeight
  ) {

    this(StaticTransformBinding.ORIGIN, newRadius, newRadius, newHeight);
  }

  public PositionCylinderRandom(
      float newMinRadius,
      float newMaxRadius,
      float newHeight
  ) {

    this(StaticTransformBinding.ORIGIN, newMinRadius, newMaxRadius, newHeight);
  }

  public PositionCylinderRandom(
      Vector3f newPosition,
      float newMinRadius,
      float newMaxRadius,
      float newHeight
  ) {

    this(new StaticTransformBinding(newPosition), newMinRadius, newMaxRadius, newHeight);
  }

  public PositionCylinderRandom(
      TransformBinding newPositionBinding,
      float newMinRadius,
      float newMaxRadius,
      float newHeight
  ) {

    this.positionBinding = newPositionBinding;
    this.minRadius = newMinRadius;
    this.radiusDiff = newMaxRadius - newMinRadius;
    this.height = newHeight;
    this.halfHeight = this.height * 0.5f;
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

    float radius = this.minRadius + this.radiusDiff * FastMath.nextRandomFloat();
    float angle = MAX_ANGLE_RADIANS * FastMath.nextRandomFloat();
    positionBinding.getPosition(p.position);
    p.position.x += FastMath.cosLUT(angle) * radius;
    p.position.y += this.height * FastMath.nextRandomFloat() - this.halfHeight;
    p.position.z += FastMath.sinLUT(angle) * radius;
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
