package engine.interaction;

import engine.entity.GameEntity;

/**
 * Prevents target object from the source
 * Code based on: https://stackoverflow.com/questions/5062833/detecting-the-direction-of-a-collision
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class PreventClipping implements Interaction{

	@Override
	public void interact(GameEntity source, GameEntity target){
		// NOTE TO US: MAJOR REFACTORING LIKELY A GOOD IDEA TO DO THIS CODE IN LEVEL
		// AND PASS THE DIRECTION OF INTERACTION THE INTERACT METHOD
		
		// for example: source = block, target = player, player is then 
		// stopped by the block so it cannot fall
		double sourceXSize = source.getSizeX();
		double sourceYSize = source.getSizeY();
		double targetXSize = target.getSizeX();
		double targetYSize = target.getSizeY();

		double sourceTop = source.getPosition()[1];
		double sourceBottom = source.getPosition()[1] - sourceYSize;
		double sourceLeft = source.getPosition()[0];
		double sourceRight = source.getPosition()[0] + sourceXSize;

		double target_bottom = target.getPosition()[1] - targetYSize;
		double source_bottom = source.getPosition()[1] - sourceYSize;
		double target_right = target.getPosition()[0] + targetXSize;
		double source_right = source.getPosition()[0] + sourceXSize;
		
//		System.out.println("target_bottom = " + target_bottom);
//		System.out.println("source_bottom = " + source_bottom);
//		System.out.println("target_right = " + target_right);
//		System.out.println("source_right = " + source_right);

		double b_collision = -(source_bottom - target.getPosition()[1]);
		double t_collision = -(target_bottom - source.getPosition()[1]);
		double l_collision = target_right - source.getPosition()[0];
		double r_collision = source_right - target.getPosition()[0];

//		System.out.println("bc = " + b_collision);
//		System.out.println("tc = " + t_collision);
//		System.out.println("lc = " + l_collision);
//		System.out.println("rc = " + r_collision);
		
		if (t_collision < b_collision && t_collision < l_collision && t_collision < r_collision )
		{                           
			// Top collision
//			System.out.println("top");
			target.overridePosition(target.getPosition()[0], sourceTop + targetYSize);
		}
		if (b_collision < t_collision && b_collision < l_collision && b_collision < r_collision)                        
		{
			// Bottom collision
//			System.out.println("bottom");
			target.overridePosition(target.getPosition()[0], sourceBottom);
		}
		if (l_collision < r_collision && l_collision < t_collision && l_collision < b_collision)
		{
			// Left collision
//			System.out.println("left");
			target.overridePosition(sourceLeft - targetXSize,target.getPosition()[1]);
		}
		if (r_collision < l_collision && r_collision < t_collision && r_collision < b_collision )
		{
			// Right collision
//			System.out.println("right");
			target.overridePosition(sourceRight,target.getPosition()[1]);
		}
	}
}