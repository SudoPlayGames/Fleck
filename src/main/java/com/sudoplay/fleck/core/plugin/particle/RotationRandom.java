package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.FastMath;

public class RotationRandom
    implements ParticlePlugin {

  protected boolean x;
  protected boolean y;
  protected boolean z;

  public RotationRandom(boolean x, boolean y, boolean z) {

    this.x = x;
    this.y = y;
    this.z = z;
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

    if (this.x) {
      p.rotation.x = FastMath.nextRandomFloat() * FastMath.TWO_PI;
    }
    if (this.y) {
      p.rotation.y = FastMath.nextRandomFloat() * FastMath.TWO_PI;
    }
    if (this.z) {
      p.rotation.z = FastMath.nextRandomFloat() * FastMath.TWO_PI;
    }
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
