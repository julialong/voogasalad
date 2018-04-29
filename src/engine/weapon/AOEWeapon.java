package engine.weapon;

import java.util.ArrayList;

import engine.entity.Block;
import engine.entity.GameEntity;
import engine.entity.GameObject;
import engine.interaction.HarmTarget;
import engine.level.Level;
import engine.physics.DetectCollision;

/**
 * A weapon that can hit in a wide area around the user
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class AOEWeapon extends GameObject implements Weapon{
	private Block hitBox = new Block();
	private GameEntity weaponHolder;
	private Level level;
	private DetectCollision collisionDetector = new DetectCollision();
	private HarmTarget dealDamage = new HarmTarget();
	private ArrayList<GameEntity> listOfEntities = new ArrayList<>();
	private double xSize;
	private double ySize;
	
	public AOEWeapon(GameEntity entity, Level level){
		weaponHolder = entity;
		this.level = level;
		xSize = weaponHolder.getSizeX();
		ySize = weaponHolder.getSizeY();
		hitBox.setSizeX(xSize + 1.5*xSize);
		hitBox.setSizeY(ySize + 1.5*xSize);
	}
	
	@Override
	public void attack() {
		//System.out.println("attacking");
		double xPos = weaponHolder.getPosition()[0];
		double yPos = weaponHolder.getPosition()[1];
		hitBox.setX(xPos - 0.75*xSize);
		hitBox.setY(yPos + 0.75*xSize);
		listOfEntities = (ArrayList<GameEntity>) level.getObjects();
		for(GameEntity entity : listOfEntities){
			//System.out.println("checking collision with " + entity.getClass().getSimpleName());
			if(!collisionDetector.detect(hitBox, entity).equals("none")){
				if(entity.getDestructible()) {
				//System.out.println("dealing damage to " + entity.getClass().getSimpleName());
					dealDamage.interact(hitBox, entity);
				}
			}
		}
	}

}
