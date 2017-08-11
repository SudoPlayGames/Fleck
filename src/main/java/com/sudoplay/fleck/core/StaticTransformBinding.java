package com.sudoplay.fleck.core;

import com.sudoplay.fleck.integration.spi.TransformBinding;
import com.sudoplay.math.Quaternion;
import com.sudoplay.math.Vector3f;

public class StaticTransformBinding
    implements TransformBinding {

  public static final StaticTransformBinding ORIGIN = new StaticTransformBinding(Vector3f.ZERO);

  private final Vector3f position;
  private final Quaternion rotation;
  private final Vector3f scale;

  public StaticTransformBinding(
      final Vector3f newPosition
  ) {

    this(newPosition, Quaternion.IDENTITY, Vector3f.UNIT_XYZ);
  }

  public StaticTransformBinding(
      final Vector3f newPosition,
      final Quaternion newRotation
  ) {

    this(newPosition, newRotation, Vector3f.UNIT_XYZ);
  }

  public StaticTransformBinding(
      final Vector3f newPosition,
      final Vector3f newScale
  ) {

    this(newPosition, Quaternion.IDENTITY, newScale);
  }

  public StaticTransformBinding(
      final Vector3f newPosition,
      final Quaternion newRotation,
      final Vector3f newScale
  ) {

    this.position = new Vector3f(newPosition);
    this.rotation = new Quaternion(newRotation);
    this.scale = new Vector3f(newScale);
  }

  @Override
  public Vector3f getPosition(final Vector3f store) {

    return store.set(this.position);
  }

  @Override
  public float getPositionX() {

    return this.position.x;
  }

  @Override
  public float getPositionY() {

    return this.position.y;
  }

  @Override
  public float getPositionZ() {

    return this.position.z;
  }

  @Override
  public Quaternion getRotation(Quaternion store) {

    return store.set(this.rotation);
  }

  @Override
  public Vector3f getScale(Vector3f store) {

    return store.set(this.scale);
  }

}
