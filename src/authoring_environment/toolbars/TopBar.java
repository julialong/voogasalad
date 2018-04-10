package authoring_environment.toolbars;

import authoring_environment.AuthoredGame;
import authoring_environment.toolbars.buttons.creator_view_buttons.InsertButton;
import authoring_environment.toolbars.buttons.creator_view_buttons.LoadButton;
import authoring_environment.toolbars.buttons.creator_view_buttons.SaveButton;
import authoring_environment.toolbars.buttons.creator_view_buttons.SettingsButton;
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

    private AuthoredGame myGame;

    /**
     * Creates the basic TopBar toolbar and adds the necessary buttons
     */
    public TopBar(AuthoredGame game) {
        super();
        myGame = game;
        addButtons();
    }

    /**
     * Adds desired buttons to the toolbar
     */
    private void addButtons() {
        this.getItems().addAll( new LoadButton(),
                                new SaveButton(myGame),
                                new InsertButton(myGame),
                                new SettingsButton());
    }
}
