package engine.interaction;

import engine.entity.GameEntity;
import engine.entity.Player;

/**
 * Removes the source entity on contact with a player
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class RemoveOnInteractWithPlayer implements Interaction{

	@Override
	public void interact(GameEntity source, GameEntity target) {
		if(!(target instanceof Player)) return;
		source.setHealth(0);
	}

}
