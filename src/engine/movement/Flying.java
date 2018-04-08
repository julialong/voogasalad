package engine.movement;

import engine.physics.Kinematics;
import engine.physics.Physics;

/**
 * Movement implementation for entities that fly and are exempt from gravity and friction
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class Flying implements Movement{

	@Override
	public Kinematics overridePosition(Kinematics k, double x, double y) {
		k.setX(x);
        k.setY(y);
        return k;
	}

	@Override
	public Kinematics setVelocityX(Kinematics k, double velocity) {
        k.setXVelocity(velocity);
        return k;
	}

	@Override
	public Kinematics setVelocityY(Kinematics k, double velocity) {
		k.setYVelocity(velocity);
        return k;
	}

	@Override
	public Kinematics setAccelerationX(Kinematics k, double accel) {
		k.setXAcceleration(accel);
		return k;
	}

	@Override
	public Kinematics setAccelerationY(Kinematics k, double accel) {
		k.setYAcceleration(accel);
		return k;
	}
    
    @Override
    public Kinematics update(Kinematics k, double xVelocityLimit, double yVelocityLimit){
        Physics p = new Physics();
    	Kinematics kFinal = p.applyPhysics(k, false);
        if ((kFinal.getXVelocity() > xVelocityLimit) || (kFinal.getXVelocity() < -xVelocityLimit)){
            kFinal.setXVelocity(xVelocityLimit);
        }
        if ((kFinal.getYVelocity() > yVelocityLimit) || (kFinal.getYVelocity() < -yVelocityLimit)){
            kFinal.setYVelocity(yVelocityLimit);
        }
        return kFinal;
    }
}
