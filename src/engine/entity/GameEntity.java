package engine.entity;

import engine.movement.Movement;

/**
 * Interface for "entities" in a game: player characters, enemies, and objects.
 * The methods here are essentially shared by all forms of entities.
 * 
 * @author Robert Gitau and Marcus Oertle
 *
 */
public interface GameEntity {
	/**
	 * Used to move the entity, given an initial velocity, using the internal
	 * physics engine.
	 * 
	 * @param velocity:
	 *            the initial the object should move in the x direction
	 */
	public abstract void moveX(double velocity);

	/**
	 * Used to move the entity, given an initial velocity, using the internal
	 * physics engine.
	 * 
	 * @param velocity:
	 *            the initial the object should move in the y direction
	 */
	public abstract void moveY(double velocity);

	/**
	 * Defines how the entity moves, namely the entity's base speed and weight.
	 * 
	 * @param movement:
	 *            the entity's movement type, an implementation of the Movement
	 *            interface.
	 */
	public abstract void setMovementType(Movement movement);

	/**
	 * Sets the entity's current health, which ends the game/ loses a life if it
	 * reaches 0.
	 * 
	 * @param HP:
	 *            an int representing what the HP value should be set to.
	 */
	public abstract void setHealth(int HP);

	/**
	 * Defines how the entity reacts to the Object given, if the object is an
	 * Enemy, GameObject, PowerUp, or Projection.
	 * 
	 * @param o:
	 *            an Object to be interacted with.
	 */
	public abstract void setInteraction(Object o);
}
