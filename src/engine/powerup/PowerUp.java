package engine.powerup;

import engine.entity.Player;

/**
 * Defines power ups that grant special effects on the player on contact for a set duration.
 * @author Marcus Oertle and Robert Gitau
 *
 */
public interface PowerUp {

    /**
     * Activates the power up and modifies the entity's list
     */
    public abstract void activate();
    
    /**
     * Deactivates the power up and modifies the entity's list
     */
    public abstract void deactivate();
    
	/**
	 * Sets the duration a power up lasts for
	 * @param time - the duration time in seconds
	 */
	public abstract void setDuration(double time);
	
	/**
	 * Sets the object that the PowerUp is interacting with
	 * @param gameEntity - the object that the PowerUp is interacting with
	 */
	public abstract void setPlayer(Player player);
	
	/**
	 * Updates the powerup at each frame
	 * @return true if should be removed, false otherwise
	 */
	public abstract boolean update();
}