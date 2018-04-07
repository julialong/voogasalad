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
        double[] positionArray = {k.getX(), k.getY()};
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
        movementType.overridePosition(x,y)
    }
    
    @Override
	public void setXVelocity(double velocity) {
        movementType.setXVelocity(velocity);
	}
    
    @Override
	public void setYVelocity(double velocity) {
        movementType.setYVelocity(velocity);
	}
    
	@Override
	public void setXAcceleration(double accel) {
        movementType.setXAcceleration(accel);
	}
    
    @Override
	public void setYAcceleration(double accel) {
        movementType.setYAcceleration(accel);
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
		weaponType.attack();
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

}