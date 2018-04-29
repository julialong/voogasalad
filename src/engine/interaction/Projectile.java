package engine.interaction;

import engine.entity.Block;
import engine.entity.GameEntity;
import engine.movement.Grounded;
import engine.physics.DetectCollision;
import engine.weapon.Weapon;

public class Projectile implements Interaction{
	private GameEntity owner;
	private int damage;
	
	public Projectile(GameEntity owner, int damage){
		this.owner = owner;
		this.damage = damage;
	}
	
	@Override
	public void interact(GameEntity source, GameEntity target) {
		if((target.equals(owner)) || (target instanceof Weapon)) return;
		if(target.getDestructible()) target.setHealth(target.getHealth()-damage);
		//System.out.println("Setting source of type " + source.getClass() + " to 0 HP.");
//		System.out.println("Collision of direction " + new DetectCollision().detect(source, target));
//		System.out.println("Source of type " + source.getClass() + " Target of type " + target.getClass());
//		source.setHealth(0);
		//System.out.println("Bullet Y Vel??? " + source.getKinematics().getYVelocity());
		bulletRemoval(source,target);
	}
	
	private void bulletRemoval(GameEntity source, GameEntity target){
		if(!(source.getMovementType() instanceof Grounded)){
			source.setHealth(0);
		}else if(!(new DetectCollision().detect(source, target).equals("bottom"))){
			source.setHealth(0);
		}
	}
	
	public void setOwner(GameEntity owner){
		this.owner = owner;
	}
	
	public void setDamage(int damage){
		this.damage = damage;
	}
}
