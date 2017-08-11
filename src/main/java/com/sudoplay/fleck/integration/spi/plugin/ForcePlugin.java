package com.sudoplay.fleck.integration.spi.plugin;

import com.sudoplay.math.Vector3f;

public interface ForcePlugin {

  /**
   * Calculate the force to be applied.
   * <p>
   * NOTE: Particle mass is accounted for in the integration step and applied to
   * total force; DO NOT apply particle mass in your force calculations.
   *
   * @param mass
   * @param position
   * @param velocity
   * @param t
   * @return
   */
  Vector3f calculateForce(float mass, Vector3f position, Vector3f velocity, float t);

}
