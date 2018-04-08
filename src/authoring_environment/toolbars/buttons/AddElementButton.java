package authoring_environment.toolbars.buttons;


import authoring_environment.AuthoredElementCreator;
import javafx.scene.control.Button;

/**
 * The AddElementButton, when clicked, will trigger the Element Editor that allows
 * users to create a new element to add to their game.
 *
 * @author julialong
 * Date started: April 01 18
 */
public class AddElementButton extends Button {

    private static final String ADD_ELEMENT= "Create new element";

    /**
     * Creates an upload image button
     */
    public AddElementButton() {
        super(ADD_ELEMENT);
        // TODO: open element editor when button is clicked
        this.setOnAction(e -> {
				new AuthoredElementCreator();
        }
       );
	
    }

}
