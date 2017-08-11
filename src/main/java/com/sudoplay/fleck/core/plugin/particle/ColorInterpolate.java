package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.InterpolatorTween;
import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.core.plugin.ColorspaceCIELABPlugin;
import com.sudoplay.fleck.integration.spi.Colorspace;
import com.sudoplay.fleck.integration.spi.Interpolator;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.ColorRGBA;
import com.sudoplay.math.Vector3f;

public class ColorInterpolate
    implements ParticlePlugin {

  protected Colorspace colorspace;
  protected Interpolator interpolator;

  protected Vector3f startColor;
  protected Vector3f endColor;
  protected Vector3f colorStore;

  public ColorInterpolate( //
                           final ColorRGBA newStartColor, //
                           final ColorRGBA newEndColor //
  ) {

    this(newStartColor, newEndColor, ColorspaceCIELABPlugin.INSTANCE, InterpolatorTween.LINEAR);
  }

  public ColorInterpolate( //
                           final ColorRGBA newStartColor, //
                           final ColorRGBA newEndColor, //
                           final Colorspace newColorspace //
  ) {

    this(newStartColor, newEndColor, newColorspace, InterpolatorTween.LINEAR);
  }

  public ColorInterpolate( //
                           final ColorRGBA newStartColor, //
                           final ColorRGBA newEndColor, //
                           final Colorspace newColorspace, //
                           final Interpolator newInterpolator //
  ) {

    colorspace = newColorspace;
    startColor = colorspace.toColorspace(newStartColor, new Vector3f());
    endColor = colorspace.toColorspace(newEndColor, new Vector3f());
    interpolator = newInterpolator;
    colorStore = new Vector3f();
  }

  @Override
  public byte getPluginCode() {

    return PluginCode.UPDATE;
  }

  @Override
  public void initializeParticle(Particle p) {
    //
  }

  @Override
  public void initializePlugin(ParticleEmitter particleEmitter) {
    //
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {

    interpolator.apply(startColor, endColor, 1.0f - particleLifePercentage, colorStore);
    colorspace.fromColorspace(colorStore, p.color);
  }

}
