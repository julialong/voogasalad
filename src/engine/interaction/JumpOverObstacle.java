package engine.interaction;

import engine.controls.Jump;
import engine.entity.GameEntity;
import engine.entity.Player;
import engine.movement.Static;
import engine.physics.DetectCollision;

public class JumpOverObstacle implements Interaction{

	@Override
	public void interact(GameEntity source, GameEntity target) {
		// TODO Auto-generated method stub
		if(target.getMovementType() instanceof Static) {
			return;
		}
		//if(target instanceof Player) return;
//		Jump jump = new Jump();
//		jump.execute(target);
		String collisionType = new DetectCollision().detect(source, target);
		System.out.println("Collision Type: " + collisionType);
		System.out.println("Between " + source.getClass() + " and " + target.getClass());
		if((collisionType.equals("left")) || (collisionType.equals("right"))){
			Jump jump = new Jump();
			jump.execute(source);
			//source.setYVelocity(source.getJumpFactor());
		}
		
//		if (collisionType.equals("left"))
//		{
//			// Move right of target to left of source
////			target.overridePosition(source.getPosition()[0] - target.getSizeX(),target.getPosition()[1]);
//			//target.setX(source.getPosition()[0] - target.getSizeX());
//			
//		}
//		if (collisionType.equals("right"))
//		{
//			// Move left of target to right of source
////			target.overridePosition(source.getPosition()[0] + source.getSizeX(),target.getPosition()[1]);
//			//target.setX(source.getPosition()[0] + source.getSizeX());
//		}
	}

}
