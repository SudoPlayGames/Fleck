package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.core.StaticTransformBinding;
import com.sudoplay.fleck.integration.spi.TransformBinding;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.FastMath;
import com.sudoplay.math.Vector3f;

public class PositionSphereRandom
    implements ParticlePlugin {

  protected TransformBinding positionBinding;
  protected float minRadius;
  protected float radiusDiff;

  public PositionSphereRandom(
      float newMinRadius,
      float newMaxRadius
  ) {

    this(StaticTransformBinding.ORIGIN, newMinRadius, newMaxRadius);
  }

  public PositionSphereRandom(
      Vector3f newPosition,
      float newMinRadius,
      float newMaxRadius
  ) {

    this(new StaticTransformBinding(newPosition), newMinRadius, newMaxRadius);
  }

  /**
   * @param newPositionBinding
   * @param newMinRadius
   * @param newMaxRadius
   */
  public PositionSphereRandom(
      TransformBinding newPositionBinding,
      float newMinRadius,
      float newMaxRadius
  ) {

    this.positionBinding = newPositionBinding;
    this.minRadius = newMinRadius;
    this.radiusDiff = newMaxRadius - newMinRadius;
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

    float polar = FastMath.acos(2 * FastMath.nextRandomFloat() - 1);
    float azimuth = FastMath.PI * (2 * FastMath.nextRandomFloat() - 1);
    float radius = this.minRadius + this.radiusDiff * FastMath.nextRandomFloat();
    float sinIncRadius = FastMath.sinLUT(polar) * radius;

    this.positionBinding.getPosition(p.position);
    p.position.x += sinIncRadius * FastMath.cosLUT(azimuth);
    p.position.z += sinIncRadius * FastMath.sinLUT(azimuth);
    p.position.y += radius * FastMath.cosLUT(polar);
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
