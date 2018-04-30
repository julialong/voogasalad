package authoring_environment.attribute_editor;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.*;

public abstract class Attribute {

    private static final String ATTRIBUTE_RESOURCES = "resources/Attributes.properties";

    public abstract void setupInputs(Pane target, String attributeType);

    /**
     * Gets all of options for the attribute passed
     *
     * @param attribute is the attribute that needs to be loaded
     *             i.e. Basic, Behavior, Movement, Interaction
     * @return a list of all the different options dependening on the attribute passed
     */
    protected List<String> loadAttributes(String attribute) {
        ResourceBundle rb = ResourceBundle.getBundle(ATTRIBUTE_RESOURCES);
        List<String> attributeOptions = new ArrayList<>();
        Enumeration<String> allOptions = rb.getKeys();
        while (allOptions.hasMoreElements()) {
            String option = allOptions.nextElement();
            String type = rb.getString(option);
            if(type == attribute){
                attributeOptions.add(option);
            }
        }
        return attributeOptions;
    }
}
