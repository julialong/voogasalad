package engine.entity;

import engine.behavior.*;
import engine.movement.*;
import engine.physics.Kinematics;
import engine.weapon.*;
/**
 * Defines information for basic enemies in a game.
 * @author Rob
 *
 */
public class Foes extends Enemy {
	private Movement movementType;
    private Kinematics kinematics;
    private double health;
    private Weapon weaponType;
    private double jumpFactor;
    private double speedFactor;
    private Behavior behavior;
    private double maxVelocityX;
    private double maxVelocityY;
    
    public Foes(Player p) {
        this(new Kinematics(0,0,0,0,0,0), p);
    }
    
    public Foes(Kinematics k, Player p){
        kinematics = k;
        movementType = new Grounded();
        weaponType = new NoWeapon();
        behavior = new MoveForward(p);
        speedFactor = 500; //arbitrary for now
        jumpFactor = 20; // arbitrary for now
        maxVelocityX = 20; // arbitrary for now
        maxVelocityY = 20; // arbitrary for now
    }
    @Override
	public void setMovementType(Movement movement) {
		movementType = movement;
	}

	@Override
	public void setHealth(int HP) {
		health = HP;		
	}

	@Override
	public void setInteraction(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBehavior(Behavior behavior) {
		this.behavior = behavior;		
	}

	@Override
	public void setWeapon(Weapon weapon) {
		weaponType = weapon;		
	}

	@Override
	public void useWeapon() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getSpeedFactor() {
		return speedFactor;
	}

	@Override
	public double[] getPosition() {
		double[] positionArray = {kinematics.getX(), kinematics.getY()};
        return positionArray;
	}

	@Override
	public double getJumpFactor() {
		return jumpFactor;
	}

	@Override
	public void overridePosition(double x, double y) {
		kinematics.setX(x);
		kinematics.setY(y);
	}

	@Override
	public void setXVelocity(double velocity) {
        kinematics.setXVelocity(velocity);
	}
    
    @Override
	public void setYVelocity(double velocity) {
    	kinematics.setYVelocity(velocity);
	}
    
	@Override
	public void setXAcceleration(double accel) {
		kinematics.setXAcceleration(accel);
	}
    
    @Override
	public void setYAcceleration(double accel) {
    	kinematics.setYAcceleration(accel);
	}

	@Override
	public Movement getMovementType() {
		return movementType;
	}
    
    @Override
	public void setMaxXVelocity(double velocity) {
		maxVelocityX = velocity;
	}

	@Override
	public void setMaxYVelocity(double velocity) {
		maxVelocityY = velocity;
		
	}

	@Override
	public void update() {
		behavior.update(this);
		kinematics = movementType.update(kinematics, maxVelocityX, maxVelocityY);
	}
}