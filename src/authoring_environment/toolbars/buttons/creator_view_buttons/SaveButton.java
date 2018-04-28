package authoring_environment.toolbars.buttons.creator_view_buttons;

import authoring_environment.editor_windows.CreatorView;
import authoring_environment.editor_windows.level_windows.LevelSaver;
import authoring_environment.editor_windows.game_windows.GameSaver;
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

    private CreatorView myWindow;

    private static final String SAVE = "Save";
    private static final String GAME = "Game";
    private static final String LEVEL = "Level";

    /**
     * Creates a simple Save menu button with the appropriate drop down items
     */
    public SaveButton(CreatorView window) {
        super(SAVE);
        myWindow = window;
        this.getItems().addAll(createGameItem(), createLevelItem());
    }

    private MenuItem createGameItem() {
        MenuItem gameItem = new MenuItem(GAME);
        gameItem.setOnAction(e -> new GameSaver(myWindow));
        return gameItem;
    }

    private MenuItem createLevelItem() {
        MenuItem levelItem = new MenuItem(LEVEL);
        levelItem.setOnAction(e -> new LevelSaver(myWindow));
        return levelItem;
    }

}
