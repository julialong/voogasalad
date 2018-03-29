package api.player;

/**
 * This interface represents the HUD component of the GameView.
 * The components of the HUD will be a series of DisplayComponents
 * that will share an interface such that the components will be
 * easily editable.
 */
public interface HeadsUpDisplay{

    /**
     * This method will be used for updating all the components in the heads up display.
     * All components of the current HUD will be updated depending on their specific
     * requirements.
     */
    public void updateComponents();
}