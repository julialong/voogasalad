package authoring_environment.toolbars.buttons;

import javafx.scene.control.MenuButton;

/**
 * The SaveButton, when clicked, triggers a drop-down menu that allows the user
 * to choose whether they would like to save wither a game or level for further
 * editing, or if they would like to publish their game so that it is playable
 * by users with the GamePlayer.
 *
 * @author julialong
 */
public class SaveButton extends MenuButton {

    private static final String SAVE = "Save";

    /**
     * Creates a simple Save menu button with the appropriate drop down items
     */
    public SaveButton() {
        super(SAVE);
    }
}
