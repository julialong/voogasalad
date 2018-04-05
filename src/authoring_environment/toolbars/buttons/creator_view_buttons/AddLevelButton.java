package authoring_environment.toolbars.buttons.creator_view_buttons;

import authoring_environment.editor_windows.LevelCreator;
import javafx.scene.control.Button;

/**
 * The AddLevelButton, when clicked, will trigger the Level Creator that allows
 * users to create a new level to add to their game.
 *
 * @author Julia Long
 * Date started: April 02 18
 */
public class AddLevelButton extends Button {

    private static final String ADD_LEVEL= "Create new level";

    /**
     * Creates a simple add element button.
     */
    public AddLevelButton() {
        super(ADD_LEVEL);
        this.setOnAction(e -> new LevelCreator());
    }

}
