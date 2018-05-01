package authoring_environment.attribute_editor;

import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This subclass represents the movement attribute that game entities can have.
 * Since a game entity may only have one movement attribute this subclass
 * is implemented through the use of a single selection combobox.
 *
 * @Author Judi Sanchez, Dorian Barber
 */
public class MovementAttribute extends Attribute{

    private static final String MOVEMENT = "Movement";
    private ComboBox<String> movementChooser;

    /**
     * Creates the movement attribute which holds the options for
     * what movement a game entity can have.
     */
    public MovementAttribute(Group targetLocation){
        movementChooser = new ComboBox<String>();
        targetLocation.getChildren().add(setupInputs(MOVEMENT));
    }


    /**
     * Creates a container which actually only contains a single selection combobox.
     * This combobox determines what movement the game entity has.
     */
    @Override
    public VBox setupInputs(String attributeType) {
        VBox container = new VBox();
        HBox inputLine = new HBox();
        Label label = new Label(attributeType);
        List<String> movementAttributeOptions = super.loadAttributes(attributeType);
        for(String movementOption : movementAttributeOptions){
            movementChooser.getItems().add(movementOption);
        }
        inputLine.getChildren().add(label);
        inputLine.getChildren().add(movementChooser);
        container.getChildren().add(inputLine);
        return container;
    }


    /**
     * Returns a map of option to a list of data fields that the user inputs.
     * If there are no data fields to input then it is an empty list.
     */
    @Override
    public Map<String, List<String>> getAttributeContent() {
        Map<String, List<String>> contents = new HashMap<>();
        String pickedMovement = (String) movementChooser.getValue();
        contents.put(pickedMovement, new ArrayList<>());
        return contents;
    }

	@Override
	public String returnName() {
		return MOVEMENT;
	}
}
