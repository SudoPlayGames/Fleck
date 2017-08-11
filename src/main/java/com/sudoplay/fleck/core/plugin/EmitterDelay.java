package com.sudoplay.fleck.core.plugin;

import com.sudoplay.math.FastMath;

/**
 * Created by sk3lls on 1/31/2015.
 */
public class EmitterDelay
    implements com.sudoplay.fleck.integration.spi.EmitterDelay {

  public static final EmitterDelay ZERO = new EmitterDelay(0);

  protected float delay;
  protected boolean active;

  public EmitterDelay(float minDelay, float maxDelay) {

    this(FastMath.nextRandomFloat() * (maxDelay - minDelay) + minDelay);
  }

  public EmitterDelay(final float newDelay) {

    this.delay = newDelay;
    this.active = !FastMath.compareFloat(delay, 0);
  }

  @Override
  public boolean update(float dt) {

    if (active) {

      this.delay -= dt;

      if (this.delay <= 0) {

        this.active = false;
        return false;

      }

      return true;

    } else {

      return false;

    }

  }

}
