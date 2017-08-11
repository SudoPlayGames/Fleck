package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.InterpolatorTween;
import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.Interpolator;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.Vector3f;

public class SizeInterpolate
    implements ParticlePlugin {

  protected Vector3f startSize;
  protected Vector3f endSize;
  protected Interpolator interpolator;

  public SizeInterpolate(Vector3f newStartSize, Vector3f newEndSize) {

    this.startSize = new Vector3f(newStartSize);
    this.endSize = new Vector3f(newEndSize);
    this.interpolator = InterpolatorTween.LINEAR;
  }

  public SizeInterpolate(
      Vector3f newStartSize,
      Vector3f newEndSize,
      Interpolator newInterpolator
  ) {

    this.startSize = new Vector3f(newStartSize);
    this.endSize = new Vector3f(newEndSize);
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

    this.interpolator.apply(this.startSize, this.endSize, 1.0f - particleLifePercentage, p.size);
  }

}