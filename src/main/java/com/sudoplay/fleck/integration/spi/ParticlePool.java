package com.sudoplay.fleck.integration.spi;

import java.util.List;

import com.sudoplay.fleck.core.Particle;

public interface ParticlePool {

  void initialize(int particleCount);

  Particle acquire();

  List<Particle> acquireAll(List<Particle> store);

  void release(Particle particle);

  Particle[] getParticles();

  int getParticleCount();
  
}
