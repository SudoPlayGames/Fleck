package com.sudoplay.fleck.core.plugin.force;

import com.sudoplay.fleck.core.StaticTransformBinding;
import com.sudoplay.fleck.integration.spi.TransformBinding;
import com.sudoplay.fleck.integration.spi.plugin.ForcePlugin;
import com.sudoplay.math.Vector3f;

public class ForceSpringPlugin
    implements ForcePlugin {

  protected TransformBinding positionBinding;
  protected float tension;
  protected float damping;

  protected Vector3f v1 = new Vector3f();
  protected Vector3f v2 = new Vector3f();

  public ForceSpringPlugin(float newTension, float newDamping) {

    this(StaticTransformBinding.ORIGIN, newTension, newDamping);
  }

  public ForceSpringPlugin(
      TransformBinding newPositionBinding,
      float newTension,
      float newDamping
  ) {

    this.positionBinding = newPositionBinding;
    this.tension = newTension;
    this.damping = newDamping;
  }

  @Override
  public Vector3f calculateForce(
      float mass,
      Vector3f positionState,
      Vector3f velocityState,
      float t
  ) {

    this.positionBinding.getPosition(this.v2);
    this.v1.set(positionState).subtractLocal(this.v2).multLocal(-this.tension);
    this.v2.set(velocityState).multLocal(this.damping);
    this.v1.subtractLocal(this.v2);
    return this.v1;
  }

}
