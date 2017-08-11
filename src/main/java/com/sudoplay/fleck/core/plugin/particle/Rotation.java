package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.FastMath;
import com.sudoplay.math.Vector3f;

public class Rotation
    implements ParticlePlugin {

  public static final Rotation ZERO = new Rotation(Vector3f.ZERO);

  protected Vector3f rotation;

  public Rotation(Vector3f axisAngles) {

    this(axisAngles.x, axisAngles.y, axisAngles.z);
  }

  public Rotation(float x, float y, float z) {

    this.rotation = new Vector3f();
    this.rotation.x = FastMath.clamp(x, 0, FastMath.TWO_PI);
    this.rotation.y = FastMath.clamp(y, 0, FastMath.TWO_PI);
    this.rotation.z = FastMath.clamp(z, 0, FastMath.TWO_PI);
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

    p.rotation.set(this.rotation);
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
