package engine.weapon;

import engine.entity.Block;
import engine.entity.GameEntity;
import engine.entity.GameObject;
import engine.interaction.HarmTarget;
import engine.physics.DetectCollision;
/**
 * A Weapon that hits in an arc in front of the Player
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class SwingingWeapon extends GameObject implements Weapon{
	private Block hitBox = new Block();
	private GameEntity weaponHolder;
	private DetectCollision collisionDetector = new DetectCollision();
	private HarmTarget dealDamage = new HarmTarget();
	private double xSize;
	private double ySize;
	
	public SwingingWeapon(GameEntity entity){
		weaponHolder = entity;
		xSize = weaponHolder.getSizeX();
		ySize = weaponHolder.getSizeY();
		hitBox.setSizeX(2*xSize);
		hitBox.setSizeY(ySize);
	}
	
	@Override
	public void attack() {
		double xPos = weaponHolder.getPosition()[0];
		double yPos = weaponHolder.getPosition()[1];
		hitBox.setX(xPos);
		hitBox.setY(yPos);
		//for(GameEntity entity : LIST_OF_ENTITIES){
			if(!collisionDetector.detect(hitBox, entity).equals("none")){
				dealDamage.interact(hitBox, entity);
			}
		//}
	}

}
