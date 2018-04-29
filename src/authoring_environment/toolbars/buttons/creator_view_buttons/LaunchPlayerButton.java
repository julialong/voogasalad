package authoring_environment.toolbars.buttons.creator_view_buttons;

import authoring_environment.editor_windows.CreatorView;
import javafx.scene.control.Button;

/**
 * Allows the user to launch the game from the authoring environment.
 *
 * @author Julia Long
 * Date started: April 29 18
 */
public class LaunchPlayerButton extends Button {

    private static final String PLAY = "Launch player";

    private CreatorView myWindow;

    /**
     * Creates a new
     * @param window the window that contains this button
     */
    public LaunchPlayerButton(CreatorView window) {
        super(PLAY);
        myWindow = window;
        this.setOnMouseClicked(e -> setAction());
    }

    private void setAction() {
        myWindow.getGame().update();
        // TODO: launch game
    }

}
