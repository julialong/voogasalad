package engine.controls;

import engine.entity.GameEntity;

/**
 * Defines certain actions, mostly movements, that can be taken by an Entity
 * when commanded
 * 
 * @author Marcus Oertle and Robert Gitau
 *
 */
public abstract class Action{
	/**
	 * Carries out the given action on a given entity
	 * 
	 * @param entity
	 *            the entity that will perform this action
	 */
	public abstract void execute(GameEntity entity);
}