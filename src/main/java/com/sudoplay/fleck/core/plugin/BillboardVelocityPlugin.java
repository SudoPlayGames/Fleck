package com.sudoplay.fleck.core.plugin;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.integration.spi.plugin.BillboardPlugin;
import com.sudoplay.math.Vector3f;

/**
 * Facing direction follows the velocity as it changes
 */
public class BillboardVelocityPlugin
    implements BillboardPlugin {

  private static final Vector3f LOCK = new Vector3f(0, 0.99f, 0.01f);

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
  }

}
