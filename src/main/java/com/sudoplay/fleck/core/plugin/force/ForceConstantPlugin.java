package com.sudoplay.fleck.core.plugin.force;

import com.sudoplay.fleck.integration.spi.plugin.ForcePlugin;
import com.sudoplay.math.Vector3f;

public class ForceConstantPlugin
    implements ForcePlugin {

  protected Vector3f force;
  protected Vector3f store;

  public ForceConstantPlugin(final Vector3f newDirection, final float newMagnitude) {

    this.force = new Vector3f(newDirection).multLocal(newMagnitude);
    this.store = new Vector3f(this.force);
  }

  @Override
  public Vector3f calculateForce(final float mass, Vector3f position, Vector3f velocity, float t) {

    return this.store.set(this.force);
  }

}
