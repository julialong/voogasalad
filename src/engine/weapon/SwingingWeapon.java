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
public class SwingingWeapon extends GameObject implements Weapon{
    private static final double DEFAULT_WEAPON_ANGLE = 80;
    private static final double WEAPON_ANGLE_INCREMENT = 20;
	private Block hitBox = new Block();
	private GameEntity weaponHolder;
	private Level level;
	private double offset = 2;
	private DetectCollision collisionDetector = new DetectCollision();
	private HarmTarget dealDamage = new HarmTarget();
	private ArrayList<GameEntity> listOfEntities = new ArrayList<>();
	private double xSize;
	private double ySize;
    private double weaponAngle;
    private boolean isAttacking = false;
    private String direction = "right";
    
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
		    if(weaponHolder.getKinematics().getXVelocity() >= 0){
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
    	if(weaponHolder.getKinematics().getXVelocity() > 0){
    		direction = "right";
		}
		else if(weaponHolder.getKinematics().getXVelocity() < 0){
			direction = "left";
		}
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
    
    // i can be your angle or yuor devil
    public double getAngle(){
    	if(direction.equals("right")){
			return -1*weaponAngle;
		}
		else{
			return weaponAngle;
		}
    }
}