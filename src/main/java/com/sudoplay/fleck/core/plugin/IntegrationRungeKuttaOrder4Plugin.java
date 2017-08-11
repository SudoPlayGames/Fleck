package com.sudoplay.fleck.core.plugin;

import com.sudoplay.fleck.core.Particle;
import com.sudoplay.fleck.core.ParticleEmitter;
import com.sudoplay.fleck.integration.spi.plugin.ForcePlugin;
import com.sudoplay.fleck.integration.spi.plugin.IntegrationPlugin;
import com.sudoplay.fleck.core.util.ForceUtil;
import com.sudoplay.math.FastMath;
import com.sudoplay.math.Quaternion;
import com.sudoplay.math.Vector3f;

import java.util.List;

/**
 * http://gafferongames.com/game-physics/integration-basics/
 * <p>
 * The "RK4" integrator, eg. Runge Kutta order 4, so named for the two german
 * mathematicians that discovered it: Carl Runge and Martin Kutta, is one of the
 * most accurate general purpose integration techniques available.
 *
 * @author Jason Taylor
 */
public class IntegrationRungeKuttaOrder4Plugin
    implements IntegrationPlugin {

  protected ParticleEmitter particleEmitter;

  protected static final float ONE_SIXTH = 1.0f / 6.0f;

  protected Vector3f positionState = new Vector3f();
  protected Vector3f velocityState = new Vector3f();

  protected Derivative a = new Derivative();
  protected Derivative b = new Derivative();
  protected Derivative c = new Derivative();
  protected Derivative d = new Derivative();

  protected Derivative summedDerivative = new Derivative();

  @Override
  public void initialize(ParticleEmitter newParticleEmitter, Particle[] particles) {

    this.particleEmitter = newParticleEmitter;
  }

  @Override
  public void integrate(Particle particle, float t, float dt) {

    integratePosition(
        this.particleEmitter.getParticleForcePlugins(),
        particle.position,
        particle.velocity,
        particle.mass,
        t,
        dt
    );
    integratePosition(
        this.particleEmitter.getParticleRotationalForcePlugins(),
        particle.rotation,
        particle.angularVelocity,
        particle.mass,
        t,
        dt
    );
  }

  public void integratePosition(
      List<ForcePlugin> forcePlugins,
      Vector3f particlePosition,
      Vector3f particleVelocity,
      float particleMass,
      float t,
      float dt
  ) {

    this.evaluatePosition(forcePlugins, particlePosition, particleVelocity, particleMass, t, dt, Derivative.ZERO, this.a);
    this.evaluatePosition(forcePlugins, particlePosition, particleVelocity, particleMass, t, dt * 0.5f, this.a, this.b);
    this.evaluatePosition(forcePlugins, particlePosition, particleVelocity, particleMass, t, dt * 0.5f, this.b, this.c);
    this.evaluatePosition(forcePlugins, particlePosition, particleVelocity, particleMass, t, dt, this.c, this.d);

    this.summedDerivative.dPosition.x = ONE_SIXTH * (this.a.dPosition.x + 2.0f * (this.b.dPosition.x + this.c.dPosition.x) + this.d.dPosition.x);
    this.summedDerivative.dPosition.y = ONE_SIXTH * (this.a.dPosition.y + 2.0f * (this.b.dPosition.y + this.c.dPosition.y) + this.d.dPosition.y);
    this.summedDerivative.dPosition.z = ONE_SIXTH * (this.a.dPosition.z + 2.0f * (this.b.dPosition.z + this.c.dPosition.z) + this.d.dPosition.z);

    this.summedDerivative.dVelocity.x = ONE_SIXTH * (this.a.dVelocity.x + 2.0f * (this.b.dVelocity.x + this.c.dVelocity.x) + this.d.dVelocity.x);
    this.summedDerivative.dVelocity.y = ONE_SIXTH * (this.a.dVelocity.y + 2.0f * (this.b.dVelocity.y + this.c.dVelocity.y) + this.d.dVelocity.y);
    this.summedDerivative.dVelocity.z = ONE_SIXTH * (this.a.dVelocity.z + 2.0f * (this.b.dVelocity.z + this.c.dVelocity.z) + this.d.dVelocity.z);

    particlePosition.addLocal(this.summedDerivative.dPosition.multLocal(dt));
    particleVelocity.addLocal(this.summedDerivative.dVelocity.multLocal(dt));
  }

  /**
   * This method takes the current state of the object (its position and
   * velocity) and advances it ahead dt seconds using an Euler Integration step
   * with the derivatives that were passed in (velocity and acceleration). Once
   * this new position and velocity are calculated, it calculates new
   * derivatives at this point in time using the integrated state. These
   * derivatives will be different to the derivatives that were initially passed
   * in to the method if the derivatives are not constant over the timestep.
   */
  protected Derivative evaluatePosition(
      List<ForcePlugin> forcePlugins,
      Vector3f particlePosition,
      Vector3f particleVelocity,
      float particleMass,
      float t,
      float dt,
      Derivative derivative,
      Derivative store
  ) {

    this.positionState.set(derivative.dPosition).multLocal(dt).addLocal(particlePosition);
    this.velocityState.set(derivative.dVelocity).multLocal(dt).addLocal(particleVelocity);
    store.dPosition.set(this.velocityState);
    ForceUtil.accumulateAcceleration(
        particleMass,
        particlePosition,
        particleVelocity,
        t + dt,
        forcePlugins,
        store.dVelocity.set(Vector3f.ZERO)
    );
    return store;
  }

  @SuppressWarnings("unused")
  protected Quaternion integrateQuaternion(Quaternion q, Vector3f angularVelocity, float dt, Quaternion store) {

    Vector3f theta = new Vector3f(angularVelocity).multLocal(dt * 0.5f);
    Quaternion tempQuat = new Quaternion();
    float w;
    float thetaMagSq = theta.lengthSquared();
    float s;
    if (thetaMagSq * thetaMagSq / 24.0f < FastMath.FLT_EPSILON) {
      w = 1.0f - thetaMagSq / 2.0f;
      s = 1.0f - thetaMagSq / 6.0f;
    } else {
      float thetaMag = FastMath.sqrt(thetaMagSq);
      w = FastMath.cosLUT(thetaMag);
      s = FastMath.sinLUT(thetaMag) / thetaMag;
    }
    tempQuat.set(theta.x * s, theta.y * s, theta.z * s, w);
    q.mult(tempQuat, store);
    return store;
  }

  protected static class Derivative {

    public static final Derivative ZERO = new Derivative();
    final Vector3f dPosition = new Vector3f(); // dx/dt = velocity
    final Vector3f dVelocity = new Vector3f(); // dv/dt = acceleration
  }

  @Override
  public void onPostParticleInitialize(Particle particle) {
    //
  }

}
