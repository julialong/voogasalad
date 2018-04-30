package authoring_environment.attribute_editor;

import javafx.scene.Group;
import javafx.scene.layout.VBox;

public class MovementAttribute extends Attribute{
    private static final String MOVEMENT = "Movement";

    public MovementAttribute(Group targetLocation){
        targetLocation.getChildren().add(setupInputs(MOVEMENT));
    }

    @Override
    public VBox setupInputs(String attributeType) {
        VBox container = new VBox();

        return null;
    }
}
