package engine.controls;

import engine.entity.GameEntity;
import engine.entity.Player;

public class Attack extends Action {

	@Override
	public void execute(GameEntity entity) {
		if(!(entity instanceof Player)) return;
		((Player) entity).useWeapon();
	}

}
