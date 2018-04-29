package authoring_environment.toolbars.buttons.creator_view_buttons;

import authoring_environment.editor_windows.CreatorView;
import javafx.scene.control.Button;

/**
 * Allows the user
 */
public class LaunchPlayerButton extends Button {

    private static final String PLAY = "Launch player";

    private CreatorView myWindow;

    public LaunchPlayerButton(CreatorView window) {
        super(PLAY);
        this.setOnMouseClicked(e -> setAction());
    }

    private void setAction() {

    }

}
