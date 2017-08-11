package com.sudoplay.fleck.integration.spi.plugin;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.math.Vector3f;

public interface BillboardPlugin {

  void billboard(
      Particle particle,
      Vector3f up,
      Vector3f left,
      Vector3f direction,
      Vector3f cameraUp,
      Vector3f cameraLeft,
      Vector3f cameraDirection
  );

}
