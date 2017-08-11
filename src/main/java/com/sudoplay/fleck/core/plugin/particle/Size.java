package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.Vector3f;

public class Size
    implements ParticlePlugin {

  public static final Size ONE = new Size(1.0f);

  protected Vector3f size;

  public Size(final float newSize) {

    this.size = new Vector3f(newSize, newSize, newSize);
  }

  public Size(final float x, final float y, final float z) {

    this.size = new Vector3f(x, y, z);
  }

  public Size(final Vector3f newSize) {

    this.size = new Vector3f(newSize);
  }

  @Override
  public byte getPluginCode() {

    return PluginCode.INITIALIZE;
  }

  public void initializePlugin(ParticleEmitter particleEmitter) {
    //
  }

  ;

  @Override
  public void initializeParticle(Particle p) {

    p.size.set(this.size);
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
