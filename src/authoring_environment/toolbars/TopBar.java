package authoring_environment.toolbars;

import authoring_environment.editor_windows.CreatorView;
import authoring_environment.game_elements.AuthoredGame;
import authoring_environment.toolbars.buttons.creator_view_buttons.*;
import javafx.scene.control.ToolBar;

/**
 * The TopBar class contains the relevant buttons needed to implement functionality of the
 * Graphic Authoring Environment, such as loading and saving games, inserting a level or
 * object, or modifying the general settings of the game.
 *
 * @author julialong
 * Date started: March 31 18
 */
public class TopBar extends ToolBar{

    private CreatorView myWindow;

    /**
     * Creates the basic TopBar toolbar and adds the necessary buttons
     */
    public TopBar(CreatorView window) {
        super();
        myWindow = window;
        addButtons();
    }

    /**
     * Adds desired buttons to the toolbar
     */
    private void addButtons() {
        this.getItems().addAll( new NewGameButton(),
                                new LaunchPlayerButton(myWindow),
                                new LoadButton(myWindow),
                                new SaveButton(myWindow),
                                new InsertButton(myWindow),
                                new SettingsButton(myWindow));
    }
}
