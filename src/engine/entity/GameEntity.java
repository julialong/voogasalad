package engine.entity;

import engine.interaction.Interaction;
import engine.movement.Movement;
import engine.physics.Kinematics;

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
	 * Returns the health of the entity
	 */
	public abstract int getHealth();

	/**
	 * Defines how the entity reacts to the Object given, if the object is an
	 * Enemy, GameObject, PowerUp, or Projection.
	 * 
	 * @param interaction:
	 *            an instance of an Interaction class
	 */
	public abstract void addInteraction(Interaction interaction);

	/**
	 * 
	 */
	public abstract void interact(GameEntity source, GameEntity target);

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
	 * Sets the speed factor
	 * @param speedFactor
	 */
	public void setSpeedFactor(double speedFactor);

	/**
	 * Sets the jump factor
	 * @param jumpFactor
	 * @return
	 */
	public void setJumpFactor(double jumpFactor);

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

	/**
	 * Sets the friction constant for the entity
	 * @param frictionConstant - the constant for friction
	 */
	public abstract void setFrictionConstant(double frictionConstant);

	/**
	 * Sets the width of the entity
	 * @param x - the width
	 */
	public abstract void setSizeX(double x);

	/**
	 * Sets the height of the entity
	 * @param y - the height
	 */
	public abstract void setSizeY(double y);

	/**
	 * Gets the width of the entity
	 */
	public abstract double getSizeX();

	/**
	 * Gets the height of the entity
	 */
	public abstract double getSizeY();

	/**
	 * Returns the kinematics object
	 */
	public abstract Kinematics getKinematics();
	
	/**
	 * Sets the image path
	 */
	public abstract void setImagePath(String path);
	
	/**
	 * Returns the image path
	 */
	public abstract String getImagePath();

	/**
	 * Updates the entity with new position/interaction paramters. Called at every time step.
	 */
	public abstract void update();
}
