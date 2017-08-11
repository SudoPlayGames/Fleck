package com.sudoplay.fleck.integration.spi;

import com.sudoplay.math.ColorRGBA;
import com.sudoplay.math.Vector3f;

public interface Colorspace {

  Vector3f toColorspace(ColorRGBA color);

  Vector3f toColorspace(ColorRGBA color, Vector3f store);

  ColorRGBA fromColorspace(Vector3f colorspace);

  ColorRGBA fromColorspace(Vector3f colorspace, ColorRGBA store);

}
