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
public class Player extends PlayerCharacter{
    private Weapon weaponType;
    private ArrayList<PowerUp> powerupList = new ArrayList<>();

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
    
	public void setWeapon(Weapon weapon) {
        weaponType = weapon;		
	}

	public void useWeapon() {
		//weaponType.attack();// TODO temporary fix to compilation errors will be replaced later
	}

	public void addPowerUp(PowerUp power) {
        if(powerupList.contains(power)){
        	power.deactivate();
        	powerupList.remove(power);
        }
        powerupList.add(power);
	}

	public void removePowerUp(PowerUp power) {
		powerupList.remove(power);
	}

	public void setPowerUpsList(List powerupList) {
		this.powerupList = (ArrayList<PowerUp>) powerupList;
		
	}

	public List getPowerUpsList() {
		return powerupList;
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
}