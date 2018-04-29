package engine.movement;

import engine.entity.GameEntity;
import engine.physics.Kinematics;
import engine.physics.Physics;

/**
 * Movement implementation for entities that remain on the ground and are affected by gravity but move with constant velocity
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class LinearGrounded implements Movement {
	//private Grounded grounded = new Grounded();
	private Physics physics = new Physics();
	
    @Override
    public Kinematics update(Kinematics k, double xVelocityLimit, double yVelocityLimit){
    	//return grounded.update(k, xVelocityLimit, yVelocityLimit);
    	Kinematics kFinal = physics.applyPhysics(k, true, false);
        if (kFinal.getXVelocity() > xVelocityLimit){
            kFinal.setXVelocity(xVelocityLimit);
        }
        if(kFinal.getXVelocity() < -xVelocityLimit){
        	kFinal.setXVelocity(-xVelocityLimit);
        }
        if (kFinal.getYVelocity() > yVelocityLimit){
            kFinal.setYVelocity(yVelocityLimit);
        }
        if(kFinal.getYVelocity() < -yVelocityLimit){
        	kFinal.setYVelocity(-yVelocityLimit);
        }
        //System.out.println("X velocity = " + kFinal.getXVelocity());
        return kFinal;
    }
}
