package engine.weapon;

import java.util.ArrayList;

import engine.entity.Block;
import engine.entity.GameEntity;
import engine.entity.GameObject;
import engine.interaction.HarmTarget;
import engine.level.Level;
import engine.physics.DetectCollision;
import engine.physics.Kinematics;
/**
 * A Weapon that hits in an arc in front of the Player
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class SwingingWeapon extends WeaponBase{
    private static final double DEFAULT_WEAPON_ANGLE = 80;
    private static final double WEAPON_ANGLE_INCREMENT = 20;
    
	public SwingingWeapon(GameEntity entity, Level level){
		kinematics = new Kinematics(0,0,0,0,0,0);
		weaponHolder = entity;
		this.level = level;
        this.level.addObject(this);
		xSize = weaponHolder.getSizeX();
		ySize = weaponHolder.getSizeY();
		hitBox.setSizeX(xSize*2);
		hitBox.setSizeY(ySize - 2*offset);
        width = xSize*2;
        height = ySize/4;
        weaponAngle = DEFAULT_WEAPON_ANGLE;
	}
	
	@Override
	public void attack() {
        if(!isAttacking){
            isAttacking = true;
		    double xPos = weaponHolder.getPosition()[0];
		    double yPos = weaponHolder.getPosition()[1];
		    if(direction.equals("right")){
			    hitBox.setX(xPos+xSize);
		    }
		    else{
			    hitBox.setX(xPos-xSize);
		    }
            hitBox.setY(yPos - offset);
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
    
	@Override
    public void update() {
		updateDirectionality();
    	if(direction.equals("right")){
    		kinematics.setX(weaponHolder.getPosition()[0] + 0.3 * xSize);
    	}
    	else{
    		kinematics.setX(weaponHolder.getPosition()[0] - width + 0.65 * xSize);
    	}
        kinematics.setY(weaponHolder.getPosition()[1] - ySize/8);
        if(isAttacking){
            weaponAngle -= WEAPON_ANGLE_INCREMENT;
            if(weaponAngle < -DEFAULT_WEAPON_ANGLE){
                weaponAngle = DEFAULT_WEAPON_ANGLE;
                isAttacking = false;
            }
        }
    }
}