package engine.weapon;

import engine.entity.GameEntity;
import engine.level.Level;
/**
 * A weapon that strikes Entities directly in front of the player
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class StabbingWeapon extends WeaponBase{
    private static final double WEAPON_MOVEMENT_INCREMENT = 2;
    
    public StabbingWeapon(GameEntity entity, Level level){
    	this(entity, level, 1);
    }
    
	public StabbingWeapon(GameEntity entity, Level level, int damage){
		super(entity, level);
		hitBox.setSizeX(2*xHolderSize);
		hitBox.setSizeY(yHolderSize/4);
        width = xHolderSize*2;
        height = yHolderSize/4;
        rightXOffset = xHolderSize/10;
        leftXOffset = width - 9*(xHolderSize/10);
        yOffset = yHolderSize/2;
        this.damage = damage;
	}
	
	public StabbingWeapon(GameEntity entity, Level level, double hitBoxX, double hitBoxY, int damage){
		super(entity, level);
		hitBox.setSizeX(hitBoxX);
		hitBox.setSizeY(hitBoxY);
        width = hitBoxX;
        height = hitBoxY;
        rightXOffset = xHolderSize/10;
        leftXOffset = width - 9*(xHolderSize/10);
        yOffset = yHolderSize/2;
        this.damage = damage;
	}
	
	@Override
	public void attack() {
        if(!isAttacking){
            isAttacking = true;
		    if(direction.equals("right")){
			    hitBox.setX(holderXPos + xHolderSize);
		    }
		    else{
			    hitBox.setX(holderXPos - width);
		    }
            hitBox.setY(holderYPos - yHolderSize/2);
            iterateEntities();
        }   
	}
	
	@Override
    public void update() {
		double startingXPos = kinematics.getX();
		double finalXPos;
		updateDirectionality();
        if(isAttacking){
        	if(direction.equals("right")){
        		finalXPos = startingXPos + WEAPON_MOVEMENT_INCREMENT;
                if(finalXPos > holderXPos + xHolderSize){
                	finalXPos = kinematics.getX();
                	isAttacking = false;
                }
        	}
        	else{
        		finalXPos = startingXPos - WEAPON_MOVEMENT_INCREMENT;
                if(finalXPos + width < holderXPos){
                	finalXPos = kinematics.getX();
                	isAttacking = false;
                }
        	}
            kinematics.setX(finalXPos);
        }
    }
}
