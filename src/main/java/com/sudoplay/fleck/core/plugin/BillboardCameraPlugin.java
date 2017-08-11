package com.sudoplay.fleck.core.plugin;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.integration.spi.plugin.BillboardPlugin;
import com.sudoplay.math.Vector3f;

public class BillboardCameraPlugin
    implements BillboardPlugin {

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

    up.set(cameraUp);
    left.set(cameraLeft);
    direction.set(cameraDirection);
  }

}
