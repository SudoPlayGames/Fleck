package com.sudoplay.fleck.core.plugin.flow;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.core.PerlinNoise;
import com.sudoplay.fleck.integration.spi.plugin.FlowFieldBase;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;
import com.sudoplay.math.Vector3f;

public class FlowPositionPlugin
    extends FlowFieldBase
    implements ParticlePlugin {

  protected Vector3f store;

  public FlowPositionPlugin(
      float newScale,
      Vector3f newInfluence,
      PerlinNoise newNoise,
      long newSeed
  ) {

    super(newScale, newInfluence, newNoise, newSeed);
    this.store = new Vector3f();
  }

  @Override
  public byte getPluginCode() {

    return PluginCode.UPDATE;
  }

  @Override
  public void initializePlugin(ParticleEmitter particleEmitter) {
    //
  }

  @Override
  public void initializeParticle(Particle p) {
    //
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {

    p.position.addLocal(calculate(p.position, t, this.store).multLocal(dt));
  }

}
