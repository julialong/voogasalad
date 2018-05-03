package engine.entity;

import java.util.ArrayList;

import engine.movement.Grounded;
import engine.physics.Kinematics;
import engine.powerup.PowerUp;
import engine.weapon.Weapon;
/**
 * Defines a player and its movement in and interactions with the game world.
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class Player extends GameObject{
    private Weapon weaponType;
    private ArrayList<PowerUp> powerupList = new ArrayList<>();
    private boolean levelComplete = false;
    private boolean gameOver = false;

    public Player() {
        this(0,0);
    }
    
    public Player(double x, double y){
        kinematics = new Kinematics(x,y,0,0,0,0);
        movementType = new Grounded();
        speedFactor = 1000; //arbitrary for now, might need to be MUCH higher
        jumpFactor = 350; // arbitrary for now
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
		if(weaponType != null){
			weaponType.deactivate();
		}
        weaponType = weapon;
        weaponType.activate();
	}

	/**
	 * Performs whatever effect the player's current weapon has in its attack
	 * method.
	 */
	public void useWeapon() {
		if(weaponType != null){
			weaponType.attack();
		}
	}
	
	/**
	 * Returns the weapon the player has.
	 */
	public Weapon getWeapon(){
		return weaponType;
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
	
	/**
	 * Sets the gameOver boolean, true if the level is over
	 * @param gameOver - boolean, true if the level is over
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * Gets the gameOver boolean, true if the game is over
	 */
	public boolean getGameOver() {
		return gameOver;
	}
}