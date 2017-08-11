package com.sudoplay.fleck.core.plugin;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.integration.spi.plugin.BillboardPlugin;
import com.sudoplay.math.FastMath;
import com.sudoplay.math.Quaternion;
import com.sudoplay.math.Vector3f;

/**
 * Facing direction follows the velocity as it changes, Y of particle always
 * faces Z of velocity, Up of the particle always faces X
 */
public class BillboardVelocityZUpYLeftPlugin
    implements BillboardPlugin {

  private static final Vector3f LOCK = new Vector3f(0, 0.99f, 0.01f);

  protected Quaternion rotStore = new Quaternion();

  @Override
  public void billboard(
      Particle particle,
      Vector3f up,
      Vector3f left,
      Vector3f direction,
      Vector3f cameraUp,
      Vector3f cameraLeft,
      Vector3f cameraDirection
  ) {

    up.set(particle.velocity).crossLocal(Vector3f.UNIT_Y).normalizeLocal();
    left.set(particle.velocity).crossLocal(up).normalizeLocal();
    direction.set(particle.velocity);
    this.rotStore.fromAngleNormalAxis(90 * FastMath.DEG_TO_RAD, particle.velocity);
    this.rotStore.mult(left, left);
    this.rotStore.mult(up, up);
    this.rotStore.fromAngleNormalAxis(-90 * FastMath.DEG_TO_RAD, left);
    this.rotStore.mult(up, up);
  }

}
