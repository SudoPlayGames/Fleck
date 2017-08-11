package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.ColorRGBA;
import com.sudoplay.math.FastMath;

public class ColorListRandom
    implements ParticlePlugin {

  protected ColorRGBA[] colors;
  protected int maxIndex;

  public ColorListRandom(final ColorRGBA[] newColors) {

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

    p.color.set(colors[FastMath.nextRandomInt(0, maxIndex)]);
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
