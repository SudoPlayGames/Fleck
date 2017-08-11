package com.sudoplay.fleck.core;

import com.sudoplay.fleck.integration.spi.ParticlePool;

import java.util.List;

/**
 * This particle pool uses a single array and a look-ahead method of finding the
 * next available particle.
 *
 * @author t0neg0d
 * @author sk3lls (Modified for LSS)
 */
public class LookAheadParticlePool
    implements ParticlePool {

  private Particle[] particleArray;
  private int maxParticles = 0;
  private int nextIndex = 0;

  public LookAheadParticlePool(int particleCount) {

    initialize(particleCount);
  }

  @Override
  public void initialize(int particleCount) {

    this.maxParticles = particleCount;
    this.particleArray = new Particle[this.maxParticles];

    for (int i = 0; i < this.maxParticles; i++) {
      this.particleArray[i] = new Particle(i);
    }
  }

  @Override
  public Particle acquire() {

    Particle particle = null;

    if (this.nextIndex != -1 && this.nextIndex < this.particleArray.length) {

      particle = this.particleArray[this.nextIndex];
      particle.live = true;

      int searchIndex = this.nextIndex;
      int initIndex = this.nextIndex;
      int loop = 0;

      while (this.particleArray[searchIndex].live) {
        searchIndex++;

        if (searchIndex > this.particleArray.length - 1) {
          searchIndex = 0;
          loop++;
        }

        if (searchIndex == initIndex && loop == 1) {
          searchIndex = -1;
          break;
        }

      }

      this.nextIndex = searchIndex;

    }

    return particle;
  }

  @Override
  public List<Particle> acquireAll(List<Particle> store) {

    for (Particle particle : this.particleArray) {

      if (!particle.live) {
        particle.live = true;
        store.add(particle);
      }

    }

    return store;
  }

  @Override
  public void release(Particle particle) {

    particle.live = false;
    this.nextIndex = particle.index;
  }

  @Override
  public Particle[] getParticles() {

    return this.particleArray;
  }

  @Override
  public int getParticleCount() {

    return this.particleArray.length;
  }

}
