package engine.entity;

import java.util.ArrayList;

import engine.behavior.*;
import engine.interaction.Interaction;
import engine.movement.*;
import engine.physics.Kinematics;
import engine.weapon.*;
/**
 * Defines information for basic enemies in a game.
 * @author Rob
 *
 */
public class Foes extends Enemy {
    private Weapon weaponType;
    
    public Foes() {
        this(0,0);
    }
    
    public Foes(double x, double y){
        kinematics = new Kinematics(x,y,0,0,0,0);
        movementType = new Grounded();
        weaponType = new NoWeapon();
        behaviorList.add(new NoBehavior()); 
        speedFactor = 500; //arbitrary for now
        jumpFactor = 20; // arbitrary for now
        maxVelocityX = 2; // arbitrary for now
        maxVelocityY = 2; // arbitrary for now
        destructible = true;
    }
   
    @Override
	public void addInteraction(Interaction i) {
		interactionList.add(i);
	}

	@Override
	public void setWeapon(Weapon weapon) {
		weaponType = weapon;		
	}

	@Override
	public void useWeapon() {
		// TODO Auto-generated method stub
		
	}
}