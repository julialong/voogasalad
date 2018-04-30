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
    protected HarmTarget dealDamage;
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
    protected boolean active = false;
    protected int damage = 1;
	
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
    		kinematics.setX(holderXPos + rightXOffset);
    	}
    	else{
    		kinematics.setX(holderXPos - leftXOffset);
    	}
        kinematics.setY(holderYPos - yOffset);
	}
	
	@Override
	public void iterateEntities(){
	    listOfEntities = (ArrayList<GameEntity>) level.getObjects();
	    for(GameEntity entity : listOfEntities){
	    	if(entity.equals(weaponHolder)){
	    		continue;
	    	}
		    if(!collisionDetector.detect(hitBox, entity).equals("none")){
			    if(entity.getDestructible()) {
			    	dealDamage = new HarmTarget(damage);
				    dealDamage.interact(hitBox, entity);
			    }
		    }
	    }
	}
	
	@Override
	public void activate(){
		active = true;
	}
	
	@Override
	public void deactivate(){
		active = false;
	}
	
	@Override
	public boolean getActive(){
		return active;
	}
}
