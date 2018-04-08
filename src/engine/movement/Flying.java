package engine.movement;

import engine.physics.Kinematics;

/**
 * Movement implementation for entities that fly and are exempt from gravity and friction
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class Flying implements Movement{

	@Override
	public Kinematics overridePosition(Kinematics k, double x, double y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kinematics setVelocityX(Kinematics k, double velocity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kinematics setVelocityY(Kinematics k, double velocity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kinematics setAccelerationX(Kinematics k, double accel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kinematics setAccelerationY(Kinematics k, double accel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kinematics update(Kinematics k, double xVelocityLimit, double yVelocityLimit) {
		// TODO Auto-generated method stub
		return null;
	}

}
