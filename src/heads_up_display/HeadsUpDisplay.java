package heads_up_display;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.SubScene;

/**
 * Public API for the HeadsUpDisplay feature
 *
 * @Author Dorian Barber
 */
public interface HeadsUpDisplay {

    /**
     * Returns a subscene object which represents the heads up display
     */
    public SubScene getHUD();


    /**
     * Adds the component string into the heads up display
     * Returns an ID which corresponds to the component in the heads up display,
     * used for updating this particular component
     */
    public int addComponent(String component);


    /**
     * Removes the component from the heads up display
     * Requires the unique ID which in order to remove the
     * component which has that same ID
     */
    public void removeComponent(int ID);


    /**
     * Adds the component string into the heads up display
     * and return the unique ID corresponding to the component
     * Parameters for the x and y coordinate will designate
     * where in the subscene the component is add.
     */
    public int addComponent(String value, int x, int y);


    /**
     * Updates the particular component specified by the ID
     * whose new value is set by the string
     */
    public void updateComponent(int ID, String newValue);

    /**
     * Sets the bindings of the heads up display to be able to
     * grow when added to pane objects. The parameters should
     * correspond to the width and height properties from the
     * pane object.
     */
    public void setBindings(ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height);
}
