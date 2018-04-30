package authoring_environment.attribute_editor;

import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;


public abstract class Attribute {

    private static final String ATTRIBUTE_RESOURCES = "resources/Attributes.properties";

    public abstract VBox setupInputs(String attributeType);

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
