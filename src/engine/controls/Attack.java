package engine.controls;

import engine.entity.GameEntity;
import engine.entity.Player;

/**
 * Allows a player to use the weapon they have equipped
 * 
 * @author Marcus Oertle
 *
 */
public class Attack extends Action {

	@Override
	public void execute(GameEntity entity) {
		if (!(entity instanceof Player))
			return;
		((Player) entity).useWeapon();
	}

}
