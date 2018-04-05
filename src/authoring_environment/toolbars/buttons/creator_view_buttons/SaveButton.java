package authoring_environment.toolbars.buttons.creator_view_buttons;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 * The SaveButton, when clicked, triggers a drop-down menu that allows the user
 * to choose whether they would like to save wither a game or level for further
 * editing, or if they would like to publish their game so that it is playable
 * by users with the GamePlayer.
 *
 * @author julialong
 * Date started: April 01 18
 */
public class SaveButton extends MenuButton {

    private static final String SAVE = "Save";
    private static final String GAME = "Game";
    private static final String LEVEL = "Level";
    private static final String PUBLISH = "Publish";

    /**
     * Creates a simple Save menu button with the appropriate drop down items
     */
    public SaveButton() {
        super(SAVE);
        this.getItems().addAll(createGameItem(),
                                createLevelItem(),
                                createPublishItem());
    }

    private MenuItem createGameItem() {
        MenuItem gameItem = new MenuItem(GAME);
        // TODO: open game saver when menu item is clicked
        // gameItem.setOnAction(e -> new GameSaver());
        return gameItem;
    }

    private MenuItem createLevelItem() {
        MenuItem levelItem = new MenuItem(LEVEL);
        // TODO: open level saver when menu item is clicked
        // gameItem.setOnAction(e -> new LevelSaver());
        return levelItem;
    }

    private MenuItem createPublishItem() {
        MenuItem publishItem = new MenuItem(PUBLISH);
        // TODO: open publishing window when menu item is clicked
        // gameItem.setOnAction(e -> publishWindow());
        return publishItem;
    }

}
