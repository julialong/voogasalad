package engine.entity;

import java.util.ArrayList;
import java.util.List;

import engine.behavior.Behavior;
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
public class Player extends GameObject{
    private Weapon weaponType;
    private ArrayList<PowerUp> powerupList = new ArrayList<>();
    private boolean levelComplete = false;

    public Player() {
        this(0,0);
    }
    
    public Player(double x, double y){
        kinematics = new Kinematics(x,y,0,0,0,0);
        movementType = new Grounded();
        weaponType = new NoWeapon();
        speedFactor = 1000; //arbitrary for now, might need to be MUCH higher
        jumpFactor = 150; // arbitrary for now
        maxVelocityX = 50; // arbitrary for now
        maxVelocityY = 500; // arbitrary for now
        destructible = true;
    }
    
    /**
	 * Sets the player's weapon, which implements the Weapon interface.
	 * 
	 * @param weapon:
	 *            the Weapon the player is given
	 */

	public void setWeapon(Weapon weapon) {
        weaponType = weapon;		
	}

	/**
	 * performs whatever effect the player's current weapon has in its attack
	 * method.
	 */
	public void useWeapon() {
		weaponType.attack();
	}

	/**
	 * Adds a power up to the Player, which has behavior defined internally.
	 * 
	 * @param power:
	 *            the PowerUp to be added.
	 */
	public void addPowerUp(PowerUp power) {
        if(powerupList.contains(power)){
        	power.deactivate();
        	powerupList.remove(power);
        }
        powerupList.add(power);
	}

	/**
	 * Removes an added power up from the player, preventing its effects.
	 * 
	 * @param power:
	 *            the PowerUp to be removed.
	 */
	public void removePowerUp(PowerUp power) {
		powerupList.remove(power);
	}
	
	@Override
	public void update() {
		interactionsMap.clear();
		ArrayList<PowerUp> toRemove = new ArrayList<>();
		for(PowerUp powerup : powerupList) {
			if(powerup.update()) {
				toRemove.add(powerup);
			}
		}
		for(PowerUp powerup : toRemove) {
			powerupList.remove(powerup);
		}
		toRemove.clear();
		kinematics = movementType.update(kinematics, maxVelocityX, maxVelocityY);
	}

	/**
	 * Sets the levelComplete boolean, true if the level is complete
	 * @param levelComplete - boolean, true if the level is complete
	 */
	public void setLevelComplete(boolean levelComplete) {
		this.levelComplete = levelComplete;
	}

	/**
	 * Gets the levelComplete boolean, true if the level is complete
	 */
	public boolean getLevelComplete() {
		return levelComplete;
	}
}