package engine.level;

import authoring_environment.ScrollingGrid;
import engine.entity.GameEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * The BasicLevel class is the basic implementation of the Level interface.
 *
 * @author Julia Long
 */
public class BasicLevel implements Level {

    private ScrollingGrid myGrid;
    private List<GameEntity> myObjects;
    private int myID;
    private String myName;

    private static final String DEFAULT = "Default";
    private static final int DEFAULT_X_SIZE = 500;
    private static final int DEFAULT_Y_SIZE = 500;

    /**
     * Creates a new basic Level.
     * @param xSize is the desired x size of the grid
     * @param ySize is the desired y size of the grid
     */
    public BasicLevel(int xSize, int ySize) {
        myGrid = new ScrollingGrid();
        myGrid.setPrefSize(xSize, ySize);
        myObjects = new ArrayList<>();
        myID = 0;
        myName = DEFAULT;
    }

    /**
     * Creates a new basic Level with no size defined.
     */
    public BasicLevel() {
        this(DEFAULT_X_SIZE, DEFAULT_Y_SIZE);
    }

    @Override
    public void setObjects(List<GameEntity> objects) {
        myObjects = objects;
    }

    @Override
    public void addObject(GameEntity object) {
        myObjects.add(object);
    }

    @Override
    public List<GameEntity> getObjects() {
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
    public String getName() {
        return myName;
    }

    @Override
    public void updateGrid(ScrollingGrid grid) {
        myGrid = grid;
    }

    @Override
    public ScrollingGrid getGrid() {
        return myGrid;
    }

    @Override
    public void setSize(double X, double Y) {
        myGrid.setPrefSize(X, Y);
    }

}
