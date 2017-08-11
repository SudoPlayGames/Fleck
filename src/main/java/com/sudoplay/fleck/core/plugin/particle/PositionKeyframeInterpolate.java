package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.Interpolator;
import com.sudoplay.fleck.integration.spi.plugin.KeyframeInterpolatePluginBase;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.fleck.integration.spi.plugin.KeyframeType;
import com.sudoplay.math.Vector3f;

public class PositionKeyframeInterpolate
    extends KeyframeInterpolatePluginBase
    implements ParticlePlugin {

  protected Vector3f[] position;

  public PositionKeyframeInterpolate(
      float[] newKey,
      Vector3f[] newPosition,
      KeyframeType newKeyType,
      Interpolator newInterpolator
  ) {

    super(newKey, newKeyType, newInterpolator);
    this.position = new Vector3f[this.key.length];
    for (int i = 0; i < this.key.length; i++) {
      this.position[i] = new Vector3f(newPosition[i]);
    }
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

    this.position[0] = p.initialPosition;
    super.update(p, particleLifePercentage);
  }

  @Override
  protected void applyLastKeyframe(Particle p, int keyIndex) {

    p.position.set(this.position[keyIndex]);
  }

  @Override
  protected void applyInterpolatedKeyframes(Particle p, int keyIndex, float time) {

    this.interpolator.apply(this.position[keyIndex], this.position[keyIndex + 1], time, p.position);
  }

}
