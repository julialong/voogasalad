package engine.physics;

import java.util.HashMap;
import java.util.Map;

import engine.entity.GameEntity;

/**
 * Performs gravitational and pseudo-frictional physics calculations
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class Physics {
    private static final double FRAME_RATE = 60;
	private static final double TIME_STEP = 1/FRAME_RATE;
	private Map<GameEntity, Kinematics> previousGeKinematicsMap = new HashMap<>();
	
	/**
	 * Apply physics to a Kinematics object
	 * @param k - a Kinematics object
	 * @param gravApplies - boolean determining whether to apply gravity
	 * @return new Kinematics()
	 */
	public Kinematics applyPhysics(Kinematics k, boolean gravApplies, boolean frictionApplies){
		double xAcc = k.getXAcceleration();
		if(frictionApplies) {
			xAcc = applyFriction(k, xAcc);
		}
		double yAcc = k.getYAcceleration();
		if(gravApplies){
			yAcc += -k.getGravitationalConstant();
		}
		double xFinalVelocity = k.getXVelocity() + xAcc * TIME_STEP;
		double yFinalVelocity = k.getYVelocity() + yAcc * TIME_STEP;
		double xFinalPos = k.getX() + k.getXVelocity() * TIME_STEP + 0.5 * xAcc * TIME_STEP * TIME_STEP;
		double yFinalPos = k.getY() + k.getYVelocity() * TIME_STEP + 0.5 * yAcc * TIME_STEP * TIME_STEP;
		if(k.getXAcceleration() == 0){
			if(((k.getXVelocity() > 0) && (xFinalVelocity < 0)) || ((k.getXVelocity() < 0) && (xFinalVelocity > 0))) {
				xFinalVelocity = 0;
				xFinalPos = k.getX();
			}
		}
		k.setX(xFinalPos);
		k.setY(yFinalPos);
		k.setXVelocity(xFinalVelocity);
		k.setYVelocity(yFinalVelocity);
		return k;
	}
	
	/**
	 * Applies friction
	 * @param k
	 * @param xAcc
	 * @return
	 */
	public double applyFriction(Kinematics k, double xAcc) {
        if(k.getXVelocity() != 0){
        	if(k.getXVelocity() < 0){
        		xAcc += k.getFrictionConstant();
        	}
        	else if (k.getXVelocity() > 0){
        		xAcc -= k.getFrictionConstant();
        	}
        }
        return xAcc;
	}
}