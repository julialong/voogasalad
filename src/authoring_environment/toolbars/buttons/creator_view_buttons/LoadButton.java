package authoring_environment.toolbars.buttons.creator_view_buttons;

import authoring_environment.editor_windows.CreatorView;
<<<<<<< HEAD
import authoring_environment.editor_windows.GameChooser;
import data.resources.DataFileException;
=======
import authoring_environment.editor_windows.game_windows.GameChooser;
import authoring_environment.editor_windows.level_windows.LevelLoader;
>>>>>>> 1409256eea8f979e35d97150101010cc7c573a25
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
<<<<<<< HEAD
        // TODO: open game chooser when menu item is clicked
        gameItem.setOnAction(e -> {
			try {
				new GameChooser();
			} catch (DataFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
=======
        gameItem.setOnAction(e -> new GameChooser(myWindow));
>>>>>>> 1409256eea8f979e35d97150101010cc7c573a25
        return gameItem;
    }

    private MenuItem createLevelItem() {
        MenuItem levelItem = new MenuItem(LEVEL);
        levelItem.setOnAction(e -> new LevelLoader(myWindow));
        return levelItem;
    }
}
