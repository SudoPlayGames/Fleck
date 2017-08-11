package com.sudoplay.fleck.core;

import com.sudoplay.math.ColorRGBA;
import com.sudoplay.math.Vector3f;

public class Particle {

  /**
   * The internal index of the particle in it's respective particle pool.
   * <p>
   * NOTE: for internal use only
   */
  public final int index;

  /**
   * Used internally and modified by the particle's pool.
   * <p>
   * NOTE: for internal use only, do not modify with plugin
   */
  public boolean live;

  /**
   * Life; changes every update.
   */
  private static final float DEFAULT_LIFE = 0.0f;
  public float life;

  /**
   * Max life; only changes when the particle is initialized.
   */
  private static final float DEFAULT_MAX_LIFE = 0.0f;
  public float maxLife;

  /**
   * Position.
   */
  private static final Vector3f DEFAULT_POSITION = Vector3f.ZERO;
  public final Vector3f position = new Vector3f(DEFAULT_POSITION);

  /**
   * Initial position.
   */
  public final Vector3f initialPosition = new Vector3f(DEFAULT_POSITION);

  /**
   * Velocity.
   */
  private static final Vector3f DEFAULT_VELOCITY = Vector3f.ZERO;
  public final Vector3f velocity = new Vector3f(DEFAULT_VELOCITY);

  /**
   * Rotation on each axis in radians.
   */
  private static final Vector3f DEFAULT_ROTATION = Vector3f.ZERO;
  public final Vector3f rotation = new Vector3f(DEFAULT_ROTATION);

  /**
   * Angular velocity.
   */
  private static final Vector3f DEFAULT_ANGULAR_VELOCITY = Vector3f.ZERO;
  public final Vector3f angularVelocity = new Vector3f(DEFAULT_ANGULAR_VELOCITY);

  /**
   * Size.
   */
  private static final Vector3f DEFAULT_SIZE = Vector3f.UNIT_XYZ;
  public final Vector3f size = new Vector3f(DEFAULT_SIZE);

  /**
   * Color.
   */
  private static final ColorRGBA DEFAULT_COLOR = ColorRGBA.White;
  public final ColorRGBA color = new ColorRGBA(DEFAULT_COLOR);

  /**
   * Sprite index.
   */
  private static final int DEFAULT_SPRITE_INDEX = 0;
  public int spriteIndex = DEFAULT_SPRITE_INDEX;

  /**
   * Mass.
   */
  private static final float DEFAULT_MASS = 1.0f;
  public float mass = DEFAULT_MASS;

  /**
   * Creates a new particle with the supplied index.
   *
   * @param newIndex
   */
  public Particle(final int newIndex) {

    this.index = newIndex;
  }

  /**
   * Resets the particle to its default state.
   */
  public void reset() {

    this.live = false;
    this.life = DEFAULT_LIFE;
    this.maxLife = DEFAULT_MAX_LIFE;
    this.position.set(DEFAULT_POSITION);
    this.initialPosition.set(DEFAULT_POSITION);
    this.velocity.set(DEFAULT_VELOCITY);
    this.rotation.set(DEFAULT_ROTATION);
    this.angularVelocity.set(DEFAULT_ANGULAR_VELOCITY);
    this.size.set(DEFAULT_SIZE);
    this.color.set(DEFAULT_COLOR).setAlpha(1);
    this.spriteIndex = DEFAULT_SPRITE_INDEX;
    this.mass = DEFAULT_MASS;
  }

}
