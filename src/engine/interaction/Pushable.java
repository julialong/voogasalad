package engine.interaction;

import engine.entity.Block;
import engine.entity.GameEntity;
import engine.entity.Player;
import engine.physics.DetectCollision;
import engine.weapon.Weapon;

/**
 * Makes the source able to be pushed around
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class Pushable implements Interaction {

	@Override
	public void interact(GameEntity source, GameEntity target){
		// for example: source = block, target = player, player is then 
		// stopped by the block so it cannot fall

		if(target instanceof Block || target instanceof Weapon) return;

		//		System.out.println("Pushable:");
		String collisionType = new DetectCollision().detect(source, target);

		if (collisionType.equals("top"))
		{
			// Move bottom of target to top of source
			//target.setY(source.getPosition()[1] + target.getSizeY());
			boolean push = true;
			for(GameEntity ge : source.getInteractionMap().keySet()) {
				if(source.getInteractionMap().get(ge).equals("bottom")) {
					for(Interaction i : ge.getInteractions()) {
						if(i instanceof PreventClipping) {
							push = false;
							target.setY(source.getPosition()[1] + target.getSizeY());
							target.setYVelocity(0);
						}
					}
				}
			}
			if(push) {
				source.setY(target.getPosition()[1] - target.getSizeX());
			}
		}
		if (collisionType.equals("bottom"))                        
		{
			// Move top of target to bottom of source
			//target.setY(source.getPosition()[1] - target.getSizeY());
			boolean push = true;
			for(GameEntity ge : source.getInteractionMap().keySet()) {
				if(source.getInteractionMap().get(ge).equals("top")) {
					for(Interaction i : ge.getInteractions()) {
						if(i instanceof PreventClipping) {
							push = false;
							target.setY(source.getPosition()[1] - target.getSizeY());
						}
					}
				}
			}
			if(push) {
				source.setY(target.getPosition()[1] + target.getSizeX());
			}
		}
		if (collisionType.equals("left"))
		{
			// Move right of target to left of source
			boolean push = true;
			for(GameEntity ge : source.getInteractionMap().keySet()) {
				if(source.getInteractionMap().get(ge).equals("right")) {
					for(Interaction i : ge.getInteractions()) {
						if(i instanceof PreventClipping) {
							push = false;
							target.setX(source.getPosition()[0] - source.getSizeX());
						}
					}
				}
			}
			if(push) {
				source.setX(target.getPosition()[0] + target.getSizeX());
			}
		}
		if (collisionType.equals("right"))
		{
			// Move left of target to right of source			
			boolean push = true;
			for(GameEntity ge : source.getInteractionMap().keySet()) {
				if(source.getInteractionMap().get(ge).equals("left")) {
					for(Interaction i : ge.getInteractions()) {
						if(i instanceof PreventClipping) {
							push = false;
							target.setX(source.getPosition()[0] + source.getSizeX());
						}
					}
				}
			}
			if(push) {
				source.setX(target.getPosition()[0] - target.getSizeX());
			}
		}
	}
}
