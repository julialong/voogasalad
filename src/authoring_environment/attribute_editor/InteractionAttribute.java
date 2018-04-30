package authoring_environment.attribute_editor;

import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This subclass represents the interaction attribute that entities may have.
 * The user may select a number of interactions; therefore, this subclass is
 * implemented with a series of checkboxes which denote whether a user would
 * like to add the interaction to the game entity.
 *
 * @Author Judi Sanchez, Dorian Barber
 */
public class InteractionAttribute extends Attribute {

    private static final String INTERACTION = "Interaction";
    private List<CheckBox> interactionOptions;

    /**
     * Creates the interaction attribute container which holds all the interactions
     * a game entity may have.
     */
    public InteractionAttribute(Group targetLocation) {
        interactionOptions = new ArrayList<>();
        targetLocation.getChildren().add(setupInputs(INTERACTION));
    }


    /**
     * Creates all the checkboxes that designate the interaction that the user
     * can select.
     */
    @Override
    public VBox setupInputs(String attributeType) {
        VBox container = new VBox();
        List<String> interactionAttributeOptions = super.loadAttributes(attributeType);
        for(String interactionName : interactionAttributeOptions){
            CheckBox inputLine = new CheckBox(interactionName);
            interactionOptions.add(inputLine);
            container.getChildren().add(inputLine);
        }
        return container;
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