package authoring_environment.toolbars.buttons.creator_view_buttons;

import javafx.scene.control.Button;

public class zoomInButton extends Button {

	private static final String ADD_ELEMENT= "Zoom In";

    /**
     * Creates a simple add element button.
     */
    public zoomInButton() {
        super(ADD_ELEMENT);
        // TODO: open element editor when button is clicked
        // this.setOnAction(e -> new AuthoredElementCreator());
    }
}
