/**
 * Defines GameObjects, objects that players and/or enemies can interact with, but usually don't actively try to hurt the player
 */
public interface GameObject {
	/**
	 * Defines how the GameObject behaves with regard to the player (ie makes the player slide across it)
	 * 
	 * @param behavior:
	 *            the enemies Behavior type.
	 */
	public abstract void setBehavior(Behavior behavior);
}