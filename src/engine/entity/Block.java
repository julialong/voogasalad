package engine.entity;

import engine.movement.Static;
import engine.physics.Kinematics;
/**
 * Defines GameObjects that are blocks found in most platformers.
 * Intended to be used for terrain and blocks that can be struck by the player to either break or grant powerups.
 * @author Robert Gitau and Marcus Oertle.
 *
 */
public class Block extends GameObject{
	
	public Block() {
		this(0,0);	
	}
	
	public Block(double x, double y) {
		movementType =  new Static();
		kinematics = new Kinematics(x,y,0,0,0,0);
	}
}
