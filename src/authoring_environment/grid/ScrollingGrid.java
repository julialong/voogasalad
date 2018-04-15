package authoring_environment.grid;

import authoring_environment.game_elements.AuthoredLevel;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * 
 * @author Judi Sanchez and Michael Acker
 * Date Started: April 1 2018
 */
public class ScrollingGrid extends GridPane {
	// TODO: Change this based on level size
	private static final int NUMBER_OF_ROWS = 20;
	private static final int NUMBER_OF_COLUMNS = 50;
	private static final int DEFAULT_CELL_SIZE = 50;
	
	private int cellSize;
	private int rowNumber;
	private int columnNumber;
	private GridCell[][] cellArray;
	private AuthoredLevel myLevel;

	public ScrollingGrid() {
		super();
		cellSize = DEFAULT_CELL_SIZE;
		cellArray = new GridCell[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];
		initCells();
		makeGrid();

	}

	/**
	 * Sets the class to notify when an object is added
	 * @param level
	 */
	public void setMediator(AuthoredLevel level) {
		myLevel = level;
	}

	private void makeGrid() {
		this.getChildren().clear();
		this.getRowConstraints().clear();
		this.getColumnConstraints().clear();
		//TODO: Change it so that a grid is created when a new level is created
		//this.setGridLinesVisible(true);
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			this.getRowConstraints().add(new RowConstraints(cellSize));
		}
		for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
			this.getColumnConstraints().add(new ColumnConstraints(cellSize));
		}
		addCells();
		
	}
	
	private void addCells() {
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
				GridCell cell = cellArray[j][i];
				this.add(cell,j,i);
			}
		}
	}
	
	private void initCells() {
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
				cellArray[j][i] = new GridCell(this, cellSize);
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
	
	public void deleteCells() {
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
				GridCell cell = cellArray[j][i];
				if (cell.isSelected()) {
					cell.reset();
				}
			}
		}
	}
	
	public void setCellImage(GridCell cell, Image image, String path) {
		if (cell.isSelected()) {
			for (int i = 0; i < NUMBER_OF_ROWS; i++) {
				for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
					GridCell checkCell = cellArray[j][i];
					if (checkCell.isSelected()) {
						checkCell.setImage(path);
						myLevel.addObject(" beep");
					}
				}
			}
		} else cell.setImage(path);
	}

	public void setCellImage(GridCell cell, String path) {
			GridCell checkCell = cell;
			checkCell.setImage(path);
	}
	
	public GridCell[][] getCellArray()	{
		return cellArray;
	}
}
