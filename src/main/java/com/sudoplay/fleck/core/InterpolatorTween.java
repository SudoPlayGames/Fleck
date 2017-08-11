package com.sudoplay.fleck.core;

import com.sudoplay.fleck.integration.spi.Interpolator;
import com.sudoplay.math.Tween;
import com.sudoplay.math.Vector3f;

public class InterpolatorTween implements Interpolator {

  public static final InterpolatorTween LINEAR = new InterpolatorTween(Tween.LINEAR);

  private final Tween tween;

  public InterpolatorTween(final Tween newTween) {
    tween = newTween;
  }

  public static InterpolatorTween createFromTween(final Tween newTween) {
    return new InterpolatorTween(newTween);
  }

  @Override
  public float apply(float start, float end, float t) {
    return tween.tween(t, start, end - start, 1.0f);
  }

  @Override
  public Vector3f apply(Vector3f start, Vector3f end, float t, Vector3f store) {
    store.x = tween.tween(t, start.x, end.x - start.x, 1.0f);
    store.y = tween.tween(t, start.y, end.y - start.y, 1.0f);
    store.z = tween.tween(t, start.z, end.z - start.z, 1.0f);
    return store;
  }

}
