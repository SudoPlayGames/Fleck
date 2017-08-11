package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.core.StaticTransformBinding;
import com.sudoplay.fleck.integration.spi.TransformBinding;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.FastMath;
import com.sudoplay.math.Vector3f;

public class PositionLineRandom
    implements ParticlePlugin {

  protected TransformBinding startPositionBinding;
  protected TransformBinding endPositionBinding;

  public PositionLineRandom(
      Vector3f newStartPosition,
      Vector3f newEndPosition
  ) {

    this(new StaticTransformBinding(newStartPosition), new StaticTransformBinding(newEndPosition));
  }

  public PositionLineRandom(
      Vector3f newStartPosition,
      TransformBinding newEndPositionBinding
  ) {

    this(new StaticTransformBinding(newStartPosition), newEndPositionBinding);
  }

  public PositionLineRandom(
      TransformBinding newStartPositionBinding,
      Vector3f newEndPosition
  ) {

    this(newStartPositionBinding, new StaticTransformBinding(newEndPosition));
  }

  public PositionLineRandom(
      TransformBinding newStartPositionBinding,
      TransformBinding newEndPositionBinding
  ) {

    this.startPositionBinding = newStartPositionBinding;
    this.endPositionBinding = newEndPositionBinding;
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

    float rnd = FastMath.nextRandomFloat();
    p.position.x = (1.0f - rnd) * this.startPositionBinding.getPositionX() + rnd * this.endPositionBinding.getPositionX();
    p.position.y = (1.0f - rnd) * this.startPositionBinding.getPositionY() + rnd * this.endPositionBinding.getPositionY();
    p.position.z = (1.0f - rnd) * this.startPositionBinding.getPositionZ() + rnd * this.endPositionBinding.getPositionZ();
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
