package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.FastMath;

public class VelocitySphereRandom
    implements ParticlePlugin {

  protected float velocityMin;
  protected float velocityRange;

  protected float polarAngle;
  protected float polarAngleVariance;
  protected float azimuthAngle;
  protected float azimuthAngleVariance;

  public VelocitySphereRandom(
      float newVelocityMin,
      float newVelocityMax
  ) {

    this(
        newVelocityMin,
        newVelocityMax,
        0,
        FastMath.HALF_PI,
        0,
        FastMath.PI
    );
  }

  /**
   * -PI/2 <= newPolarAngle +/- newPolarAngleVariance < PI/2 <br>
   * PI <= newAzimuthAngle +/- newAzimuthAngleVariance < PI
   *
   * @param newVelocityMin
   * @param newVelocityMax
   * @param newPolarAngle
   * @param newPolarAngleVariance
   * @param newAzimuthAngle
   * @param newAzimuthAngleVariance
   */
  public VelocitySphereRandom(
      float newVelocityMin,
      float newVelocityMax,
      float newPolarAngle,
      float newPolarAngleVariance,
      float newAzimuthAngle,
      float newAzimuthAngleVariance
  ) {

    this.velocityMin = Math.max(0, newVelocityMin);
    this.velocityRange = Math.max(0, newVelocityMax - newVelocityMin);
    this.polarAngle = newPolarAngle;
    this.polarAngleVariance = newPolarAngleVariance;
    this.azimuthAngle = newAzimuthAngle;
    this.azimuthAngleVariance = newAzimuthAngleVariance;
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

    float polar = this.polarAngle + this.polarAngleVariance * (FastMath.nextRandomFloat() * 2 - 1);
    float azimuth = this.azimuthAngle + this.azimuthAngleVariance * (FastMath.nextRandomFloat() * 2 - 1);

    float velocity = this.velocityMin + this.velocityRange * FastMath.nextRandomFloat();
    float sinIncVelocity = FastMath.sinLUT(polar) * velocity;

    p.velocity.x = sinIncVelocity * FastMath.cosLUT(azimuth);
    p.velocity.y = velocity * FastMath.cosLUT(polar);
    p.velocity.z = sinIncVelocity * FastMath.sinLUT(azimuth);
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
