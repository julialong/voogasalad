package engine.interaction;

import engine.entity.GameEntity;
import engine.movement.Static;
import engine.physics.DetectCollision;

/**
 * Prevents target object from clipping through the source
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class PreventClipping implements Interaction{

	@Override
	public void interact(GameEntity source, GameEntity target){
		// for example: source = block, target = player, player is then 
		// stopped by the block so it cannot fall
		
//		System.out.println("PreventClipping:");
		
		if(target.getMovementType() instanceof Static) {
			return;
		}
		String collisionType = new DetectCollision().detect(source, target);
		
		if (collisionType.equals("top"))
		{
			// Move bottom of target to top of source
//			target.overridePosition(target.getPosition()[0], source.getPosition()[1] + target.getSizeY());
			target.setY(source.getPosition()[1] + target.getSizeY());
			target.setYVelocity(0);
		}
		if (collisionType.equals("bottom"))                        
		{
			// Move top of target to bottom of source
//			target.overridePosition(target.getPosition()[0], source.getPosition()[1] - target.getSizeY());
			target.setY(source.getPosition()[1] - target.getSizeY());
			target.setYVelocity(0);
		}
		if (collisionType.equals("left"))
		{
			// Move right of target to left of source
//			target.overridePosition(source.getPosition()[0] - target.getSizeX(),target.getPosition()[1]);
			target.setX(source.getPosition()[0] - target.getSizeX());
		}
		if (collisionType.equals("right"))
		{
			// Move left of target to right of source
//			target.overridePosition(source.getPosition()[0] + source.getSizeX(),target.getPosition()[1]);
			target.setX(source.getPosition()[0] + source.getSizeX());
		}
	}
}