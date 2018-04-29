package engine.weapon;

import engine.entity.GameEntity;
import engine.level.Level;

/**
 * A Weapon that hits in an arc in front of the Player
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class SwingingWeapon extends WeaponBase{
    private static final double DEFAULT_WEAPON_ANGLE = 60;
    private static final double WEAPON_ANGLE_INCREMENT = 20;
    
	public SwingingWeapon(GameEntity entity, Level level){
		super(entity, level);
		hitBox.setSizeX(xHolderSize*2);
		hitBox.setSizeY(yHolderSize - 2*offset);
        width = xHolderSize*2;
        height = yHolderSize/4;
        weaponAngle = DEFAULT_WEAPON_ANGLE;
        rightXOffset = 0.3 * xHolderSize;
        leftXOffset = width - 0.65 * xHolderSize;
        yOffset = yHolderSize/8;
	}
	
	@Override
	public void attack() {
        if(!isAttacking){
            isAttacking = true;
		    if(direction.equals("right")){
			    hitBox.setX(holderXPos+xHolderSize);
		    }
		    else{
			    hitBox.setX(holderXPos-xHolderSize);
		    }
            hitBox.setY(holderYPos - offset);
            iterateEntities();
        }   
	}
    
	@Override
    public void update() {
		updateDirectionality();
        if(isAttacking){
            weaponAngle -= WEAPON_ANGLE_INCREMENT;
            if(weaponAngle < -DEFAULT_WEAPON_ANGLE){
                weaponAngle = DEFAULT_WEAPON_ANGLE;
                isAttacking = false;
            }
        }
    }
}