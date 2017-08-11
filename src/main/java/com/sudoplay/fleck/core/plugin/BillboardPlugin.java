package com.sudoplay.fleck.core.plugin;

public final class BillboardPlugin {

  public static final com.sudoplay.fleck.integration.spi.plugin.BillboardPlugin CAMERA = new BillboardCameraPlugin();
  public static final com.sudoplay.fleck.integration.spi.plugin.BillboardPlugin NONE = new BillboardNonePlugin();
  public static final com.sudoplay.fleck.integration.spi.plugin.BillboardPlugin UNIT_X = new BillboardUnitXPlugin();
  public static final com.sudoplay.fleck.integration.spi.plugin.BillboardPlugin UNIT_Y = new BillboardUnitYPlugin();
  public static final com.sudoplay.fleck.integration.spi.plugin.BillboardPlugin UNIT_Z = new BillboardUnitZPlugin();
  public static final com.sudoplay.fleck.integration.spi.plugin.BillboardPlugin VELOCITY = new BillboardVelocityPlugin();
  public static final com.sudoplay.fleck.integration.spi.plugin.BillboardPlugin VELOCITY_Z_UP = new BillboardVelocityZUpPlugin();
  public static final com.sudoplay.fleck.integration.spi.plugin.BillboardPlugin VELOCITY_Z_UP_Y_LEFT = new BillboardVelocityZUpYLeftPlugin();

  private BillboardPlugin() {
    //
  }

}
