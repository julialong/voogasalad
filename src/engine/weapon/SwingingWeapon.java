package engine.weapon;

import java.util.ArrayList;

import engine.entity.Block;
import engine.entity.GameEntity;
import engine.entity.GameObject;
import engine.interaction.HarmTarget;
import engine.level.Level;
import engine.physics.DetectCollision;
/**
 * A Weapon that hits in an arc in front of the Player
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class SwingingWeapon extends GameObject implements Weapon{
	private Block hitBox = new Block();
	private GameEntity weaponHolder;
	private Level level;
	private DetectCollision collisionDetector = new DetectCollision();
	private HarmTarget dealDamage = new HarmTarget();
	private ArrayList<GameEntity> listOfEntities = new ArrayList<>();
	private double xSize;
	private double ySize;
	
	public SwingingWeapon(GameEntity entity, Level level){
		weaponHolder = entity;
		this.level = level;
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
		listOfEntities = (ArrayList<GameEntity>) level.getObjects();
		for(GameEntity entity : listOfEntities){
			if(!collisionDetector.detect(hitBox, entity).equals("none")){
				dealDamage.interact(hitBox, entity);
			}
		}
	}

}
