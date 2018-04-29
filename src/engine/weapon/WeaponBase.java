package engine.weapon;

import java.util.ArrayList;

import engine.entity.Block;
import engine.entity.GameEntity;
import engine.entity.GameObject;
import engine.interaction.HarmTarget;
import engine.level.Level;
import engine.physics.DetectCollision;
import engine.physics.Kinematics;

public abstract class WeaponBase extends GameObject implements Weapon{
    protected Block hitBox = new Block();
    protected GameEntity weaponHolder;
    protected Level level;
    protected double offset = 2;
    protected DetectCollision collisionDetector = new DetectCollision();
    protected HarmTarget dealDamage = new HarmTarget();
    protected ArrayList<GameEntity> listOfEntities = new ArrayList<>();
    protected double xHolderSize;
    protected double yHolderSize;
    protected double weaponAngle;
    protected boolean isAttacking = false;
    protected String direction = "right";
    protected double rightXOffset;
    protected double leftXOffset;
    protected double yOffset;
    protected double holderXPos;
    protected double holderYPos;
	
    public WeaponBase(GameEntity entity, Level level){
		kinematics = new Kinematics(0,0,0,0,0,0);
		weaponHolder = entity;
		this.level = level;
        this.level.addObject(this);
        xHolderSize = weaponHolder.getSizeX();
        yHolderSize = weaponHolder.getSizeY();
        width = xHolderSize*2;
        height = yHolderSize/4;
        weaponAngle = 0;
    }
    
	@Override
    public double getAngle(){
    	if(direction.equals("right")){
			return -1*weaponAngle;
		}
		else{
			return weaponAngle;
		}
    }
	
	@Override
	public void updateDirectionality(){
		holderXPos = weaponHolder.getPosition()[0];
		holderYPos = weaponHolder.getPosition()[1];
    	if(weaponHolder.getKinematics().getXVelocity() > 0){
    		direction = "right";
		}
		else if(weaponHolder.getKinematics().getXVelocity() < 0){
			direction = "left";
		}
    	if(direction.equals("right")){
    		kinematics.setX(weaponHolder.getPosition()[0] + rightXOffset);
    	}
    	else{
    		kinematics.setX(weaponHolder.getPosition()[0] + leftXOffset);
    	}
        kinematics.setY(weaponHolder.getPosition()[1] + yOffset);
	}
	
	@Override
	public void iterateEntities(){
	    listOfEntities = (ArrayList<GameEntity>) level.getObjects();
	    for(GameEntity entity : listOfEntities){
		    if(!collisionDetector.detect(hitBox, entity).equals("none")){
			    if(entity.getDestructible()) {
				    dealDamage.interact(hitBox, entity);
			    }
		    }
	    }
	}
}
