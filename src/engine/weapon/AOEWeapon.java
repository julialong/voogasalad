package engine.weapon;

import engine.entity.Block;
import engine.entity.GameEntity;
import engine.entity.GameObject;
import engine.interaction.HarmTarget;
import engine.physics.DetectCollision;

/**
 * A weapon that can hit in a circle around the user
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class AOEWeapon extends GameObject implements Weapon{
	private Block hitBox = new Block();
	private GameEntity weaponHolder;
	private DetectCollision collisionDetector = new DetectCollision();
	private HarmTarget dealDamage = new HarmTarget();
	private double xSize;
	private double ySize;
	
	public AOEWeapon(GameEntity entity){
		weaponHolder = entity;
		xSize = weaponHolder.getSizeX();
		ySize = weaponHolder.getSizeY();
		hitBox.setSizeX(xSize + 1.5*xSize);
		hitBox.setSizeY(ySize + 1.5*xSize);
	}
	
	@Override
	public void attack(GameEntity entity) {
		double xPos = weaponHolder.getPosition()[0];
		double yPos = weaponHolder.getPosition()[1];
		hitBox.setX(xPos - hitBox.getSizeX());
		hitBox.setY(yPos + hitBox.getSizeY());
		//for(GameEntity entity : LIST_OF_ENTITIES){
			if(!collisionDetector.detect(hitBox, entity).equals("none")){
				dealDamage.interact(hitBox, entity);
			}
		//}
	}

}
