package com.sudoplay.fleck.core;

import com.sudoplay.fleck.integration.spi.EmitterDelay;
import com.sudoplay.fleck.integration.spi.ParticlePool;
import com.sudoplay.fleck.integration.spi.ParticleRenderer;
import com.sudoplay.fleck.integration.spi.plugin.*;
import com.sudoplay.math.FastMath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ParticleEmitter {

  private final EmitterDelay emitterDelay;
  private final ParticlePool particlePool;
  private final ParticleRenderer particleRenderer;

  private final EmissionPlugin particleEmissionPlugin;
  private final TimestepPlugin particleTimestepPlugin;
  private final IntegrationPlugin particleIntegrationPlugin;
  private final BillboardPlugin particleBillboardPlugin;

  private final List<ParticlePlugin> particlePlugins;
  private final List<ParticlePlugin> initializableParticlePlugins;
  private final List<ParticlePlugin> initializerParticlePlugins;
  private final List<ParticlePlugin> updateParticlePlugins;
  private final List<ForcePlugin> particleForcePlugins;
  private final List<ForcePlugin> particleRotationalForcePlugins;

  private final float particleSoftness;
  private final boolean useVelocityStretching;
  private final float velocityStretchFactor;

  /**
   * If true, this emitter will continue to emit new particles; if false, this
   * emitter will still update it's existing particles, but will not spawn any
   * new ones.
   */
  private boolean active = true;

  /**
   * If true, this emitter will not spawn any new particles or update any
   * existing particles
   */
  private boolean dead = false;

  protected ParticleEmitter(
      final EmitterDelay newEmitterDelay,
      final ParticlePool newParticlePool,
      final ParticleRenderer newParticleRenderer,
      final EmissionPlugin newEmitterEmissionPlugin,
      final TimestepPlugin newEmitterUpdatePlugin,
      final IntegrationPlugin newParticleIntegrationPlugin,
      final BillboardPlugin newParticleBillboardPlugin,
      final Collection<ParticlePlugin> newParticlePluginList,
      final Collection<ForcePlugin> newParticleForcePluginList,
      final Collection<ForcePlugin> newParticleRotationalForcePluginList,
      final float newParticleSoftness,
      final float newVelocityStretchFactor
  ) {
    emitterDelay = newEmitterDelay;
    particlePool = newParticlePool;
    particleRenderer = newParticleRenderer;
    particleEmissionPlugin = newEmitterEmissionPlugin;
    particleTimestepPlugin = newEmitterUpdatePlugin;
    particleIntegrationPlugin = newParticleIntegrationPlugin;
    particleBillboardPlugin = newParticleBillboardPlugin;

    particlePlugins = Collections.unmodifiableList(new ArrayList<>(newParticlePluginList));
    particleForcePlugins = Collections.unmodifiableList(new ArrayList<ForcePlugin>(newParticleForcePluginList));
    particleRotationalForcePlugins = Collections.unmodifiableList(new ArrayList<ForcePlugin>(newParticleRotationalForcePluginList));

    particleSoftness = newParticleSoftness;
    velocityStretchFactor = newVelocityStretchFactor;
    useVelocityStretching = !FastMath.compareFloat(newVelocityStretchFactor, 0f);

    particleRenderer.initialize(this);
    particleIntegrationPlugin.initialize(this, particlePool.getParticles());

    List<ParticlePlugin> initializableList = new ArrayList<>();
    List<ParticlePlugin> initializerList = new ArrayList<>();
    List<ParticlePlugin> updateList = new ArrayList<>();

    for (int i = 0; i < particlePlugins.size(); i++) {
      ParticlePlugin particlePlugin = particlePlugins.get(i);
      byte pluginCode = particlePlugin.getPluginCode();
      if ((pluginCode & ParticlePlugin.PluginCode.INITIALIZE_PLUGIN) != 0) {
        initializableList.add(particlePlugin);
      }
      if ((pluginCode & ParticlePlugin.PluginCode.INITIALIZE) != 0) {
        initializerList.add(particlePlugin);
      }
      if ((pluginCode & ParticlePlugin.PluginCode.UPDATE) != 0) {
        updateList.add(particlePlugin);
      }
    }

    if (!initializableList.isEmpty()) {
      for (int i = 0; i < initializableList.size(); i++) {
        initializableList.get(i).initializePlugin(this);
      }
    }

    initializableParticlePlugins = Collections.unmodifiableList(initializableList);
    initializerParticlePlugins = Collections.unmodifiableList(initializerList);
    updateParticlePlugins = Collections.unmodifiableList(updateList);
  }

  public void update(int tpf) {
    if (!dead && !emitterDelay.update(tpf)) {
      particleTimestepPlugin.update(this, tpf);
    }
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean isDead() {
    return dead;
  }

  public void setDead(boolean dead) {
    this.dead = dead;
  }

  @SuppressWarnings("unchecked")
  public <T extends ParticleRenderer> T getParticleRenderer() {
    return (T) particleRenderer;
  }

  public ParticlePool getParticlePool() {
    return particlePool;
  }

  public Particle[] getParticles() {
    return particlePool.getParticles();
  }

  public int getParticleCount() {
    return particlePool.getParticleCount();
  }

  public float getParticleSoftness() {
    return particleSoftness;
  }

  public float getVelocityStretchFactor() {
    return velocityStretchFactor;
  }

  public boolean useVelocityStretching() {
    return useVelocityStretching;
  }

  public EmissionPlugin getParticleEmissionPlugin() {
    return particleEmissionPlugin;
  }

  public List<ForcePlugin> getParticleForcePlugins() {
    return particleForcePlugins;
  }

  public List<ForcePlugin> getParticleRotationalForcePlugins() {
    return particleRotationalForcePlugins;
  }

  public BillboardPlugin getParticleBillboardPlugin() {
    return particleBillboardPlugin;
  }

  public IntegrationPlugin getParticleIntegrationPlugin() {
    return particleIntegrationPlugin;
  }

  public ParticleRenderer getParticleRendererPlugin() {
    return particleRenderer;
  }

  public TimestepPlugin getParticleTimestepPlugin() {
    return particleTimestepPlugin;
  }

  public List<ParticlePlugin> getParticlePlugins() {
    return particlePlugins;
  }

  public List<ParticlePlugin> getInitializableParticlePlugins() {
    return initializableParticlePlugins;
  }

  public List<ParticlePlugin> getInitializerParticlePlugins() {
    return initializerParticlePlugins;
  }

  public List<ParticlePlugin> getUpdateParticlePlugins() {
    return updateParticlePlugins;
  }


}
