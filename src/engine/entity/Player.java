package engine.entity;

import java.util.ArrayList;

import engine.interaction.Interaction;
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
    private ArrayList<Interaction> interactionList = new ArrayList<>();
    private double speedFactor;
    private double jumpFactor;
    private double maxVelocityX;
    private double maxVelocityY;

    public Player() {
        this(0,0);
    }
    
    public Player(double x, double y){
        kinematics = new Kinematics(x,y,0,0,0,0);
        movementType = new Grounded();
        weaponType = new NoWeapon();
        speedFactor = 100; //arbitrary for now, might need to be MUCH higher
        jumpFactor = 20; // arbitrary for now
        maxVelocityX = 2; // arbitrary for now
        maxVelocityY = 2; // arbitrary for now
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
    public void setSpeedFactor(double speedFactor){
        this.speedFactor = speedFactor;
    }
    
    @Override
    public void setJumpFactor(double jumpFactor){
        this.jumpFactor = jumpFactor;
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
	public void setMovementType(Movement movement) {
        movementType = movement;
	}

	@Override
	public void setHealth(int HP) {
		health = HP;
	}
	
	@Override
	public int getHealth() {
		return health;
	}


	@Override
	public void addInteraction(Interaction i) {
		interactionList.add(i);
	}
	
	@Override
	public void interact(GameEntity source, GameEntity target) {
		for(Interaction i : interactionList) {
			i.interact(source, target);
		}
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
		maxVelocityX = velocity;
	}

	@Override
	public void setMaxYVelocity(double velocity) {
		maxVelocityY = velocity;		
	}

	@Override
	public void setFrictionConstant(double frictionConstant) {
		kinematics.setFrictionConstant(frictionConstant);
	}
	
	@Override
	public Kinematics getKinematics() {
		return kinematics;
	}
	
	@Override
	public void update() {
		kinematics = movementType.update(kinematics, maxVelocityX, maxVelocityY);
	}
}