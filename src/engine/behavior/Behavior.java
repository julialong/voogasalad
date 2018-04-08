package engine.behavior;

import engine.entity.GameEntity;

/**
 * Defines how an object reacts to another object, usually with regards to a
 * PlayerCharacter. Mostly for enemy AI.
 * 
 * @author Robert Gitau and Marcus Oertle
 *
 */
public interface Behavior {
	/**
	 * Carries out the logic that determines what action the Non-Player Entity
	 * will take.
	 * 
	 * @param entity
	 *            the entity that will carry out this behavior
	 */
	public abstract void behave(GameEntity entity);
}
