package com.sudoplay.fleck.core.plugin.particle;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.TransformBinding;
import com.sudoplay.fleck.integration.spi.plugin.ParticlePlugin;

public class VelocityPointBinding
    implements ParticlePlugin {

  protected TransformBinding positionBinding;
  protected float magnitude;

  public VelocityPointBinding(TransformBinding newPositionBinding, float newMagnitude) {

    this.positionBinding = newPositionBinding;
    this.magnitude = newMagnitude;
  }

  @Override
  public void initializePlugin(ParticleEmitter particleEmitter) {
    //
  }

  @Override
  public void initializeParticle(Particle p) {

    this.positionBinding.getPosition(p.velocity).subtractLocal(p.position).normalizeLocal().multLocal(this.magnitude);
  }

  @Override
  public byte getPluginCode() {

    return PluginCode.INITIALIZE;
  }

  @Override
  public void updateParticle(Particle p, float particleLifePercentage, float t, float dt) {
    //
  }

}
