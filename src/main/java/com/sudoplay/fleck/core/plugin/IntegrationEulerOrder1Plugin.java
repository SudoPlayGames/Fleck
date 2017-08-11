package com.sudoplay.fleck.core.plugin;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.IntegrationPlugin;
import com.sudoplay.fleck.core.util.ForceUtil;
import com.sudoplay.math.Vector3f;

public class IntegrationEulerOrder1Plugin
    implements IntegrationPlugin {

  protected Vector3f force;

  protected ParticleEmitter particleEmitter;

  public IntegrationEulerOrder1Plugin() {

    this.force = new Vector3f();
  }

  @Override
  public void initialize(ParticleEmitter newParticleEmitter, Particle[] particles) {

    this.particleEmitter = newParticleEmitter;
  }

  @Override
  public void integrate(Particle particle, float t, float dt) {

    particle.position.x += particle.velocity.x * dt;
    particle.position.y += particle.velocity.y * dt;
    particle.position.z += particle.velocity.z * dt;
    ForceUtil.accumulateAcceleration(
        particle.mass,
        particle.position,
        particle.velocity,
        t + dt,
        this.particleEmitter.getParticleForcePlugins(),
        this.force.set(Vector3f.ZERO)
    );
    particle.velocity.x += this.force.x * dt;
    particle.velocity.y += this.force.y * dt;
    particle.velocity.z += this.force.z * dt;

    particle.rotation.x += particle.angularVelocity.x * dt;
    particle.rotation.y += particle.angularVelocity.y * dt;
    particle.rotation.z += particle.angularVelocity.z * dt;
    ForceUtil.accumulateAcceleration(
        particle.mass,
        particle.rotation,
        particle.angularVelocity,
        t + dt,
        this.particleEmitter.getParticleRotationalForcePlugins(),
        this.force.set(Vector3f.ZERO)
    );
    particle.angularVelocity.x += this.force.x * dt;
    particle.angularVelocity.y += this.force.y * dt;
    particle.angularVelocity.z += this.force.z * dt;
  }

  @Override
  public void onPostParticleInitialize(Particle particle) {
    //
  }

}
