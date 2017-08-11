package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.InterpolatorTween;
import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.Interpolator;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;

public class AlphaInterpolate
    implements ParticlePlugin {

  protected float startAlpha;
  protected float endAlpha;
  protected Interpolator interpolator;

  public AlphaInterpolate(float newStartAlpha, float newEndAlpha) {

    this(newStartAlpha, newEndAlpha, InterpolatorTween.LINEAR);
  }

  public AlphaInterpolate(float newStartAlpha, float newEndAlpha, Interpolator newInterpolator) {

    this.startAlpha = newStartAlpha;
    this.endAlpha = newEndAlpha;
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

    p.color.setAlpha(this.interpolator.apply(this.startAlpha, this.endAlpha, 1.0f - particleLifePercentage));
  }

}