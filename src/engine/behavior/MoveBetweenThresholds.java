package engine.behavior;

import engine.entity.GameEntity;
import engine.movement.Flying;
import engine.movement.LinearFlying;
import engine.physics.Kinematics;
import engine.physics.Physics;

/**
 * Behavior implementation that causes entity to reverse direction when it
 * reaches the edge of an object it is standing on
 * 
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class MoveBetweenThresholds implements Behavior {
	private boolean goingLeft = true;
	private double thresholdLeft;
	private double thresholdRight;

	public MoveBetweenThresholds(double thresholdLeft, double thresholdRight) {
		this.thresholdLeft = thresholdLeft;
		this.thresholdRight = thresholdRight;
	}

	@Override
	public void update(GameEntity entity) {
		Physics lookaheadPhysics = new Physics();
		Kinematics lookaheadKinematics = new Kinematics(entity.getKinematics().getX(), entity.getKinematics().getY(),
				entity.getKinematics().getXVelocity(), entity.getKinematics().getYVelocity(),
				entity.getKinematics().getXAcceleration(), entity.getKinematics().getYAcceleration());
		if (!(entity.getMovementType() instanceof Flying) && !(entity.getMovementType() instanceof LinearFlying)) {
			Kinematics newKinematics = lookaheadPhysics.applyPhysics(lookaheadKinematics, true, true);
			if ((newKinematics.getX() < thresholdLeft) || (newKinematics.getX() > thresholdRight)) {
				if (goingLeft) {
					entity.setXVelocity(entity.getMaxXVelocity());
					goingLeft = false;
				} else {
					entity.setXVelocity(-entity.getMaxXVelocity());
					goingLeft = true;
				}
			}
			if (goingLeft) {
				entity.setXVelocity(-entity.getMaxXVelocity());
			} else {
				entity.setXVelocity(entity.getMaxXVelocity());
			}
		}
	}

}
