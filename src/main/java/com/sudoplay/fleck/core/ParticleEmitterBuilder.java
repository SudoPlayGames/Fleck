package com.sudoplay.fleck.core;

import com.sudoplay.fleck.core.plugin.particle.*;
import com.sudoplay.fleck.integration.spi.*;
import com.sudoplay.fleck.integration.spi.plugin.*;
import com.sudoplay.math.ColorRGBA;
import com.sudoplay.math.FastMath;
import com.sudoplay.math.Vector3f;

import java.util.*;

public class ParticleEmitterBuilder {

  private float softness;
  private float velocityStretchFactor;

  private EmitterDelay emitterDelay;
  private ParticlePool particlePool;
  private EmissionPlugin emissionPlugin;
  private TimestepPlugin timestepPlugin;
  private IntegrationPlugin integrationPlugin;
  private BillboardPlugin billboardPlugin = com.sudoplay.fleck.core.plugin.BillboardPlugin.CAMERA;

  private ParticlePlugin alphaPlugin;
  private ParticlePlugin collisionPlugin;
  private ParticlePlugin colorPlugin = Color.WHITE;
  private ParticlePlugin lifePlugin = Life.ONE_THOUSAND;
  private ParticlePlugin massPlugin = Mass.ONE;
  private ParticlePlugin positionPlugin = PositionPoint.ORIGIN;
  private ParticlePlugin rotationPlugin = Rotation.ZERO;
  private ParticlePlugin sizePlugin = Size.ONE;
  private ParticlePlugin spriteIndexPlugin;
  private ParticlePlugin velocityPlugin;

  private List<ForcePlugin> forcePlugins = new ArrayList<ForcePlugin>();
  private List<ForcePlugin> rotationalForcePlugins = new ArrayList<ForcePlugin>();

  private final Map<String, ParticleEmitterBuilder.BindingPostProcess> bindingPostProcessMap;

  interface BindingPostProcess {

    void postProcess(Map<String, TransformBinding> bindingMap);

  }

  public ParticleEmitterBuilder() {

    bindingPostProcessMap = new HashMap<>();
  }

  public Map<String, ParticleEmitterBuilder.BindingPostProcess> getBindingPostProcessMap() {

    return bindingPostProcessMap;
  }

  public ParticleEmitterBuilder setVelocityStretchFactor(
      float velocityStretchFactor
  ) {

    this.velocityStretchFactor = velocityStretchFactor;
    return this;
  }

  public ParticleEmitterBuilder setTimestepPlugin(
      TimestepPlugin timestepPlugin
  ) {

    this.timestepPlugin = timestepPlugin;
    return this;
  }

  public ParticleEmitterBuilder setIntegrationPlugin(
      IntegrationPlugin integrationPlugin
  ) {

    this.integrationPlugin = integrationPlugin;
    return this;
  }

  public ParticleEmitterBuilder setDelay(float delay) {

    this.emitterDelay = new com.sudoplay.fleck.core.plugin.EmitterDelay(delay);
    return this;
  }

  public ParticleEmitterBuilder setSoftness(
      float softness
  ) {

    this.softness = softness;
    return this;
  }

  public ParticleEmitterBuilder setParticlePool(
      int particleCount
  ) {

    this.particlePool = new DefaultParticlePool(particleCount);
    return this;
  }

  public ParticleEmitterBuilder setParticlePool(
      ParticlePool particlePool
  ) {

    this.particlePool = particlePool;
    return this;
  }

  public ParticleEmitterBuilder setEmissionPlugin(
      EmissionPlugin emissionPlugin
  ) {

    this.emissionPlugin = emissionPlugin;
    return this;
  }

  public ParticleEmitterBuilder setBillboardPlugin(
      BillboardPlugin billboardPlugin
  ) {

    this.billboardPlugin = billboardPlugin;
    return this;
  }

  public ParticleEmitterBuilder addForcePlugin(
      ForcePlugin plugin
  ) {

    forcePlugins.add(plugin);
    return this;
  }

  public ParticleEmitterBuilder addRotationalForcePlugin(
      ForcePlugin plugin
  ) {

    rotationalForcePlugins.add(plugin);
    return this;
  }

  public ParticleEmitterBuilder setAlphaPlugin(
      ParticlePlugin alphaPlugin
  ) {

    this.alphaPlugin = alphaPlugin;
    return this;
  }

  public ParticleEmitterBuilder setCollisionPlugin(
      ParticlePlugin collisionPlugin
  ) {

    this.collisionPlugin = collisionPlugin;
    return this;
  }

  public ParticleEmitterBuilder setColorPlugin(
      ParticlePlugin colorPlugin
  ) {

    this.colorPlugin = colorPlugin;
    return this;
  }

  public ParticleEmitterBuilder setLifePlugin(
      ParticlePlugin lifePlugin
  ) {

    this.lifePlugin = lifePlugin;
    return this;
  }

  public ParticleEmitterBuilder setMassPlugin(
      ParticlePlugin massPlugin
  ) {

    this.massPlugin = massPlugin;
    return this;
  }

  public ParticleEmitterBuilder setPositionPlugin(
      ParticlePlugin positionPlugin
  ) {

    this.positionPlugin = positionPlugin;
    return this;
  }

  public ParticleEmitterBuilder setRotationPlugin(
      ParticlePlugin rotationPlugin
  ) {

    this.rotationPlugin = rotationPlugin;
    return this;
  }

  public ParticleEmitterBuilder setSizePlugin(
      ParticlePlugin sizePlugin
  ) {

    this.sizePlugin = sizePlugin;
    return this;
  }

  public ParticleEmitterBuilder setSpriteIndexPlugin(
      ParticlePlugin spriteIndexPlugin
  ) {

    this.spriteIndexPlugin = spriteIndexPlugin;
    return this;
  }

  public ParticleEmitterBuilder setVelocityPlugin(
      ParticlePlugin velocityPlugin
  ) {

    this.velocityPlugin = velocityPlugin;
    return this;
  }

  /*
   * ==========================================================================
   * = Emission
   * ==========================================================================
   */

  public ParticleEmitterBuilder setEmissionContinuous(
      float particlesPerSecond
  ) {

    emissionPlugin = new com.sudoplay.fleck.core.plugin.EmissionContinuousPlugin(particlesPerSecond);
    return this;
  }

  public ParticleEmitterBuilder setEmissionSingleBurst(
      int particlesPerBurst
  ) {

    emissionPlugin = new com.sudoplay.fleck.core.plugin.EmissionBurstPlugin(particlesPerBurst);
    return this;
  }

  public ParticleEmitterBuilder setEmissionBurst(
      int particlesPerBurst,
      int bursts,
      float delay
  ) {

    emissionPlugin = new com.sudoplay.fleck.core.plugin.EmissionBurstPlugin(particlesPerBurst, bursts, delay);
    return this;
  }

  public ParticleEmitterBuilder setEmissionBurstContinuous(
      int particlesPerBurst
  ) {

    emissionPlugin = new com.sudoplay.fleck.core.plugin.EmissionBurstContinuousPlugin(particlesPerBurst);
    return this;
  }

  public ParticleEmitterBuilder setEmissionBurstContinuous(
      int particlesPerBurst,
      float delay
  ) {

    emissionPlugin = new com.sudoplay.fleck.core.plugin.EmissionBurstContinuousPlugin(particlesPerBurst, delay);
    return this;
  }

  public ParticleEmitterBuilder setEmissionBurstContinuousRandom(
      int particlesMin,
      int particlesMax,
      float delayMin,
      float delayMax
  ) {

    emissionPlugin = new com.sudoplay.fleck.core.plugin.EmissionBurstContinuousRandom(
        particlesMin,
        particlesMax,
        delayMin,
        delayMax
    );
    return this;
  }

  public ParticleEmitterBuilder setEmissionContinuousLimited(
      float particlesPerSecond,
      float durationSeconds
  ) {

    emissionPlugin = new com.sudoplay.fleck.core.plugin.EmissionContinuousLimitedPlugin(
        particlesPerSecond,
        durationSeconds
    );
    return this;
  }

  /*
   * ==========================================================================
   * = Billboard
   * ==========================================================================
   */

  public ParticleEmitterBuilder setBillboardCamera() {

    billboardPlugin = com.sudoplay.fleck.core.plugin.BillboardPlugin.CAMERA;
    return this;
  }

  public ParticleEmitterBuilder setBillboardNone() {

    billboardPlugin = com.sudoplay.fleck.core.plugin.BillboardPlugin.NONE;
    return this;
  }

  public ParticleEmitterBuilder setBillboardUnitX() {

    billboardPlugin = com.sudoplay.fleck.core.plugin.BillboardPlugin.UNIT_X;
    return this;
  }

  public ParticleEmitterBuilder setBillboardUnitY() {

    billboardPlugin = com.sudoplay.fleck.core.plugin.BillboardPlugin.UNIT_Y;
    return this;
  }

  public ParticleEmitterBuilder setBillboardUnitZ() {

    billboardPlugin = com.sudoplay.fleck.core.plugin.BillboardPlugin.UNIT_Z;
    return this;
  }

  public ParticleEmitterBuilder setBillboardVelocity() {

    billboardPlugin = com.sudoplay.fleck.core.plugin.BillboardPlugin.VELOCITY;
    return this;
  }

  public ParticleEmitterBuilder setBillboardVelocityZUp() {

    billboardPlugin = com.sudoplay.fleck.core.plugin.BillboardPlugin.VELOCITY_Z_UP;
    return this;
  }

  public ParticleEmitterBuilder setBillboardVelocityZUpYLeft() {

    billboardPlugin = com.sudoplay.fleck.core.plugin.BillboardPlugin.VELOCITY_Z_UP_Y_LEFT;
    return this;
  }

  /*
   * ==========================================================================
   * = Life
   * ==========================================================================
   */

  public ParticleEmitterBuilder setLifeEternal() {

    lifePlugin = com.sudoplay.fleck.core.plugin.particle.LifeEternal.INSTANCE;
    return this;
  }

  public ParticleEmitterBuilder setLife(
      float life
  ) {

    lifePlugin = new com.sudoplay.fleck.core.plugin.particle.Life(life);
    return this;
  }

  public ParticleEmitterBuilder setLifeRandom(
      float min,
      float max
  ) {

    lifePlugin = new com.sudoplay.fleck.core.plugin.particle.LifeRandom(min, max);
    return this;
  }

  /*
   * ==========================================================================
   * = Mass
   * ==========================================================================
   */

  public ParticleEmitterBuilder setMass(
      float mass
  ) {

    massPlugin = new com.sudoplay.fleck.core.plugin.particle.Mass(mass);
    return this;
  }

  public ParticleEmitterBuilder setMassRandom(
      float min,
      float max
  ) {

    massPlugin = new com.sudoplay.fleck.core.plugin.particle.MassRandom(min, max);
    return this;
  }

  /*
   * ==========================================================================
   * = Size
   * ==========================================================================
   */

  public ParticleEmitterBuilder setSize(
      float size
  ) {

    sizePlugin = new com.sudoplay.fleck.core.plugin.particle.Size(size);
    return this;
  }

  public ParticleEmitterBuilder setSize(
      Vector3f size
  ) {

    sizePlugin = new com.sudoplay.fleck.core.plugin.particle.Size(size);
    return this;
  }

  public ParticleEmitterBuilder setSize(
      float x,
      float y,
      float z
  ) {

    sizePlugin = new com.sudoplay.fleck.core.plugin.particle.Size(x, y, z);
    return this;
  }

  public ParticleEmitterBuilder setSizeInterpolate(
      float startSize,
      float endSize
  ) {

    sizePlugin = new com.sudoplay.fleck.core.plugin.particle.SizeInterpolate(
        new Vector3f(startSize, startSize, startSize),
        new Vector3f(endSize, endSize, endSize)
    );
    return this;
  }

  public ParticleEmitterBuilder setSizeInterpolate(
      float startSize,
      float endSize,
      Interpolator interpolator
  ) {

    sizePlugin = new com.sudoplay.fleck.core.plugin.particle.SizeInterpolate(
        new Vector3f(startSize, startSize, startSize),
        new Vector3f(endSize, endSize, endSize),
        interpolator
    );
    return this;
  }

  public ParticleEmitterBuilder setSizeInterpolate(
      Vector3f startSize,
      Vector3f endSize
  ) {

    sizePlugin = new com.sudoplay.fleck.core.plugin.particle.SizeInterpolate(startSize, endSize);
    return this;
  }

  public ParticleEmitterBuilder setSizeInterpolate(
      Vector3f startSize,
      Vector3f endSize,
      Interpolator interpolator
  ) {

    sizePlugin = new com.sudoplay.fleck.core.plugin.particle.SizeInterpolate(startSize, endSize, interpolator);
    return this;
  }

  /*
   * ==========================================================================
   * = Sprite Index
   * ==========================================================================
   */

  public ParticleEmitterBuilder setSpriteIndex(
      int index
  ) {

    spriteIndexPlugin = new com.sudoplay.fleck.core.plugin.particle.SpriteIndex(index);
    return this;
  }

  public ParticleEmitterBuilder setSpriteIndexRandom(
      int min,
      int max
  ) {

    spriteIndexPlugin = new com.sudoplay.fleck.core.plugin.particle.SpriteIndexRandom(min, max);
    return this;
  }

  /*
   * ==========================================================================
   * = Position
   * ==========================================================================
   */

  public ParticleEmitterBuilder setPositionPoint(
      Vector3f position
  ) {

    positionPlugin = new com.sudoplay.fleck.core.plugin.particle.PositionPoint(position);
    return this;
  }

  public ParticleEmitterBuilder setPositionPoint(
      TransformBinding positionBinding
  ) {

    positionPlugin = new com.sudoplay.fleck.core.plugin.particle.PositionPoint(positionBinding);
    return this;
  }

  public ParticleEmitterBuilder setPositionLine(
      Vector3f start,
      Vector3f end
  ) {

    positionPlugin = new com.sudoplay.fleck.core.plugin.particle.PositionLineRandom(start, end);
    return this;
  }

  public ParticleEmitterBuilder setPositionLine(
      TransformBinding start,
      Vector3f end
  ) {

    positionPlugin = new com.sudoplay.fleck.core.plugin.particle.PositionLineRandom(start, end);
    return this;
  }

  public ParticleEmitterBuilder setPositionLine(
      Vector3f start,
      TransformBinding end
  ) {

    positionPlugin = new com.sudoplay.fleck.core.plugin.particle.PositionLineRandom(start, end);
    return this;
  }

  public ParticleEmitterBuilder setPositionLine(
      TransformBinding start,
      TransformBinding end
  ) {

    positionPlugin = new com.sudoplay.fleck.core.plugin.particle.PositionLineRandom(start, end);
    return this;
  }

  public ParticleEmitterBuilder setPositionBox(
      Vector3f size
  ) {

    positionPlugin = new com.sudoplay.fleck.core.plugin.particle.PositionBoxRandom(size);
    return this;
  }

  public ParticleEmitterBuilder setPositionBox(
      Vector3f position,
      Vector3f size
  ) {

    positionPlugin = new com.sudoplay.fleck.core.plugin.particle.PositionBoxRandom(position, size);
    return this;
  }

  public ParticleEmitterBuilder setPositionBox(
      TransformBinding positionBinding,
      Vector3f size
  ) {

    positionPlugin = new com.sudoplay.fleck.core.plugin.particle.PositionBoxRandom(positionBinding, size);
    return this;
  }

  public ParticleEmitterBuilder setPositionCylinder(
      float minRadius,
      float maxRadius,
      float height
  ) {

    positionPlugin = new com.sudoplay.fleck.core.plugin.particle.PositionCylinderRandom(minRadius, maxRadius, height);
    return this;
  }

  public ParticleEmitterBuilder setPositionCylinder(
      Vector3f position,
      float minRadius,
      float maxRadius,
      float height
  ) {

    positionPlugin = new com.sudoplay.fleck.core.plugin.particle.PositionCylinderRandom(
        position,
        minRadius,
        maxRadius,
        height
    );
    return this;
  }

  public ParticleEmitterBuilder setPositionCylinder(
      TransformBinding positionBinding,
      float minRadius,
      float maxRadius,
      float height
  ) {

    positionPlugin = new com.sudoplay.fleck.core.plugin.particle.PositionCylinderRandom(
        positionBinding,
        minRadius,
        maxRadius,
        height
    );
    return this;
  }

  public ParticleEmitterBuilder setPositionSphere(
      float minRadius,
      float maxRadius
  ) {

    positionPlugin = new com.sudoplay.fleck.core.plugin.particle.PositionSphereRandom(minRadius, maxRadius);
    return this;
  }

  public ParticleEmitterBuilder setPositionSphere(
      Vector3f position,
      float minRadius,
      float maxRadius
  ) {

    positionPlugin = new com.sudoplay.fleck.core.plugin.particle.PositionSphereRandom(position, minRadius, maxRadius);
    return this;
  }

  public ParticleEmitterBuilder setPositionSphere(
      TransformBinding positionBinding,
      float minRadius,
      float maxRadius
  ) {

    positionPlugin = new com.sudoplay.fleck.core.plugin.particle.PositionSphereRandom(
        positionBinding,
        minRadius,
        maxRadius
    );
    return this;
  }

  /*
   * ==========================================================================
   * = Velocity
   * ==========================================================================
   */

  public ParticleEmitterBuilder setVelocity(
      Vector3f direction,
      float magnitude
  ) {

    velocityPlugin = new com.sudoplay.fleck.core.plugin.particle.VelocityExact(direction, magnitude);
    return this;
  }

  public ParticleEmitterBuilder setVelocityRandom(
      Vector3f direction,
      float minVelocity,
      float maxVelocity
  ) {

    velocityPlugin = new com.sudoplay.fleck.core.plugin.particle.VelocityRandom(direction, minVelocity, maxVelocity);
    return this;
  }

  public ParticleEmitterBuilder setVelocity(
      TransformBinding positionBinding,
      float magnitude
  ) {

    velocityPlugin = new com.sudoplay.fleck.core.plugin.particle.VelocityPointBinding(positionBinding, magnitude);
    return this;
  }

  public ParticleEmitterBuilder setVelocity(
      TransformBinding positionBinding,
      float minVelocity,
      float maxVelocity
  ) {

    velocityPlugin = new com.sudoplay.fleck.core.plugin.particle.VelocityPointBindingRandom(
        positionBinding,
        minVelocity,
        maxVelocity
    );
    return this;
  }

  public ParticleEmitterBuilder setVelocitySphere(
      float minVelocity,
      float maxVelocity,
      float polarAngle,
      float polarAngleVariance,
      float azimuthAngle,
      float azimuthAngleVariance
  ) {

    velocityPlugin = new com.sudoplay.fleck.core.plugin.particle.VelocitySphereRandom(
        minVelocity,
        maxVelocity,
        polarAngle,
        polarAngleVariance,
        azimuthAngle,
        azimuthAngleVariance
    );
    return this;
  }

  public ParticleEmitterBuilder setVelocitySphere(
      float minVelocity,
      float maxVelocity
  ) {

    velocityPlugin = new com.sudoplay.fleck.core.plugin.particle.VelocitySphereRandom(minVelocity, maxVelocity);
    return this;
  }

  /*
   * ==========================================================================
   * = Rotation
   * ==========================================================================
   */

  public ParticleEmitterBuilder setRotation(Vector3f axisAngles) {

    rotationPlugin = new com.sudoplay.fleck.core.plugin.particle.Rotation(axisAngles);
    return this;
  }

  public ParticleEmitterBuilder setRotation(
      float x,
      float y,
      float z
  ) {

    rotationPlugin = new com.sudoplay.fleck.core.plugin.particle.Rotation(x, y, z);
    return this;
  }

  public ParticleEmitterBuilder setRotationRandom(
      boolean x,
      boolean y,
      boolean z
  ) {

    rotationPlugin = new com.sudoplay.fleck.core.plugin.particle.RotationRandom(x, y, z);
    return this;
  }

   /*
   * ==========================================================================
   * = Alpha
   * ==========================================================================
   */

  public ParticleEmitterBuilder setAlpha(
      float alpha
  ) {

    alphaPlugin = new com.sudoplay.fleck.core.plugin.particle.Alpha(alpha);
    return this;
  }

  public ParticleEmitterBuilder setAlphaInterpolate(
      float startAlpha,
      float endAlpha
  ) {

    alphaPlugin = new com.sudoplay.fleck.core.plugin.particle.AlphaInterpolate(startAlpha, endAlpha);
    return this;
  }

  public ParticleEmitterBuilder setAlphaInterpolate(
      float startAlpha,
      float endAlpha,
      Interpolator interpolator
  ) {

    alphaPlugin = new com.sudoplay.fleck.core.plugin.particle.AlphaInterpolate(startAlpha, endAlpha, interpolator);
    return this;
  }

  /*
   * ==========================================================================
   * = Color
   * ==========================================================================
   */

  public ParticleEmitterBuilder setColor(
      ColorRGBA color
  ) {

    colorPlugin = new com.sudoplay.fleck.core.plugin.particle.Color(color);
    return this;
  }

  public ParticleEmitterBuilder setColorInterpolate(
      ColorRGBA startColor,
      ColorRGBA endColor
  ) {

    colorPlugin = new com.sudoplay.fleck.core.plugin.particle.ColorInterpolate(startColor, endColor);
    return this;
  }

  public ParticleEmitterBuilder setColorInterpolate(
      ColorRGBA startColor,
      ColorRGBA endColor,
      Interpolator interpolator
  ) {

    colorPlugin = new com.sudoplay.fleck.core.plugin.particle.ColorInterpolate(
        startColor,
        endColor,
        com.sudoplay.fleck.core.plugin.ColorspaceCIELABPlugin.INSTANCE,
        interpolator
    );
    return this;
  }

  /*
   * ==========================================================================
   * = Creator
   * ==========================================================================
   */

  public ParticleEmitter create(ParticleRenderer particleRenderer) {

    softness = FastMath.clamp(softness, 0, 1);

    if (emitterDelay == null) {
      emitterDelay = com.sudoplay.fleck.core.plugin.EmitterDelay.ZERO;
    }

    if (particlePool == null) {
      particlePool = new DefaultParticlePool(10);
    }

    if (emissionPlugin == null) {
      emissionPlugin = new com.sudoplay.fleck.core.plugin.EmissionContinuousPlugin(1);
    }

    if (timestepPlugin == null) {
      timestepPlugin = new com.sudoplay.fleck.core.plugin.TimestepGafferPlugin();
    }

    if (integrationPlugin == null) {
      integrationPlugin = new com.sudoplay.fleck.core.plugin.IntegrationVelocityVerletOrder2Plugin();
    }

    List<ParticlePlugin> particlePlugins = new LinkedList<>();
    addPluginToList(alphaPlugin, particlePlugins);
    addPluginToList(collisionPlugin, particlePlugins);
    addPluginToList(colorPlugin, particlePlugins);
    addPluginToList(lifePlugin, particlePlugins);
    addPluginToList(massPlugin, particlePlugins);
    addPluginToList(positionPlugin, particlePlugins);
    addPluginToList(rotationPlugin, particlePlugins);
    addPluginToList(sizePlugin, particlePlugins);
    addPluginToList(spriteIndexPlugin, particlePlugins);
    addPluginToList(velocityPlugin, particlePlugins);

    return new ParticleEmitter(
        emitterDelay,
        particlePool,
        particleRenderer,
        emissionPlugin,
        timestepPlugin,
        integrationPlugin,
        billboardPlugin,
        particlePlugins,
        forcePlugins,
        rotationalForcePlugins,
        softness,
        velocityStretchFactor
    );

  }

  private void addPluginToList(ParticlePlugin plugin, List<ParticlePlugin> list) {

    if (plugin != null) {
      list.add(plugin);
    }
  }

}
