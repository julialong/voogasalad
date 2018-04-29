package authoring_environment.toolbars.buttons.creator_view_buttons;

import authoring_environment.editor_windows.AttributeEditor;
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
        this.setOnAction(e -> new AttributeEditor());

    }

}
