package engine.level;


import authoring_environment.grid.ScrollingGrid;
import engine.entity.GameEntity;
import engine.entity.Player;
import engine.physics.DetectCollision;

import java.util.ArrayList;
import java.util.List;

/**
 * The BasicLevel class is the basic implementation of the Level interface.
 *
 * @author Robert Gitau, Marcus Oertle, Julia Long, Michael Acker
 */
public class BasicLevel implements Level {

    private ScrollingGrid myGrid;
    private List<GameEntity> myObjects;
    private int myID;
    private String myName;
    private DetectCollision detectCollision = new DetectCollision();
    private ArrayList<GameEntity> toRemoveFromObjectList = new ArrayList<>();

    private static final String DEFAULT = "Default";
    private static final int DEFAULT_X_SIZE = 500;
    private static final int DEFAULT_Y_SIZE = 500;
    private static final int DEFAULT_CELL_SIZE = 50;
    private int myXSize;
    private int myYSize;
    private int myCellSize;

    /**
     * Creates a new basic Level.
     * @param xSize is the desired x size of the grid
     * @param ySize is the desired y size of the grid
     */
    public BasicLevel(int xSize, int ySize, int ID) {
        myGrid = new ScrollingGrid();
        myXSize = xSize;
        myYSize = ySize;
        myGrid.setPrefSize(myXSize, myYSize);
        myObjects = new ArrayList<>();
        myID = ID;
        myName = DEFAULT;
    }

    /**
     * Creates a new basic Level with no size defined.
     */
    public BasicLevel(int ID) {
        this(DEFAULT_X_SIZE, DEFAULT_Y_SIZE, ID);
    }

    public BasicLevel() {
        this(0);
    }

    @Override
    public void setObjects(List<GameEntity> objects) {
        myObjects = objects;
    }

    @Override
    public void addObject(GameEntity object) {
//    	if(object instanceof Player || object instanceof Foes) {
//    		myObjects.add(0, object);
//    	}
//    	else {
    		myObjects.add(object);
//    	}
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
    public int getID() {
        return myID;
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
    
    @Override
    public void update(){
    	for(GameEntity source : myObjects){
    		source.update();
    		if(source.getHealth() < 1 && !(source instanceof Player)) {
    			//System.out.println(source.getClass().getSimpleName() + ": " + source.getHealth());
    			toRemoveFromObjectList.add(source);
    		}
    	}
    	for(GameEntity ge : toRemoveFromObjectList) {
    		myObjects.remove(ge);
    	}
    	toRemoveFromObjectList.clear();
        for(GameEntity source : myObjects){
            for(GameEntity target : myObjects){
            	if(!(source == target)) {
            		checkInteractions(source, target);
            	}
            }
        }
    }
    
    private void checkInteractions(GameEntity source, GameEntity target){
    	String direction = detectCollision.detect(source, target);
    	if(!(direction.equals("none"))) {
    		source.interact(source, target, direction);
    	}
    }
}