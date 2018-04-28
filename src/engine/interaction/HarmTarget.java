package engine.interaction;

import engine.entity.GameEntity;

/**
 * The source harms the target.
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class HarmTarget implements Interaction{

	@Override
	public void interact(GameEntity source, GameEntity target) {
		target.setHealth(target.getHealth()-1);
	}

}
