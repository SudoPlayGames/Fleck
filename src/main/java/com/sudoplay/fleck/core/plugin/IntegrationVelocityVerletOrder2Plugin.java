package com.sudoplay.fleck.core.plugin;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ForcePlugin;
import com.sudoplay.fleck.integration.spi.plugin.IntegrationPlugin;
import com.sudoplay.fleck.core.util.ForceUtil;
import com.sudoplay.math.Vector3f;

import java.util.List;

/**
 * http://en.wikipedia.org/wiki/Verlet_integration#Velocity_Verlet
 *
 * @author Jason Taylor
 */
public class IntegrationVelocityVerletOrder2Plugin
    implements IntegrationPlugin {

  protected ParticleEmitter particleEmitter;

  protected Vector3f[] particleForce;
  protected Vector3f[] particleRotationalForce;

  @Override
  public void initialize(ParticleEmitter newParticleEmitter, Particle[] particles) {

    this.particleEmitter = newParticleEmitter;
    this.particleForce = new Vector3f[particles.length];
    this.particleRotationalForce = new Vector3f[particles.length];
  }

  @Override
  public void integrate(Particle particle, float t, float dt) {

    integrate_internal(
        particle.position,
        particle.velocity,
        this.particleForce[particle.index],
        particle.mass,
        t,
        dt,
        this.particleEmitter.getParticleForcePlugins()
    );
    integrate_internal(
        particle.rotation,
        particle.angularVelocity,
        this.particleRotationalForce[particle.index],
        particle.mass,
        t,
        dt,
        this.particleEmitter.getParticleRotationalForcePlugins()
    );
  }

  public void integrate_internal(
      Vector3f position,
      Vector3f velocity,
      Vector3f acceleration,
      float mass,
      float t,
      float dt,
      List<ForcePlugin> forcePlugins
  ) {

    /*
     * Initial integration using the forces from the previous step, noting that
     * velocities are advanced in half time step
     */
    velocity.x += acceleration.x * dt * 0.5f;
    velocity.y += acceleration.y * dt * 0.5f;
    velocity.z += acceleration.z * dt * 0.5f;

    position.x += velocity.x * dt;
    position.y += velocity.y * dt;
    position.z += velocity.z * dt;

    /*
     * Compute new forces from new positions
     */
    ForceUtil.accumulateAcceleration(
        mass,
        position,
        velocity,
        t + dt,
        forcePlugins,
        acceleration.set(Vector3f.ZERO)
    );

    /*
     * Final integration, update the velocities by half time step using the new
     * forces
     */
    velocity.x += acceleration.x * dt * 0.5f;
    velocity.y += acceleration.y * dt * 0.5f;
    velocity.z += acceleration.z * dt * 0.5f;

  }

  @Override
  public void onPostParticleInitialize(Particle particle) {
    /*
     * Compute the forces from the initial positions and velocities
     */
    if (this.particleForce[particle.index] == null) {
      this.particleForce[particle.index] = new Vector3f();
    }
    ForceUtil.accumulateAcceleration(
        particle.mass,
        particle.position,
        particle.velocity,
        0,
        this.particleEmitter.getParticleForcePlugins(),
        this.particleForce[particle.index].set(Vector3f.ZERO)
    );

    /*
     * Also, angular velocity...
     */
    if (this.particleRotationalForce[particle.index] == null) {
      this.particleRotationalForce[particle.index] = new Vector3f();
    }
    ForceUtil.accumulateAcceleration(
        particle.mass,
        particle.rotation,
        particle.angularVelocity,
        0,
        this.particleEmitter.getParticleRotationalForcePlugins(),
        this.particleRotationalForce[particle.index].set(Vector3f.ZERO)
    );
  }

}
