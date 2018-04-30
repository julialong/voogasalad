package authoring_environment.attribute_editor;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.*;

public abstract class Attribute {

    private static final String ATTRIBUTE_RESOURCES = "resources/";

    public abstract void setupInputs(Pane target, String attributeType);

    /**
     * Gets all of options for the attribute passed
     *
     * @param attribute is the attribute that needs to be loaded
     *             i.e. Basic, Behavior, Movement, Interaction
     * @return a list of all the different options dependening on the attribute passed
     */
    protected List<String> loadAttributes(String attribute) {
        Map<String, List<String>> attributes = new HashMap<>();
        ResourceBundle resources = ResourceBundle.getBundle(ATTRIBUTE_RESOURCES + attribute);
        Enumeration<String> attributeOptions = resources.getKeys();
        while (attributeOptions.hasMoreElements()) {
            String option = attributeOptions.nextElement();
            String type = resources.getString(option);
            if(attributes.containsKey(type)) {
                List<String> optionList = attributes.get(type);
                optionList.add(option);
                attributes.put(type, optionList);
            }
            else {
                List<String> optionList = new ArrayList<>();
                optionList.add(option);
                attributes.put(type, optionList);
            }
        }
        return attributes.get(attribute);
    }
}
