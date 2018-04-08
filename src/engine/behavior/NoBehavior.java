package engine.behavior;

import engine.entity.GameEntity;

/**
 * Behavior implementation that will do nothing (required since an entity must have a behavior)
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class NoBehavior implements Behavior{

	@Override
	public void behave(GameEntity entity) {
		// DO NOTHING
	}
}
