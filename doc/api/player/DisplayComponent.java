package api.player;

/**
 * This interface represents each component in the HUD.
 * They will be able to be updated universally during the
 * actual game loop.
 * These components might utilize the Factory design pattern
 */
public interface DisplayComponent{
    /**
     * Updates this single HUD component depending on what specific component it is.
     */
    public void update();
}