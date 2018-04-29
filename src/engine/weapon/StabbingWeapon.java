package engine.weapon;

import java.util.ArrayList;

import engine.entity.Block;
import engine.entity.GameEntity;
import engine.entity.GameObject;
import engine.interaction.HarmTarget;
import engine.level.Level;
import engine.physics.DetectCollision;
/**
 * A weapon that strikes Entities directly in front of the player
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class StabbingWeapon extends GameObject implements Weapon{

	private Block hitBox = new Block();
	private GameEntity weaponHolder;
	private Level level;
	private DetectCollision collisionDetector = new DetectCollision();
	private HarmTarget dealDamage = new HarmTarget();
	private ArrayList<GameEntity> listOfEntities = new ArrayList<>();
	private double xSize;
	private double ySize;
	
	public StabbingWeapon(GameEntity entity, Level level){
		weaponHolder = entity;
		this.level = level;
		xSize = weaponHolder.getSizeX();
		ySize = weaponHolder.getSizeY();
		hitBox.setSizeX(1.5*xSize);
		hitBox.setSizeY(ySize/4);
	}
	
	@Override
	public void attack() {
		double xPos = weaponHolder.getPosition()[0];
		double yPos = weaponHolder.getPosition()[1];
		if(weaponHolder.getKinematics().getXVelocity() >= 0){
			hitBox.setX(xPos + xSize);
			hitBox.setY(yPos - ySize/2);
		}
		else{
			hitBox.setX(xPos - xSize);
			hitBox.setY(yPos - ySize/2);
		}
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
