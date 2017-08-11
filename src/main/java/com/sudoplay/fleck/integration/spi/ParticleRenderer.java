package com.sudoplay.fleck.integration.spi;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.math.Matrix3f;
import com.sudoplay.math.Vector3f;

public interface ParticleRenderer {

  void initialize(ParticleEmitter newParticleEmitter);

  void render(
      Particle[] particles,
      Vector3f cameraUp,
      Vector3f cameraLeft,
      Vector3f cameraDirection,
      Matrix3f inverseRotation
  );

}
