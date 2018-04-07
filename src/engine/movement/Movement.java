package engine.movement;

import engine.physics.Kinematics;

/**
 * Movement interface is implemented by all movement types to control how an
 * entity moves.
 * @author Robert Gitau and Marcus Oertle
 */
public interface Movement {
    
    public abstract Kinematics overridePosition(Kinematics k, double x, double y);
    
    public abstract Kinematics setVelocityX(Kinematics k, double velocity);
    
    public abstract Kinematics setVelocityY(Kinematics k, double velocity);
        
    public abstract Kinematics setAccelerationX(Kinematics k, double accel);
    
    public abstract Kinematics setAccelerationY(Kinematics k, double accel);
    
    public abstract Kinematics update(Kinematics k, double xVelocityLimit, double yVelocityLimit);
}