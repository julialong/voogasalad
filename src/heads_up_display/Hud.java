package heads_up_display;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.text.Text;
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
        displayBoard.setUserAgentStylesheet("hudStyleSheet.css");
    }


    /**
     * Constructor which takes in a height and width which would correspond
     * to either the screen size for the user or the desired size for the heads
     * up display
     */
    public Hud(int width, int height){
        initializeFields();
        displayBoard = new SubScene(root, width, height);
        displayBoard.setUserAgentStylesheet("src\\heads_up_display\\hudStyleSheet.css");
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
     * Adds the component string into the heads up display
     * Returns an ID which corresponds to the component in the heads up display,
     * used for updating this particular component
     */
    @Override
    public int addComponent(String value) {
        return addComponent(value, (int)displayBoard.getWidth()/2, (int)displayBoard.getHeight()/2);
    }


    /**
     * Adds the component string into the heads up display
     * and return the unique ID corresponding to the component
     * Parameters for the x and y coordinate will designate
     * where in the subscene the component is add.
     */
    @Override
    public int addComponent(String value, int x, int y){
        HudComponent displayComponent = new HudComponent(value);
        displayComponent.setX(x);
        displayComponent.setY(y);
        components.put(counter, displayComponent);
        counter += 1;
        root.getChildren().add(displayComponent);
        root.getChildren().add(new Text("HI"));
        return counter - 1;
    }


    /**
     * Removes the component from the heads up display
     * Requires the unique ID which in order to remove the
     * component which has that same ID
     *
     * @param ID
     */
    @Override
    public void removeComponent(int ID) {
            components.remove(ID);
    }
}
