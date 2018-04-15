package engine.movement;

import engine.entity.GameEntity;
import engine.physics.Kinematics;

public class Static implements Movement{

	@Override
	public Kinematics update(Kinematics k, double xVelocityLimit, double yVelocityLimit) {
		return k;
	}
}
