package com.sudoplay.fleck.integration.spi.plugin;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.integration.spi.Interpolator;

import java.util.Arrays;

public abstract class KeyframeInterpolatePluginBase {

  protected Interpolator interpolator;
  protected float[] key;
  protected KeyframeType keyType;

  protected KeyframeInterpolatePluginBase(
      float[] newKey,
      KeyframeType newKeyType,
      Interpolator newInterpolator
  ) {

    this.interpolator = newInterpolator;
    this.key = Arrays.copyOf(newKey, newKey.length);
    this.keyType = newKeyType;
  }

  protected void update(Particle p, float particleLifePercentage) {

    int index = 0;
    float time = 0;

    switch (this.keyType) {

      case Fixed:
        time = p.maxLife - p.life;
        index = getPreviousKeyframeIndex(time);
        break;

      case ParticleLife:
        time = 1.0f - particleLifePercentage;
        index = getPreviousKeyframeIndex(time);
        break;
    }

    if (index + 1 >= this.key.length) {

      applyLastKeyframe(p, index);

    } else {

      time = (time - this.key[index]) / (this.key[index + 1] - this.key[index]);
      applyInterpolatedKeyframes(p, index, time);

    }

  }

  protected int getPreviousKeyframeIndex(float t) {

    for (int i = 0; i < this.key.length; i++) {

      if (this.key[i] == t) {

        return i;

      } else if (this.key[i] > t) {

        return i - 1;

      }

    }

    return this.key.length - 1;
  }

  protected abstract void applyLastKeyframe(Particle p, int keyIndex);

  protected abstract void applyInterpolatedKeyframes(Particle p, int keyIndex, float time);

}
