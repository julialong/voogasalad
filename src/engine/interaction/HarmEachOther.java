package engine.interaction;

import engine.entity.GameEntity;

/**
 * Both Objects in contact damage each other
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class HarmEachOther implements Interaction{

	@Override
	public void interact(GameEntity source, GameEntity target) {
		source.setHealth(source.getHealth()-1);
		target.setHealth(target.getHealth()-1);
	}

}
