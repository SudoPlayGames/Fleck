package com.sudoplay.fleck.core.util;

import com.sudoplay.math.ColorRGBA;
import com.sudoplay.math.FastMath;
import com.sudoplay.math.Vector3f;

public class ColorUtil {

  private static final float REF_X = 95.047f;
  private static final float REF_Y = 100.0f;
  private static final float REF_Z = 108.883f;

  private static final float A = 1.0f / 2.4f;
  private static final float B = 16.0f / 116.0f;

  /**
   * http://www.easyrgb.com/index.php?X=MATH
   * 
   * @param color
   * @param store
   * @return
   */
  public static Vector3f rgb2xyz(ColorRGBA color, Vector3f store) {

    float r = color.getRed();
    float g = color.getGreen();
    float b = color.getBlue();

    if (r > 0.04045f) {
      r = FastMath.pow((r + 0.055f) / 1.055f, 2.4f);
    } else {
      r /= 12.92f;
    }

    if (g > 0.04045f) {
      g = FastMath.pow((g + 0.055f) / 1.055f, 2.4f);
    } else {
      g /= 12.92f;
    }

    if (b > 0.04045f) {
      b = FastMath.pow((b + 0.055f) / 1.055f, 2.4f);
    } else {
      b /= 12.92f;
    }

    r *= 100;
    g *= 100;
    b *= 100;

    // Observer. = 2�, Illuminant = D65
    store.x = r * 0.4124f + g * 0.3576f + b * 0.1805f;
    store.y = r * 0.2126f + g * 0.7152f + b * 0.0722f;
    store.z = r * 0.0193f + g * 0.1192f + b * 0.9505f;

    return store;
  }

  public static ColorRGBA xyz2rgb(Vector3f color, ColorRGBA store) {

    // X from 0 to 95.047 (Observer = 2�, Illuminant = D65)
    // Y from 0 to 100.000
    // Z from 0 to 108.883

    float x = color.x * 0.01f;
    float y = color.y * 0.01f;
    float z = color.z * 0.01f;

    float r = x * 3.2406f + y * -1.5372f + z * -0.4986f;
    float g = x * -0.9689f + y * 1.8758f + z * 0.0415f;
    float b = x * 0.0557f + y * -0.2040f + z * 1.0570f;

    if (r > 0.0031308f) {
      r = 1.055f * (FastMath.pow(r, A)) - 0.055f;
    } else {
      r *= 12.92f;
    }

    if (g > 0.0031308f) {
      g = 1.055f * (FastMath.pow(g, A)) - 0.055f;
    } else {
      g *= 12.92f;
    }

    if (b > 0.0031308f) {
      b = 1.055f * (FastMath.pow(b, A)) - 0.055f;
    } else {
      b *= 12.92f;
    }

    store.setRed(r);
    store.setGreen(g);
    store.setBlue(b);

    return store;
  }

  public static Vector3f xyz2cielab(Vector3f color, Vector3f store) {

    float x = color.x / REF_X;
    float y = color.y / REF_Y;
    float z = color.z / REF_Z;

    if (x > 0.008856f) {
      x = (float) Math.cbrt(x);
    } else {
      x = (7.787f * x) + B;
    }

    if (y > 0.008856f) {
      y = (float) Math.cbrt(y);
    } else {
      y = (7.787f * y) + B;
    }

    if (z > 0.008856f) {
      z = (float) Math.cbrt(z);
    } else {
      z = (7.787f * z) + B;
    }

    store.x = (116.0f * y) - 16.0f; // L*
    store.y = 500.0f * (x - y); // a*
    store.z = 200.0f * (y - z); // b*

    return store;
  }

  public static Vector3f cielab2xyz(Vector3f color, Vector3f store) {

    float y = (color.x + 16.0f) / 116.0f;
    float x = color.y / 500.0f + y;
    float z = y - color.z / 200.0f;

    if (FastMath.pow(y, 3) > 0.008856f) {
      y = y * y * y;
    } else {
      y = (y - 16.0f / 116.0f) / 7.787f;
    }

    if (FastMath.pow(x, 3) > 0.008856f) {
      x = x * x * x;
    } else {
      x = (x - 16.0f / 116.0f) / 7.787f;
    }

    if (FastMath.pow(z, 3) > 0.008856f) {
      z = z * z * z;
    } else {
      z = (z - 16.0f / 116.0f) / 7.787f;
    }

    store.x = x * REF_X;
    store.y = y * REF_Y;
    store.z = z * REF_Z;

    return store;
  }

}
