package engine.interaction;

import engine.entity.GameEntity;
import engine.movement.Static;
import engine.physics.DetectCollision;
import engine.weapon.Weapon;

/**
 * Prevents target object from clipping through the source
 * 
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class PreventClipping implements Interaction {

	@Override
	public void interact(GameEntity source, GameEntity target) {

		if (target.getMovementType() instanceof Static || target instanceof Weapon) {
			return;
		}
		String collisionType = new DetectCollision().detect(source, target);

		if (collisionType.equals("top")) {
			target.setY(source.getPosition()[1] + target.getSizeY());
			target.setYVelocity(0);
		}
		if (collisionType.equals("bottom")) {
			target.setY(source.getPosition()[1] - target.getSizeY());
			target.setYVelocity(0);
		}
		if (collisionType.equals("left")) {
			target.setX(source.getPosition()[0] - target.getSizeX());
		}
		if (collisionType.equals("right")) {
			target.setX(source.getPosition()[0] + source.getSizeX());
		}
	}
}