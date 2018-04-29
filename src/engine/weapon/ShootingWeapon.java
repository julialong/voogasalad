package engine.weapon;

import java.util.ArrayList;
import java.util.List;

import engine.behavior.Behavior;
import engine.behavior.MoveForward;
import engine.entity.Block;
import engine.entity.GameEntity;
import engine.entity.GameObject;
import engine.interaction.Projectile;
import engine.level.Level;
import engine.movement.Flying;
import engine.movement.Movement;
import engine.physics.Kinematics;

/**
 * A Weapon that fires Projectiles
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class ShootingWeapon extends WeaponBase{
	private static final double DEFAULT_WEAPON_ANGLE = 0;
    private static final double WEAPON_ANGLE_INCREMENT = 0;
    //private Block projectile;
    private Movement projectileMovement;
    private List<Behavior> projectileBehavior;
    private int projectileDamage = 1;
    private double projectileWidth = 5;
    private double projectileHeight = 5;
    private double projectileSpeed = 100;
    
	public ShootingWeapon(GameEntity entity, Level level){
		super(entity, level);
		hitBox.setSizeX(xHolderSize*2);
		hitBox.setSizeY(yHolderSize - 2*offset);
        width = xHolderSize*2;
        height = yHolderSize/4;
        weaponAngle = DEFAULT_WEAPON_ANGLE;
        rightXOffset = xHolderSize/10;
        leftXOffset = width - 9*(xHolderSize/10);
        yOffset = yHolderSize/3;
        //projectile = new Block();
		projectileMovement = new Flying();
		projectileBehavior = new ArrayList<Behavior>();
		projectileBehavior.add(new MoveForward());
	}
	
	public ShootingWeapon(GameEntity entity, Level level, Movement movement, List<Behavior> behavior, int damage, double xSize, double ySize, double speed){
		this(entity, level);
		projectileMovement = movement;
		projectileBehavior = behavior;
		projectileDamage = damage;
		projectileWidth = xSize;
		projectileHeight = ySize;
		projectileSpeed = speed;
	}
	
	@Override
	public void attack() {
//        if(!isAttacking){
//            isAttacking = true;
//		    if(direction.equals("right")){
//			    hitBox.setX(holderXPos+xHolderSize);
//		    }
//		    else{
//			    hitBox.setX(holderXPos-xHolderSize);
//		    }
//            hitBox.setY(holderYPos - offset);
//            iterateEntities();
//        }
		Block projectile = new Block();
		projectile.setSizeX(projectileWidth);
		projectile.setSizeY(projectileHeight);
		if(direction.equals("right")){
			projectile.overridePosition(kinematics.getX() + width, kinematics.getY());
			projectile.setXVelocity(projectileSpeed);
		} else {
			projectile.overridePosition(kinematics.getX() - projectileWidth, kinematics.getY());
			projectile.setXVelocity(-projectileSpeed);
		}
		projectile.setMaxXVelocity(projectileSpeed);
		projectile.setMovementType(projectileMovement);
		for(Behavior b : projectileBehavior) projectile.addBehavior(b);
		projectile.addInteraction(new Projectile(weaponHolder,projectileDamage));
		level.addObject(projectile);
	}
    
	@Override
    public void update() {
		updateDirectionality();
//        if(isAttacking){
//            weaponAngle -= WEAPON_ANGLE_INCREMENT;
//            if(weaponAngle < -DEFAULT_WEAPON_ANGLE){
//                weaponAngle = DEFAULT_WEAPON_ANGLE;
//                isAttacking = false;
//            }
//        }
    }
}
