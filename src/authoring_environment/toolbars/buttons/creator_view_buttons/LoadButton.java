package authoring_environment.toolbars.buttons.creator_view_buttons;

import authoring_environment.editor_windows.CreatorView;
import authoring_environment.editor_windows.EditorWindow;
import authoring_environment.editor_windows.GameChooser;
import authoring_environment.editor_windows.LevelLoader;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 * The LoadButton, when clicked, triggers a drop-down menu that allows the user
 * to choose whether they would like to load a game or level for editing
 *
 * @author Julia Long
 * Date started: April 01 18
 */
public class LoadButton extends MenuButton {

    private CreatorView myWindow;

    private static final String LOAD = "Load";
    private static final String GAME = "Game";
    private static final String LEVEL = "Level";

    /**
     * Creates a simple Load menu button with the appropriate drop down items
     */
    public LoadButton(CreatorView window) {
        super(LOAD);
        myWindow = window;
        this.getItems().addAll(createGameItem(),
                                createLevelItem());
    }

    private MenuItem createGameItem() {
        MenuItem gameItem = new MenuItem(GAME);
        gameItem.setOnAction(e -> new GameChooser(myWindow));
        return gameItem;
    }

    private MenuItem createLevelItem() {
        MenuItem levelItem = new MenuItem(LEVEL);
        levelItem.setOnAction(e -> new LevelLoader(myWindow));
        return levelItem;
    }
}
