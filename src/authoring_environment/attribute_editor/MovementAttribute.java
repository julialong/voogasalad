package authoring_environment.attribute_editor;

import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class MovementAttribute extends Attribute{
    private static final String MOVEMENT = "Movement";

    public MovementAttribute(Group targetLocation){
        targetLocation.getChildren().add(setupInputs(MOVEMENT));
    }

    @Override
    public VBox setupInputs(String attributeType) {
        VBox container = new VBox();
        HBox inputLine = new HBox();
        Label label = new Label(attributeType);
        ComboBox movementChooser = new ComboBox();
        List<String> movementAttributeOptions = super.loadAttributes(attributeType);
        for(String movementOption : movementAttributeOptions){
            movementChooser.getItems().add(movementOption);
        }
        inputLine.getChildren().add(label);
        inputLine.getChildren().add(movementChooser);
        container.getChildren().add(inputLine);
        return container;
    }
}
