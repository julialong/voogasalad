package engine.interaction;

import engine.entity.GameEntity;
import engine.entity.Player;
import engine.physics.DetectCollision;

/**
 * If the target is a PlayerCharacter, the player moves to the next level.
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class Goal implements Interaction{

	@Override
	public void interact(GameEntity source, GameEntity target) {
        if(!(target instanceof Player)) return;
		String collisionType = new DetectCollision().detect(source, target);
        ((Player) target).setLevelComplete(!collisionType.equals("none"));
	}

}
