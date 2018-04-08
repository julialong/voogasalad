package engine.entity;

import engine.behavior.Behavior;
import engine.movement.Movement;
/**
 * Defines GameObjects that tend to move and/or can be jumped through and onto by other Entities.
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class Platform extends GameObject {
	@Override
	public void setMovementType(Movement movement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHealth(int HP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInteraction(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBehavior(Behavior behavior) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getSpeedFactor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getJumpFactor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void overridePosition(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setXVelocity(double velocity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setYVelocity(double velocity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setXAcceleration(double accel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setYAcceleration(double accel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Movement getMovementType() {
		// TODO Auto-generated method stub
		return null;
	}

}
