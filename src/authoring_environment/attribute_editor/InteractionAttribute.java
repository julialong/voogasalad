package authoring_environment.attribute_editor;

import java.util.List;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class InteractionAttribute extends Attribute {

    private static final String INTERACTION = "Interaction";


    public InteractionAttribute(Group targetLocation) {
        targetLocation.getChildren().add(setupInputs(INTERACTION));
    }

    @Override
    public VBox setupInputs(String attributeType) {
        VBox container = new VBox();
        List<String> interactionAttributeOptions = super.loadAttributes(attributeType);
        for(String interactionName : interactionAttributeOptions){
            container.getChildren().add(getOptionContents(interactionName));
        }
        return container;
    }

    private CheckBox getOptionContents(String option) {
        CheckBox interaction = new CheckBox(option);
        return interaction;
    }
}
