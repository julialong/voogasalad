package authoring_environment.toolbars;

import authoring_environment.toolbars.buttons.LoadButton;
import authoring_environment.toolbars.buttons.SaveButton;
import javafx.scene.control.ToolBar;

/**
 * The TopBar class contains the relevant buttons needed to implement functionality of the
 * Graphic Authoring Environment, such as loading and saving games, inserting a level or
 * object, or modifying the general settings of the game.
 *
 * @author julialong
 */
public class TopBar extends ToolBar{

    /**
     * Creates the basic TopBar toolbar and adds the necessary buttons
     */
    public TopBar() {
        super();
        addButtons();
    }

    /**
     * Adds desired buttons to the toolbar
     */
    private void addButtons() {
        this.getItems().addAll( new LoadButton(),
                                new SaveButton());
    }
}
