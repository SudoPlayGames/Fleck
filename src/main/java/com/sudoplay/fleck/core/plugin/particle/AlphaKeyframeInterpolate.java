package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.InterpolatorTween;
import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.Interpolator;
import com.sudoplay.fleck.integration.spi.plugin.KeyframeInterpolatePluginBase;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.fleck.integration.spi.plugin.KeyframeType;

import java.util.Arrays;

public class AlphaKeyframeInterpolate
    extends KeyframeInterpolatePluginBase
    implements ParticlePlugin {

  protected float[] alpha;

  public AlphaKeyframeInterpolate(
      float[] newKey,
      float[] newColor
  ) {

    this(newKey, newColor, KeyframeType.ParticleLife, InterpolatorTween.LINEAR);
  }

  public AlphaKeyframeInterpolate(
      float[] newKey,
      float[] newColor,
      KeyframeType newKeyType
  ) {

    this(newKey, newColor, newKeyType, InterpolatorTween.LINEAR);
  }

  public AlphaKeyframeInterpolate(
      float[] newKey,
      float[] newColor,
      KeyframeType newKeyType,
      Interpolator newInterpolator
  ) {

    super(newKey, newKeyType, newInterpolator);
    this.alpha = Arrays.copyOf(newColor, newColor.length);
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

    super.update(p, particleLifePercentage);
  }

  @Override
  protected void applyLastKeyframe(Particle p, int keyIndex) {

    p.color.setAlpha(this.alpha[keyIndex]);
  }

  @Override
  protected void applyInterpolatedKeyframes(Particle p, int keyIndex, float time) {

    float newAlpha = this.interpolator.apply(this.alpha[keyIndex], this.alpha[keyIndex + 1], time);
    p.color.setAlpha(newAlpha);
  }

  @Override
  public byte getPluginCode() {

    return PluginCode.UPDATE;
  }

}
