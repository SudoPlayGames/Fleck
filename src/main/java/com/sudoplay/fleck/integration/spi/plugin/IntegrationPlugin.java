package com.sudoplay.fleck.integration.spi.plugin;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;

public interface IntegrationPlugin {

  void initialize(ParticleEmitter newParticleEmitter, Particle[] particles);

  void integrate(Particle particle, float t, float dt);

  void onPostParticleInitialize(Particle particle);

}
