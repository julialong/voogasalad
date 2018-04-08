package engine.entity;

import java.util.ArrayList;

import engine.movement.*;
import engine.physics.Kinematics;
import engine.powerup.*;
import engine.weapon.*;
/**
 * Defines a player and its movement in and interactions with the game world.
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class Player extends PlayerCharacter{
    private Kinematics kinematics;
    private int health;
    private Movement movementType;
    private Weapon weaponType;
    private ArrayList<PowerUp> powerupArrayList = new ArrayList<>();
    private double speedFactor;
    private double jumpFactor;
    private double maxVelocityX;
    private double maxVelocityY;

    public Player() {
        this(new Kinematics(0,0,0,0,0,0));
    }
    
    public Player(Kinematics k){
        kinematics = k;
        movementType = new Grounded();
        weaponType = new NoWeapon();
        speedFactor = 20; //arbitrary for now
        jumpFactor = 20; // arbitrary for now
    }
    
    @Override
    public double[] getPosition(){
        double[] positionArray = {kinematics.getX(), kinematics.getY()};
        return positionArray;
    }
    
    @Override
    public double getSpeedFactor(){
        return speedFactor;
    }
    
    @Override
    public double getJumpFactor(){
        return jumpFactor;
    }
    
    @Override
    public void overridePosition(double x, double y) {
        movementType.overridePosition(kinematics,x,y);
    }
    
    @Override
	public void setXVelocity(double velocity) {
        movementType.setVelocityX(kinematics, velocity);
	}
    
    @Override
	public void setYVelocity(double velocity) {
        movementType.setVelocityY(kinematics, velocity);
	}
    
	@Override
	public void setXAcceleration(double accel) {
        movementType.setAccelerationX(kinematics, accel);
	}
    
    @Override
	public void setYAcceleration(double accel) {
        movementType.setAccelerationY(kinematics, accel);
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
	public void setWeapon(Weapon weapon) {
        weaponType = weapon;		
	}

	@Override
	public void useWeapon() {
		//weaponType.attack();// TODO temporary fix to compilation errors will be replaced later
	}

	@Override
	public void addPowerUp(PowerUp power) {
        if(powerupArrayList.contains(power)){
            powerupArrayList.remove(power);
        }
        powerupArrayList.add(power);
	}

	@Override
	public void removePowerUp(PowerUp power) {
        powerupArrayList.remove(power);
	}

	@Override
	public Movement getMovementType() {
		return movementType;
	}

	@Override
	public void setMaxXVelocity(double velocity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMaxYVelocity(double velocity) {
		// TODO Auto-generated method stub
		
	}

}