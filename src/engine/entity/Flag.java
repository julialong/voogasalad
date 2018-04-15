package engine.entity;

import engine.behavior.Behavior;
import engine.interaction.Interaction;
import engine.movement.Movement;
import engine.movement.Static;
import engine.physics.Kinematics;
/**
 * Defines GameObjects that are generally vertical and thin, and can be passed through in almost any direction.
 * Usually, these would be goals or checkpoints.
 * @author Robert Gitau and Marcus Oertle.
 *
 */
public class Flag extends GameObject{
	public Flag() {
		this(0,0);	
	}
	
	public Flag(double x, double y) {
		movementType =  new Static();
		kinematics = new Kinematics(x,y,0,0,0,0);
	}
}
