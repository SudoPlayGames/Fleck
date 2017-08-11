package com.sudoplay.fleck.integration.spi;

import com.sudoplay.math.Quaternion;
import com.sudoplay.math.Vector3f;

public interface TransformBinding {

  Vector3f getPosition(Vector3f store);

  float getPositionX();

  float getPositionY();

  float getPositionZ();

  Quaternion getRotation(Quaternion store);

  Vector3f getScale(Vector3f store);

}
