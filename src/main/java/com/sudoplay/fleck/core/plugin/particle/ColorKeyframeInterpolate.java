package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.InterpolatorTween;
import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.core.plugin.ColorspaceCIELABPlugin;
import com.sudoplay.fleck.integration.spi.Colorspace;
import com.sudoplay.fleck.integration.spi.Interpolator;
import com.sudoplay.fleck.integration.spi.plugin.KeyframeInterpolatePluginBase;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.fleck.integration.spi.plugin.KeyframeType;
import com.sudoplay.math.ColorRGBA;
import com.sudoplay.math.Vector3f;

public class ColorKeyframeInterpolate
    extends KeyframeInterpolatePluginBase
    implements ParticlePlugin {

  protected Colorspace colorspace;
  protected Vector3f[] color;
  protected Vector3f colorStore;

  public ColorKeyframeInterpolate( //
                                   final float[] newKey, //
                                   final ColorRGBA[] newColor
  ) {

    this(newKey, newColor, KeyframeType.ParticleLife, ColorspaceCIELABPlugin.INSTANCE, InterpolatorTween.LINEAR);
  }

  public ColorKeyframeInterpolate( //
                                   final float[] newKey, //
                                   final ColorRGBA[] newColor, //
                                   final KeyframeType newKeyType, //
                                   final Colorspace newColorspace, //
                                   final Interpolator newInterpolator //
  ) {

    super(newKey, newKeyType, newInterpolator);
    colorspace = newColorspace;
    color = new Vector3f[key.length];
    for (int i = 0; i < key.length; i++) {
      color[i] = new Vector3f();
      colorspace.toColorspace(newColor[i], color[i]);
    }
    colorStore = new Vector3f();
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

    super.update(p, particleLifePercentage);
  }

  @Override
  protected void applyLastKeyframe(Particle p, int keyIndex) {

    colorspace.fromColorspace(color[keyIndex], p.color);
  }

  @Override
  protected void applyInterpolatedKeyframes(Particle p, int keyIndex, float time) {

    interpolator.apply(color[keyIndex], color[keyIndex + 1], time, colorStore);
    colorspace.fromColorspace(colorStore, p.color);
  }

}
