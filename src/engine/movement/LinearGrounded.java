package engine.movement;

import engine.entity.GameEntity;
import engine.physics.Kinematics;

/**
 * Movement implementation for entities that remain on the ground and are affected by gravity but move with constant velocity
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class LinearGrounded implements Movement {
	private Grounded grounded = new Grounded();
	
    @Override
    public Kinematics update(Kinematics k, double xVelocityLimit, double yVelocityLimit){
    	return grounded.update(k, xVelocityLimit, yVelocityLimit);
    }
}
