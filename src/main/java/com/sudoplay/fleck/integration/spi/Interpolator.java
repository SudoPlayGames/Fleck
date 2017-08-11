package com.sudoplay.fleck.integration.spi;

import com.sudoplay.math.Vector3f;

public interface Interpolator {

  /**
   * Returns a value between start and end based on time t.
   * <p>
   * 0 <= t <= 1
   * 
   * @param start
   * @param end
   * @param t
   * @return
   */
  float apply(float start, float end, float t);

  /**
   * Returns a value between start and end based on time t.
   * <p>
   * 0 <= t <= 1
   * 
   * @param start
   * @param end
   * @param t
   * @param store
   * @return
   */
  Vector3f apply(Vector3f start, Vector3f end, float t, Vector3f store);

}
