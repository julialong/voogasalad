package engine.level;

import engine.entity.GameEntity;
import engine.entity.GameObject;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

/**
 * The BasicLevel class is the basic implementation of the Level interface.
 *
 * @author Robert Gitau, Marcus Oertle, Julia Long
 */
public class BasicLevel implements Level {

    private GridPane myGrid;
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
        myGrid = new GridPane();
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
    public void updateGrid(GridPane grid) {
        myGrid = grid;
    }

    @Override
    public GridPane getGrid() {
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
        double sourceXSize = source.getSizeX();
        double sourceYSize = source.getSizeY();
        double targetXSize = target.getSizeX();
        double targetYSize = target.getSizeY();
        
        double sourceTop = source.getPosition()[1];
        double sourceBottom = source.getPosition()[1] - sourceYSize;
        double sourceLeft = source.getPosition()[0];
        double sourceRight = source.getPosition()[0] + sourceXSize;
        
        double targetTop = target.getPosition()[1];
        double targetBottom = target.getPosition()[1] - targetYSize;
        double targetLeft = target.getPosition()[0];
        double targetRight = target.getPosition()[0] + targetXSize;
        
        if((targetBottom < sourceTop && targetBottom > sourceBottom) || (targetTop < sourceTop && targetTop > sourceBottom)) {
        	if((targetLeft > sourceLeft && targetLeft < sourceRight) || (targetRight > sourceLeft && targetRight < sourceRight)) {
        		source.interact(source, target);
        	}
        }
    }

}