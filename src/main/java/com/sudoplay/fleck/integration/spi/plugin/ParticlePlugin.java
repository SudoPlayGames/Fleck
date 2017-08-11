package com.sudoplay.fleck.integration.spi.plugin;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;

public interface ParticlePlugin {

  class PluginCode {
    public static final byte INITIALIZE_PLUGIN = 0x01;
    public static final byte INITIALIZE = 0x02;
    public static final byte UPDATE = 0x04;
  }

  byte getPluginCode();

  void initializePlugin(ParticleEmitter particleEmitter);

  void initializeParticle(Particle p);

  void updateParticle(Particle p, float particleLifePercentage, float t, float dt);

}
