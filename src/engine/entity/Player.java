package engine.entity;

import java.util.ArrayList;

import engine.interaction.Interaction;
import engine.movement.*;
import engine.physics.Kinematics;
import engine.powerup.*;
import engine.weapon.*;
import javafx.scene.image.ImageView;
/**
 * Defines a player and its movement in and interactions with the game world.
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class Player extends PlayerCharacter{
    private Weapon weaponType;
    private ArrayList<PowerUp> powerupArrayList = new ArrayList<>();
    private ArrayList<Interaction> interactionList = new ArrayList<>();
    private double speedFactor;
    private double jumpFactor;
    private double maxVelocityX;
    private double maxVelocityY;
    private ImageView myImageView;
    private String myElementID;

    public Player() {
        this(0,0);
    }
    
    public Player(double x, double y){
        kinematics = new Kinematics(x,y,0,0,0,0);
        movementType = new Grounded();
        weaponType = new NoWeapon();
        speedFactor = 1000; //arbitrary for now, might need to be MUCH higher
        jumpFactor = 80; // arbitrary for now
        maxVelocityX = 100; // arbitrary for now
        maxVelocityY = 50; // arbitrary for now
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
	public void setImageView(ImageView view) {
		myImageView = view; 
	}
	
	@Override
	public ImageView getImageView() {
		return myImageView;
	}
	
	@Override
	public void update() {
		kinematics = movementType.update(kinematics, maxVelocityX, maxVelocityY);
	}

	@Override
	public void setElementID(String ID) {
		myElementID = ID;
	}

	@Override
	public String getElementID() {
		return myElementID;
	}
}