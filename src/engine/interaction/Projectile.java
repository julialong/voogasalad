package engine.interaction;

import engine.entity.Block;
import engine.entity.GameEntity;
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
		source.setHealth(0);
	}
	
	public void setOwner(GameEntity owner){
		this.owner = owner;
	}
	
	public void setDamage(int damage){
		this.damage = damage;
	}
}
