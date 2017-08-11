package com.sudoplay.fleck.core.util;

import com.sudoplay.fleck.integration.spi.plugin.ForcePlugin;
import com.sudoplay.math.Vector3f;

import java.util.List;

public class ForceUtil {

  /**
   * Returns accumulated force divided by mass.
   *
   * @param mass         mass
   * @param position     position
   * @param velocity     velocity
   * @param dt           change in time
   * @param forcePlugins force plugins
   * @param store        store the result
   * @return stored result
   */
  public static Vector3f accumulateAcceleration(
      float mass,
      Vector3f position,
      Vector3f velocity,
      float dt,
      List<ForcePlugin> forcePlugins,
      Vector3f store
  ) {

    if (!forcePlugins.isEmpty()) {

      for (int i = 0; i < forcePlugins.size(); i++) {
        store.addLocal(forcePlugins.get(i).calculateForce(mass, position, velocity, dt));
      }

      store.divideLocal(mass);

    }

    return store;

  }

  private ForceUtil() {
    //
  }

}
