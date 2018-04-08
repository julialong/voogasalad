package authoring_environment;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
<<<<<<< src/authoring_environment/ScrollingGrid.java
 * 
 * @author Judi Sanchez and Michael Acker
 * This class is a ScrollingPane that contains the grid that represents the placement 
 * of objects in each level of the created game
 * Date Started: April 1 2018
 */
public class ScrollingGrid extends ScrollPane {
	// TODO: Change this based on level size
	private static final int NUMBER_OF_ROWS = 50;
	private static final int NUMBER_OF_COLUMNS = 60;
	private static final int DEFAULT_CELL_SIZE = 25;
	
	private GridPane gridpane;
	private ScrollPane scrollpane;
	private int cellSize;

	/**
	 * This constructor creates a new ScrollPane that holds a GridPane
	 */
	public ScrollingGrid() {
		gridpane = new GridPane();
		scrollpane = new ScrollPane(gridpane);
		setupDragAndDrop();
		cellSize = DEFAULT_CELL_SIZE;
		makeGrid();

	}

	private void makeGrid() {
		//TODO: Change it so that a grid is created when a new level is created
		gridpane.setGridLinesVisible(true);
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			gridpane.getRowConstraints().add(new RowConstraints(25));
		}
		for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
			gridpane.getColumnConstraints().add(new ColumnConstraints(25));
		}
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
				GridCell cell = new GridCell();
				cell.setFitHeight(cellSize);
				cell.setFitWidth(cellSize);
				gridpane.add(cell,j,i);
			}
		}
	}

	/**
	 * @return the ScrollPane 
	 */
	public ScrollPane getScrollingGridPane() {
		return scrollpane;
	}
	
	private void setupDragAndDrop() {
		scrollpane.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != scrollpane &&
		                event.getDragboard().hasString()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY);
		        }
		        
		        event.consume();
		    }
		});
		gridpane.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != gridpane &&
		                event.getDragboard().hasString()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY);
		        }
		        
		        event.consume();
		    }
		});
	}

}
