package com.sudoplay.fleck.core.plugin;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.fleck.integration.spi.plugin.TimestepPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * http://gafferongames.com/game-physics/fix-your-timestep/
 *
 * @author Jason Taylor
 */
public class TimestepGafferPlugin
    implements TimestepPlugin {

  private static final Logger LOG = LoggerFactory.getLogger(TimestepGafferPlugin.class);

  protected static final float DT = 0.01f;

  protected float t;
  protected float accumulator;

  @Override
  public void update(ParticleEmitter particleEmitter, float tpf) {

    this.accumulator += tpf;

    while (this.accumulator >= DT) {
      this.internalUpdate(particleEmitter, t, DT);
      this.accumulator -= DT;
      this.t += DT;
    }
  }

  protected void internalUpdate(ParticleEmitter particleEmitter, float t, float dt) {

    Particle particle;
    Particle[] particles = particleEmitter.getParticles();
    boolean hasActiveParticles = false;
    float particleLifePercentage;

    // update the emitter's emission plugin
    particleEmitter.getParticleEmissionPlugin().update(dt);

    // attempt to acquire new particles if needed and initialize
    if (particleEmitter.isActive()) {
      int newParticles = particleEmitter.getParticleEmissionPlugin().getCount();

      if (newParticles > 0) {

        for (int i = 0; i < newParticles; i++) {
          particle = particleEmitter.getParticlePool().acquire();

          if (particle == null) {
            break;
          }

          List<ParticlePlugin> initializers = particleEmitter.getInitializerParticlePlugins();

          for (int j = 0; j < initializers.size(); j++) {
            initializers.get(j).initializeParticle(particle);
          }

          particleEmitter.getParticleIntegrationPlugin().onPostParticleInitialize(particle);
          particle.initialPosition.set(particle.position);
        }
      }
    }

    // update the particles
    for (int i = 0; i < particles.length; i++) {
      particle = particles[i];

      if (particle.life <= 0 && particle.live) {
        particle.reset();
        particleEmitter.getParticlePool().release(particle);
        continue;
      }

      if (particle.live) {
        hasActiveParticles = true;
        particleLifePercentage = particle.life / particle.maxLife;
        List<ParticlePlugin> updaters = particleEmitter.getUpdateParticlePlugins();

        for (int j = 0; j < updaters.size(); j++) {
          updaters.get(j).updateParticle(particle, particleLifePercentage, t, dt);
        }

        particleEmitter.getParticleIntegrationPlugin().integrate(particle, t, dt);

      }

    }

    // check if this emitter is dead
    if (!hasActiveParticles && (!particleEmitter.isActive()
        || !particleEmitter.getParticleEmissionPlugin().isActive())) {

      particleEmitter.setDead(true);
    }
  }

}
