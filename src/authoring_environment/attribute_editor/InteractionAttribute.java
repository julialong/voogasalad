package authoring_environment.attribute_editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class InteractionAttribute extends Attribute {

    private static final String INTERACTION = "Interaction";
    private List<CheckBox> interactionOptions;


    public InteractionAttribute(Group targetLocation) {
        interactionOptions = new ArrayList<>();
        targetLocation.getChildren().add(setupInputs(INTERACTION));
    }

    @Override
    public VBox setupInputs(String attributeType) {
        VBox container = new VBox();
        List<String> interactionAttributeOptions = super.loadAttributes(attributeType);
        for(String interactionName : interactionAttributeOptions){
            CheckBox inputLine = getOptionContents(interactionName);
            interactionOptions.add(inputLine);
            container.getChildren().add(inputLine);
        }
        return container;
    }

    private CheckBox getOptionContents(String option) {
        CheckBox interaction = new CheckBox(option);
        return interaction;
    }

    /**
     * Returns a map of option to a list of data fields that the user inputs.
     * If there are no data fields to input then it is an empty list.
     */
    @Override
    public Map<String, List<String>> getAttributeContent() {
        Map<String, List<String>> contents = new HashMap<>();
        for(CheckBox interaction : interactionOptions){
            if(interaction.isSelected()){
                String interactionLabel = interaction.getText();
                contents.put(interactionLabel, new ArrayList<>());
            }
        }
        return contents;
    }
}
