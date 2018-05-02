package authoring_environment.attribute_editor;

import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Map;

/**
 * Super class for all attributes which can be added to an entity in the
 * game authoring environment. Each subclass represents an attribute
 * that an entity can possibly have. This format is easily extendable
 * since if a user would like to create a new attribute to add to entities,
 * they need create a new subclass and implement the methods to create the attribute.
 * Then the attribute must be added to the AttributeEditor for that sect of entities.
 *
 * @Author Judi Sanchez, Dorian Barber
 */
public abstract class Attribute {

    private static final String ATTRIBUTE_RESOURCES = "resources.Attribute";
    
    public abstract String returnName();

    /**
     * Returns a VBox which represents all of the options that a user can select
     * or edit to alter the attributes of the game entity. The format of the
     * vbox is entirely dependent on the subclass.
     */
    public abstract VBox setupInputs(String attributeType);


    /**
     * Returns a map of option to a list of data fields that the user inputs.
     * If there are no data fields to input then it is an empty list.
     */
    public abstract Map<String, List<String>> getAttributeContent();


    /**
     * Gets all of options for the attribute passed. This method is used
     * by each subclass to get all of the options related to this attribute.
     *
     * @param attribute is the attribute that needs to be loaded
     *             i.e. Basic, Behavior, Movement, Interaction, etc
     * @return a list of all the different options depending on the attribute passed
     */
    protected List<String> loadAttributes(String attribute) {
        ResourceBundle rb = ResourceBundle.getBundle(ATTRIBUTE_RESOURCES);
        List<String> attributeOptions = new ArrayList<>();
        Enumeration<String> allOptions = rb.getKeys();
        while (allOptions.hasMoreElements()) {
            String option = allOptions.nextElement();
            String type = rb.getString(option);
            System.out.println(type);
            if(type.contains(attribute)){
                attributeOptions.add(option);
            }
        }
        return attributeOptions;
    }
}