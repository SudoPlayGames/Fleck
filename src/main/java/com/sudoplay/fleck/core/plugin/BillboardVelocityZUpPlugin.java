package com.sudoplay.fleck.core.plugin;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.integration.spi.plugin.BillboardPlugin;
import com.sudoplay.math.FastMath;
import com.sudoplay.math.Quaternion;
import com.sudoplay.math.Vector3f;

/**
 * Facing direction follows the velocity as it changes, Y of particle always
 * faces Z of velocity
 */
public class BillboardVelocityZUpPlugin
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

    if (particle.velocity.x != Vector3f.UNIT_Y.x
        && particle.velocity.y != Vector3f.UNIT_Y.y
        && particle.velocity.z != Vector3f.UNIT_Y.z) {

      up.set(particle.velocity).crossLocal(Vector3f.UNIT_Y).normalizeLocal();

    } else {

      up.set(particle.velocity).crossLocal(LOCK).normalizeLocal();

    }

    left.set(particle.velocity).crossLocal(up).normalizeLocal();
    direction.set(particle.velocity);

    this.rotStore.fromAngleNormalAxis(-90 * FastMath.DEG_TO_RAD, left);
    this.rotStore.mult(left, left);
    this.rotStore.mult(up, up);
  }

}
