/**
 * Interface for PowerUp type classes that can be utilized by the PlayerCharacter
 * @author Marcus
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