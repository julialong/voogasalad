package engine.entity;

import java.util.ArrayList;

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
    private Weapon weaponType;
    
    public Foes(Player p) {
        this(new Kinematics(0,0,0,0,0,0), p);
    }
    
    public Foes(Kinematics k, Player p){
        kinematics = k;
        movementType = new Grounded();
        weaponType = new NoWeapon();
        behaviorList.add(new NoBehavior());
        speedFactor = 500; //arbitrary for now
        jumpFactor = 20; // arbitrary for now
        maxVelocityX = 20; // arbitrary for now
        maxVelocityY = 20; // arbitrary for now
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		for(Behavior behavior : behaviorList) {
			behavior.update(this);
		}
		kinematics = movementType.update(kinematics, maxVelocityX, maxVelocityY);
	}
}