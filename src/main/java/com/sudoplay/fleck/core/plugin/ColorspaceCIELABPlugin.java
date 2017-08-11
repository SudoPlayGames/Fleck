package com.sudoplay.fleck.core.plugin;

import com.sudoplay.fleck.integration.spi.Colorspace;
import com.sudoplay.fleck.core.util.ColorUtil;
import com.sudoplay.math.ColorRGBA;
import com.sudoplay.math.TempVars;
import com.sudoplay.math.Vector3f;

public class ColorspaceCIELABPlugin
    implements Colorspace {

  public static final ColorspaceCIELABPlugin INSTANCE = new ColorspaceCIELABPlugin();

  private ColorspaceCIELABPlugin() {
    //
  }

  @Override
  public Vector3f toColorspace(ColorRGBA color) {

    return toColorspace(color, new Vector3f());
  }

  @Override
  public Vector3f toColorspace(ColorRGBA color, Vector3f store) {

    ColorUtil.rgb2xyz(color, store);
    ColorUtil.xyz2cielab(store, store);
    return store;
  }

  @Override
  public ColorRGBA fromColorspace(Vector3f colorspace) {

    return fromColorspace(colorspace, new ColorRGBA());
  }

  @Override
  public ColorRGBA fromColorspace(Vector3f colorspace, ColorRGBA store) {

    TempVars tv = TempVars.get();
    Vector3f temp = tv.vect1;
    ColorUtil.cielab2xyz(colorspace, temp);
    ColorUtil.xyz2rgb(temp, store);
    tv.release();
    return store;
  }

}
