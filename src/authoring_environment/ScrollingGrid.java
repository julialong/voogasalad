package authoring_environment;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

/**
 * 
 * @author Judi Sanchez and Michael Acker
 * Date Started: April 1 2018
 */
public class ScrollingGrid extends ScrollPane {
	// TODO: Change this based on level size
	private static final int NUMBER_OF_ROWS = 20;
	private static final int NUMBER_OF_COLUMNS = 50;
	private static final int DEFAULT_CELL_SIZE = 50;
	
	private GridPane gridpane;
	private ScrollPane scrollpane;
	private int cellSize;

	public ScrollingGrid() {
		gridpane = new GridPane();
		scrollpane = new ScrollPane(gridpane);
		cellSize = DEFAULT_CELL_SIZE;
		makeGrid();

	}

	private void makeGrid() {
		gridpane.getChildren().clear();
		gridpane.getRowConstraints().clear();
		gridpane.getColumnConstraints().clear();
		//TODO: Change it so that a grid is created when a new level is created
		//gridpane.setGridLinesVisible(true);
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			gridpane.getRowConstraints().add(new RowConstraints(cellSize));
		}
		for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
			gridpane.getColumnConstraints().add(new ColumnConstraints(cellSize));
		}
		setupCells();
		
	}

	public ScrollPane getScrollingGridPane() {
		return scrollpane;
	}
	
	private void setupCells() {
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
				GridCell cell = new GridCell();
				cell.getView().setFitHeight(cellSize);
				cell.getView().setFitWidth(cellSize);
				cell.setMinHeight(cellSize);
				cell.setMinWidth(cellSize);
				cell.setStyle("-fx-border-color: black;");
				gridpane.add(cell,j,i);
			}
		}	
	}
	
	public void zoomIn() {
		cellSize = cellSize + 5;
		makeGrid();
	}
	
	public void zoomOut() {
		cellSize = cellSize - 5;
		makeGrid();
	}
}
