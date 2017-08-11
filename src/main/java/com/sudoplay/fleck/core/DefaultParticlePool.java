package com.sudoplay.fleck.core;

import com.sudoplay.fleck.integration.spi.ParticlePool;

import java.util.List;

public class DefaultParticlePool
    implements ParticlePool {

  private Particle[] particleArray;
  private ParticleBag liveParticles;
  private ParticleBag deadParticles;

  public DefaultParticlePool(int particleCount) {

    initialize(particleCount);
  }

  @Override
  public void initialize(int particleCount) {

    this.particleArray = new Particle[particleCount];
    this.liveParticles = new ParticleBag(particleCount);
    this.deadParticles = new ParticleBag(particleCount);

    for (int i = 0; i < particleCount; i++) {

      this.particleArray[i] = new Particle(i);
      this.deadParticles.set(i, this.particleArray[i]);

    }

  }

  @Override
  public Particle acquire() {

    Particle particle = this.deadParticles.removeLast();

    if (particle != null) {

      particle.live = true;
      this.liveParticles.set(particle.index, particle);

    }

    return particle;
  }

  @Override
  public List<Particle> acquireAll(List<Particle> store) {

    Particle particle;

    while ((particle = this.deadParticles.removeLast()) != null) {

      particle.live = true;
      store.add(particle);
      this.liveParticles.set(particle.index, particle);

    }

    return store;
  }

  @Override
  public void release(Particle particle) {

    particle.live = false;
    this.liveParticles.set(particle.index, null);
    this.deadParticles.add(particle);
  }

  @Override
  public Particle[] getParticles() {

    return this.particleArray;
  }

  private class ParticleBag {

    private Particle[] data;
    private int size = 0;

    /**
     * Constructs an empty Bag with the specified initial capacity.
     *
     * @param capacity the initial capacity of Bag
     */
    public ParticleBag(int capacity) {

      this.data = new Particle[capacity];
    }

    /**
     * Remove and return the last object in the bag.
     *
     * @return the last object in the bag, null if empty.
     */
    public Particle removeLast() {

      if (this.size > 0) {

        Particle e = this.data[--size];
        this.data[this.size] = null;
        return e;

      }

      return null;
    }

    /**
     * Adds the specified element to the end of this bag. if needed also
     * increases the capacity of the bag.
     *
     * @param e element to be added to this list
     */
    public void add(Particle e) {

      this.data[size++] = e;
    }

    /**
     * Set element at specified index in the bag.
     *
     * @param index position of element
     * @param e     the element
     */
    public void set(int index, Particle e) {

      if (this.size <= index) {

        this.size = index + 1;

      }

      this.data[index] = e;
    }

  }

  @Override
  public int getParticleCount() {

    return this.particleArray.length;
  }

}
