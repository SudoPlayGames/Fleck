package com.sudoplay.fleck.integration.spi.plugin;

import com.sudoplay.fleck.core.ParticleEmitter;

public interface TimestepPlugin {

  void update(ParticleEmitter emitter, float tpf);

}
