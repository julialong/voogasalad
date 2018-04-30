package authoring_environment.attribute_editor;

import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.ResourceBundle;

public class BehaviorAttribute extends Attribute {
    private static final String BEHAVIOR = "Behavior";
    private ResourceBundle rb = ResourceBundle.getBundle("resources.Behaviors");

    public BehaviorAttribute(Group targetLocation){
        targetLocation.getChildren().add(setupInputs(BEHAVIOR));
    }

    /**
     * Creates the container for the behavior selection model
     * and creates the listener which will automatically
     * create the input lines for each one selected
     */
    @Override

    public VBox setupInputs(String attributeType) {
        VBox container = new VBox();
        List<String> behaviorAttributesOptions = super.loadAttributes(attributeType);
        for(String option : behaviorAttributesOptions){
            container.getChildren().add(getOptionContents(option));
        }
        return container;
    }

    /**
     * Returns an HBox which contains a label and a textfield that represent
     * the components within each option. Each option from a behavior
     * has specifications which need to be filled in.
     */
    private HBox getOptionContents(String option){
        HBox inputLine = new HBox();
        CheckBox checker = new CheckBox(option);
        inputLine.getChildren().add(checker);
        int numbOfFields = Integer.parseInt(rb.getString(option));
        while(numbOfFields > 0){
            TextField dataField = new TextField();
            //TODO probably need this data field to have a setOnFill thing
            inputLine.getChildren().add(dataField);
            numbOfFields -= 1;
        }
        return inputLine;
    }
}
