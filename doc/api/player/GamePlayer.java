package api.player;

/**
 * Main application for the GamePlayer module. Within the GamePlayer
 * is the MenuBar and the GameView.
 */
public interface GamePlayer{

    /**
     * Reload the GamePlayer with a new version of a game.
     * Primary use case: occurs after the user opens the editor
     * @return new updated GameView
     */
    public GameView reload();
}