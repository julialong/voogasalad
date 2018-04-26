package heads_up_display;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.stage.Screen;
import java.util.HashMap;
import java.util.Map;

/**
 * Heads up display implementation. Has no dependencies and uses
 * a map implementation with unique IDs for components
 */
public class Hud implements HeadsUpDisplay {
    private SubScene displayBoard;
    private Group root;
    private Map<Integer, Component> components;
    private int counter;
    private DoubleProperty width;
    private DoubleProperty height;

    /**
     * No argument constructor, the width and height of the
     * subscene are taken from the entire screen size
     */
    public Hud(){
        initializeFields();
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        double width = screen.getWidth();
        double height = screen.getHeight();
        displayBoard = new SubScene(root, width, height);
        displayBoard.setUserAgentStylesheet("./game.player.styling/hudStyleSheet.css");
    }


    /**
     * Constructor which takes in a height and width which would correspond
     * to either the screen size for the user or the desired size for the heads
     * up display
     */
    public Hud(int width, int height){
        initializeFields();
        displayBoard = new SubScene(root, width, height);
        displayBoard.setUserAgentStylesheet("./game.player.styling/hudStyleSheet.css");
    }


    /**
     * Initializes all fields except for the displayBoard field
     * this initializations are standard across all constructors
     */
    private void initializeFields(){
        root = new Group();
        components = new HashMap<>();
        counter = 0;
    }


    /**
     * Returns a subscene object which represents the heads up display
     */
    @Override
    public SubScene getHUD() {
        return displayBoard;
    }


    /**
     * Simpler version of add component with only a string value.
     * This call the other addComponent method with parameters
     * (value, 0, 0). This translates to placing the component at
     * (0,0). Look to other method for more specific documentation.
     */
    @Override
    public int addComponent(String value) {
        return addComponent(value, 20, 20);
    }


    /**
     * Adds the component to the heads up display with specific coordinates.
     * If no coordinates are specified the placement defaults to (0,0).
     * An integer is returned which holds the unique ID for this specific component.
     * The matching ID is required in order to update the component.
     *
     * @param value String text to display on the HUD
     * @param x The x-coordinate of the hud component
     * @param y The y-coordinate of the hud component
     * @return int The unique ID that is REQUIRED to update this component
     */
    @Override
    public int addComponent(String value, int x, int y){
        HudComponent displayComponent = new HudComponent(value);
        displayComponent.setX(x);
        displayComponent.setY(y);
        components.put(counter, displayComponent);
        counter += 1;
        root.getChildren().add(displayComponent);
        return counter - 1;
    }

    /**
     * Updates the particular component specified by the ID
     * whose new value is set by the string
     */
    @Override
    public void updateComponent(int ID, String newValue) {
        components.get(ID).setComponent(newValue);
    }

    /**
     * Sets the bindings of the heads up display to be able to
     * grow when added to pane objects. The parameters should
     * correspond to the width and height properties from the
     * pane object.
     */
    @Override
    public void setBindings(ReadOnlyDoubleProperty paneWidth, ReadOnlyDoubleProperty paneHeight) {
        width.bind(paneWidth);
        height.bind(paneHeight);
    }


    /**
     * Removes the component from the heads up display
     * Requires the unique ID which in order to remove the
     * component which has that same ID
     */
    @Override
    public void removeComponent(int ID) {
        components.remove(ID);
    }
}
