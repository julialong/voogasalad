package engine.weapon;

import engine.entity.GameEntity;
import engine.level.Level;

/**
 * No weapon. Cannot deal damage at all.
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class NoWeapon extends WeaponBase{

	public NoWeapon(GameEntity entity, Level level) {
		super(entity, level);
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

}
