package com.sudoplay.fleck.core.plugin.force;

import com.sudoplay.fleck.core.PerlinNoise;
import com.sudoplay.fleck.integration.spi.plugin.FlowFieldBase;
import com.sudoplay.fleck.integration.spi.plugin.ForcePlugin;
import com.sudoplay.math.Vector3f;

public class ForceFlowFieldPlugin
    extends FlowFieldBase
    implements ForcePlugin {

  protected Vector3f store;

  public ForceFlowFieldPlugin(
      float newScale,
      Vector3f newInfluence,
      PerlinNoise newNoise,
      long newSeed
  ) {

    super(newScale, newInfluence, newNoise, newSeed);
    this.store = new Vector3f();
  }

  @Override
  public Vector3f calculateForce(float mass, Vector3f position, Vector3f velocity, float t) {

    return calculate(position, t, this.store);
  }

}
