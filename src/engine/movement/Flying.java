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
