package engine.weapon;

import engine.entity.GameEntity;
import engine.level.Level;

/**
 * A weapon that can hit in a wide area around the user
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class AOEWeapon extends WeaponBase{
    private static final double DEFAULT_WEAPON_ANGLE = 0;
    private static final double WEAPON_ANGLE_INCREMENT = 20;
    
	public AOEWeapon(GameEntity entity, Level level){
		this(entity, level, 1);
	}
	
	public AOEWeapon(GameEntity entity, Level level, int damage){
		super(entity, level);
		width = xHolderSize*5;
        height = yHolderSize/4;
		hitBox.setSizeX(5*xHolderSize);
		hitBox.setSizeY(yHolderSize + 2*xHolderSize);
		rightXOffset = -2 * xHolderSize;
        leftXOffset = -rightXOffset;
        yOffset = yHolderSize/2;
        weaponAngle = DEFAULT_WEAPON_ANGLE;
        this.damage = damage;
	}
	
    public AOEWeapon(GameEntity entity, Level level, double hitBoxX, double hitBoxY, int damage){
    	super(entity, level);
    	width = hitBoxX;
        height = yHolderSize/4;
		hitBox.setSizeX(hitBoxX);
		hitBox.setSizeY(hitBoxY);
		rightXOffset = -hitBoxX/2 + xHolderSize/2;
        leftXOffset = -rightXOffset;
        yOffset = yHolderSize/2;
        weaponAngle = DEFAULT_WEAPON_ANGLE;
        this.damage = damage;
    }
    
	@Override
	public void attack() {
		if(!isAttacking){
			isAttacking = true;
			hitBox.setX(holderXPos + rightXOffset);
			hitBox.setY(holderYPos + xHolderSize);
			iterateEntities();
		}
	}

	@Override
	public void update(){
		updateDirectionality();
		if(isAttacking){
            weaponAngle -= WEAPON_ANGLE_INCREMENT;
            if(weaponAngle < -360 - DEFAULT_WEAPON_ANGLE){
            	weaponAngle = DEFAULT_WEAPON_ANGLE;
                isAttacking = false;
            }
        }
	}
}
