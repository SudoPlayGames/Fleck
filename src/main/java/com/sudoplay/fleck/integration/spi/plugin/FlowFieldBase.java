package com.sudoplay.fleck.integration.spi.plugin;

import com.sudoplay.fleck.core.PerlinNoise;
import com.sudoplay.math.Vector2f;
import com.sudoplay.math.Vector3f;

public abstract class FlowFieldBase {

  protected Vector2f baseOffset;
  protected float scale;
  protected Vector3f influence;
  protected Vector2f offset;
  protected PerlinNoise noise;

  protected FlowFieldBase(
      float newScale,
      Vector3f newInfluence,
      PerlinNoise newNoise,
      long newSeed
  ) {

    this.scale = newScale;
    this.influence = new Vector3f(newInfluence);
    this.baseOffset = new Vector2f(16 + newSeed, 32 + newSeed);
    this.offset = new Vector2f();
    this.noise = newNoise;
  }

  protected Vector3f calculate(
      Vector3f particlePosition,
      float t,
      Vector3f store
  ) {

    this.offset.set(t + this.baseOffset.x, t + this.baseOffset.y);

    float dx = particlePosition.x * this.scale;
    float dy = particlePosition.y * this.scale;
    float dz = particlePosition.z * this.scale;

    this.noise.offset(0, 0, 0);
    float nx = this.noise.smoothNoise(dx, dy, dz, 1);
    this.noise.offset(this.offset.x, this.offset.x, this.offset.x);
    float ny = this.noise.smoothNoise(dx, dy, dz, 1);
    this.noise.offset(-this.offset.y, -this.offset.y, -this.offset.y);
    float nz = this.noise.smoothNoise(dx, dy, dz, 1);

    store.x = nx * this.influence.x;
    store.y = ny * this.influence.y;
    store.z = nz * this.influence.z;

    return store;
  }

}
