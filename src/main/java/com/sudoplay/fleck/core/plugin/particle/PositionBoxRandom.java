package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.core.StaticTransformBinding;
import com.sudoplay.fleck.integration.spi.TransformBinding;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.FastMath;
import com.sudoplay.math.Vector3f;

public class PositionBoxRandom
    implements ParticlePlugin {

  protected TransformBinding positionBinding;
  protected Vector3f size;
  protected Vector3f halfSize;

  public PositionBoxRandom(
      Vector3f newSize
  ) {

    this(StaticTransformBinding.ORIGIN, newSize);
  }

  public PositionBoxRandom(
      Vector3f newPosition,
      Vector3f newSize
  ) {

    this(new StaticTransformBinding(newPosition), newSize);
  }

  public PositionBoxRandom(
      TransformBinding newPositionBinding,
      Vector3f newSize
  ) {

    this.positionBinding = newPositionBinding;
    this.size = new Vector3f(newSize);
    this.halfSize = new Vector3f(newSize).multLocal(0.5f);
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

    this.positionBinding.getPosition(p.position).subtractLocal(this.halfSize);

    p.position.x += size.x * FastMath.nextRandomFloat();
    p.position.y += size.y * FastMath.nextRandomFloat();
    p.position.z += size.z * FastMath.nextRandomFloat();

  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
