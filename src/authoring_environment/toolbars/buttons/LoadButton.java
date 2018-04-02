package authoring_environment.toolbars.buttons;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 * The LoadButton, when clicked, triggers a drop-down menu that allows the user
 * to choose whether they would like to load a game or level for editing
 *
 * @author julialong
 * Date started: April 01 18
 */
public class LoadButton extends MenuButton {

    private static final String LOAD = "Load";
    private static final String GAME = "Game";
    private static final String LEVEL = "Level";

    /**
     * Creates a simple Load menu button with the appropriate drop down items
     */
    public LoadButton() {
        super(LOAD);
        this.getItems().addAll(createGameItem(),
                                createLevelItem());
    }

    private MenuItem createGameItem() {
        MenuItem gameItem = new MenuItem(GAME);
        // TODO: open game chooser when menu item is clicked
        // gameItem.setOnAction(e -> new GameChooser());
        return gameItem;
    }

    private MenuItem createLevelItem() {
        MenuItem levelItem = new MenuItem(LEVEL);
        // TODO: open level chooser when menu item is clicked
        // gameItem.setOnAction(e -> new LevelChooser());
        return levelItem;
    }
}
