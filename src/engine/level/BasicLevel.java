package engine.level;

import engine.entity.GameObject;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

/**
 * The BasicLevel class is the basic implementation of the Level interface.
 *
 * @author Julia Long
 */
public class BasicLevel implements Level {

    private GridPane myGrid;
    private List<GameObject> myObjects;
    private int myID;
    private String myName;

    private static final String DEFAULT = "Default";

    /**
     * Creates a new basic Level.
     * @param xSize is the desired x size of the grid
     * @param ySize is the desired y size of the grid
     */
    public BasicLevel(int xSize, int ySize) {
        myGrid = new GridPane();
        myGrid.setPrefSize(xSize, ySize);
        myObjects = new ArrayList<>();
        myID = 0;
        myName = DEFAULT;
    }

    @Override
    public void setObjects(List<GameObject> objects) {
        myObjects = objects;
    }

    @Override
    public void addObject(GameObject object) {
        myObjects.add(object);
    }

    @Override
    public List<GameObject> getObjects() {
        return myObjects;
    }

    @Override
    public void setID(int id) {
        myID = id;
    }

    @Override
    public void setName(String name) {
        myName = name;
    }

    @Override
    public void updateGrid(GridPane grid) {
        myGrid = grid;
    }

    @Override
    public GridPane getGrid() {
        return myGrid;
    }
}
