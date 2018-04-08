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

	/**
	 * Returns a factor that determines the acceleration of the entity in any
	 * given direction
	 * 
	 * @return a double corresponding to this factor
	 */
	public abstract double getSpeedFactor();

	/**
	 * Returns an array of the Entity's position.
	 * 
	 * @return an array, where the x position is index 0 and the y position is
	 *         index 1
	 */
	public abstract double[] getPosition();

	/**
	 * Returns a factor that determines the velocity of an entity when it jumps
	 * 
	 * @return a double corresponding to that factor
	 */
	public abstract double getJumpFactor();

	/**
	 * Overrides the position of the object.
	 * 
	 * @param x
	 *            the desired X position
	 * @param y
	 *            the desired Y position
	 */
	public void overridePosition(double x, double y);

	/**
	 * Sets the object's velocity in the x direction (+x is right)
	 * 
	 * @param velocity
	 *            the desired x velocity
	 */
	public void setXVelocity(double velocity);

	/**
	 * Sets the object's velocity in the y direction (+y is up)
	 * 
	 * @param velocity
	 *            the desired y velocity
	 */
	public void setYVelocity(double velocity);

	/**
	 * Sets the object's acceleration constant in the x direction. Note that
	 * this is influenced by friction.
	 * 
	 * @param accel
	 *            the desired x acceleration
	 */
	public void setXAcceleration(double accel);

	/**
	 * Sets the object's acceleration constant in the y direction. Note that
	 * this is influenced by gravity.
	 * 
	 * @param accel
	 *            the desired y acceleration
	 */
	public void setYAcceleration(double accel);

	/**
	 * Returns the Movement of this Entity
	 * 
	 * @return the Entity's Movement
	 */
	public abstract Movement getMovementType();
	
	/**
	 * Sets the maximum speed the Entity can move in the x direction
	 * @param velocity the desired maximum speed
	 */
	public abstract void setMaxXVelocity(double velocity);
	
	/**
	 * Sets the maximum speed the Entity can move in the y direction
	 * @param velocity the desired maximum speed
	 */
	public abstract void setMaxYVelocity(double velocity);
	
	public abstract void update();
}
