package engine.entity;

import engine.behavior.Behavior;
import engine.interaction.Interaction;
import engine.movement.Movement;
import engine.movement.Static;
import engine.physics.Kinematics;
/**
 * Defines GameObjects that tend to move and/or can be jumped through and onto by other Entities.
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class Platform extends GameObject {
	public Platform() {
		this(0,0);	
	}
	
	public Platform(double x, double y) {
		movementType =  new Static();
		kinematics = new Kinematics(x,y,0,0,0,0);
	}
}
