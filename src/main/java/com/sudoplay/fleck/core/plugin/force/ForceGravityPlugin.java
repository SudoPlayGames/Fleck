package com.sudoplay.fleck.core.plugin.force;

import com.sudoplay.fleck.integration.spi.TransformBinding;
import com.sudoplay.fleck.integration.spi.plugin.ForcePlugin;
import com.sudoplay.math.Vector3f;

public class ForceGravityPlugin
    implements ForcePlugin {

  public static final float GRAVITATIONAL_CONSTANT = (float) 6.67384e-11;
  public static final float DEFAULT_GRAVITATIONAL_CONSTANT = 1.0f;

  protected TransformBinding positionBinding;
  protected float mass;
  protected float gravitationalConstant;

  protected Vector3f store;

  public ForceGravityPlugin(
      TransformBinding newPositionBinding,
      float newMass
  ) {

    this(newPositionBinding, newMass, DEFAULT_GRAVITATIONAL_CONSTANT);
  }

  public ForceGravityPlugin(
      TransformBinding newPositionBinding,
      float newMass,
      float newGravitationalConstant
  ) {

    positionBinding = newPositionBinding;
    mass = newMass;
    gravitationalConstant = newGravitationalConstant;

    store = new Vector3f();
  }

  @Override
  public Vector3f calculateForce(
      float particleMass,
      Vector3f positionState,
      Vector3f velocityState,
      float t
  ) {

    positionBinding.getPosition(store);
    float distanceSquared = positionState.distanceSquared(store);
    store.subtractLocal(positionState).normalizeLocal();
    float force = gravitationalConstant * (mass * particleMass) / (1 + distanceSquared);
    return store.multLocal(force);
  }

}
