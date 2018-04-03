package authoring_environment.toolbars.buttons;

import java.net.MalformedURLException;

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
     * Creates a simple add element button.
     */
    public AddElementButton() {
        super(ADD_ELEMENT);
        // TODO: open element editor when button is clicked
        this.setOnAction(e -> {
			try {
				new AuthoredElementCreator();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
    }

}
