package engine.level;

import authoring_environment.GridCell;
import authoring_environment.ScrollingGrid;
import engine.entity.Block;
import engine.entity.Enemy;
import engine.entity.Flag;
import engine.entity.Foes;
import engine.entity.GameEntity;
import engine.entity.GameObject;
import engine.entity.Platform;
import engine.entity.Player;
import engine.physics.DetectCollision;
import javafx.scene.image.ImageView;

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
            for(GameEntity target : myObjects){
                checkInteractions(source, target);
            }
            source.update();
        }
    }
    
    private void checkInteractions(GameEntity source, GameEntity target){
    	if(!((new DetectCollision().detect(source, target)).equals("none"))) {
    		source.interact(source, target);
    	}
    }
    
    @Override
    public List<GameEntity> export() {
    	ArrayList<GameEntity> entityList = new ArrayList<GameEntity>();
    	for (int i = 0; i < myXSize; i++) {
    		for (int j = 0; j < myYSize; i++) {
    			GameEntity entity = null;
    			GridCell cell = myGrid.getCellArray()[i][j];
    			String type = cell.getType();
    			String ID = cell.getID();
    			ImageView view = cell.getView();
    			view.setFitHeight(myCellSize);
    			view.setFitWidth(myCellSize);
    			int xPos = i*myCellSize;
    			int yPos = j*myCellSize;
    			if(type != null) {
    				switch(type) {
    				case "Block": entity = new Block(xPos, yPos);
    				case "Flag": entity = new Flag(xPos, yPos);
    				case "Foes": entity = new Foes(xPos, yPos);
    				case "Platform": entity = new Platform(xPos, yPos);
    				case "Player": entity = new Player(xPos, yPos);
    				}
    				entity.setImageView(view);
    				entity.setElementID(ID);
    				entityList.add(entity);
    			}
    		}
    	}
    	return entityList;
    }

}