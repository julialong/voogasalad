package engine.weapon;

import java.util.ArrayList;

import engine.entity.Block;
import engine.entity.GameEntity;
import engine.entity.GameObject;
import engine.interaction.HarmTarget;
import engine.level.Level;
import engine.physics.DetectCollision;

public class WeaponBase extends GameObject implements Weapon{
    protected Block hitBox = new Block();
    protected GameEntity weaponHolder;
    protected Level level;
    protected double offset = 2;
    protected DetectCollision collisionDetector = new DetectCollision();
    protected HarmTarget dealDamage = new HarmTarget();
    protected ArrayList<GameEntity> listOfEntities = new ArrayList<>();
    protected double xSize;
    protected double ySize;
    protected double weaponAngle;
    protected boolean isAttacking = false;
    protected String direction = "right";
    
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
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
    	if(weaponHolder.getKinematics().getXVelocity() > 0){
    		direction = "right";
		}
		else if(weaponHolder.getKinematics().getXVelocity() < 0){
			direction = "left";
		}
	}
}
