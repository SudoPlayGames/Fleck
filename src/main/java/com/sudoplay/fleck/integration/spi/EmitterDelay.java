package com.sudoplay.fleck.integration.spi;

public interface EmitterDelay {

  /**
   * Returns true while the delay is active.
   *
   * @param dt delta time in seconds
   * @return true while the delay is active
   */
  boolean update(float dt);

}
