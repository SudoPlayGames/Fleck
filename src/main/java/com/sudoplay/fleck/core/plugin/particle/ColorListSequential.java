package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.ColorRGBA;

public class ColorListSequential
    implements ParticlePlugin {

  protected ColorRGBA[] colors;
  protected int maxIndex;
  protected int nextIndex;

  public ColorListSequential(final ColorRGBA[] newColors) {

    colors = new ColorRGBA[newColors.length];
    for (int i = 0; i < newColors.length; i++) {
      colors[i] = new ColorRGBA(newColors[i]);
    }
    maxIndex = colors.length - 1;
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

    p.color.set(colors[nextIndex]);
    if (++nextIndex > maxIndex) {
      nextIndex = 0;
    }
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
