package engine.interaction;

import engine.entity.GameEntity;
import engine.physics.DetectCollision;

/**
 * Prevents target object from clipping through the source unless it jumps through the bottom
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class Platform implements Interaction{

	@Override
	public void interact(GameEntity source, GameEntity target){
		
		String collisionType = new DetectCollision().detect(source, target);
		
		if(!collisionType.equals("bottom")) {
			new PreventClipping().interact(source, target);
		}
	}
}