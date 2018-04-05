package engine.powerup;
/**
 * Defines power ups that grant special effects on the player on contact for a set duration.
 * @author Marcus Oertle and Robert Gitau
 *
 */
public interface PowerUp {
	/**
	 * Sets the duration a power up lasts for
	 * @param time - the duration time in milliseconds
	 */
	public abstract void setDuration(double time);
	
	/**
	 * Sets the object that the PowerUp is interacting with
	 * @param object - the object that the PowerUp is interacting with
	 */
	public abstract void setInteraction(Object object);
}
