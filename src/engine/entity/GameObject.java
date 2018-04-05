package engine.entity;

import engine.behavior.Behavior;

public abstract class GameObject implements GameEntity{
	/**
	 * Defines how the GameObject behaves with regard to the player (ie makes the player slide across it)
	 * 
	 * @param behavior:
	 *            the enemies Behavior type.
	 */
	public abstract void setBehavior(Behavior behavior);
}
