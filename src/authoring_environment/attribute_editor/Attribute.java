package authoring_environment.attribute_editor;

import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Map;


public abstract class Attribute {

    private static final String ATTRIBUTE_RESOURCES = "resources.Attribute";

    public abstract VBox setupInputs(String attributeType);

    /**
     * Returns a map of option to a list of data fields that the user inputs.
     * If there are no data fields to input then it is an empty list.
     */
    public abstract Map<String, List<String>> getAttributeContent();

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
            if(type.equals(attribute)){
                attributeOptions.add(option);
            }
        }
        return attributeOptions;
    }
}
