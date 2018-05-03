package authoring_environment.grid;

import authoring_environment.DocumentGetter;
import authoring_environment.game_elements.AuthoredLevel;

import engine.entity.GameEntity;
import org.w3c.dom.Document;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * 
 * @author Judi Sanchez, Michael Acker, Julia Long
 * Date Started: April 1 2018
 */
public class ScrollingGrid extends GridPane implements DocumentGetter{
	// TODO: Change this based on level size
	private static final int DEFAULT_ROWS = 20;
	private static final int DEFAULT_COLUMNS = 50;
	private static final int DEFAULT_CELL_SIZE = 50;
	private static final String ELEMENT_DATA_PATH = "./data/authoredElementData/";
	private static final int CELL_INCREMENT = 5;

	
	private int cellSize;
	private GridCell[][] cellArray;
	private AuthoredLevel myLevel;
	private int rows;
	private int cols;

	/**
	 * Creates a new Scrolling Grid
	 */
	public ScrollingGrid(int x, int y) {
		super();
		cellSize = DEFAULT_CELL_SIZE;
		cellArray = new GridCell[y][x];
		rows = x;
		cols = y;
		initCells();
		makeGrid();
	}



	public ScrollingGrid() {
		this(DEFAULT_ROWS, DEFAULT_COLUMNS);
	}



	/**
	 * Sets the class to notify when an object is added
	 * @param level is the AuthoredLevel to associate with this grid.
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
		for (int i = 0; i < rows; i++) {
			this.getRowConstraints().add(new RowConstraints(cellSize));
		}
		for (int i = 0; i < cols; i++) {
			this.getColumnConstraints().add(new ColumnConstraints(cellSize));
		}
		addCells();
		
	}
	
	private void addCells() {
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				GridCell cell = cellArray[i][j];
				this.add(cell,i,j);
			}
		}
	}
	
	private void initCells() {
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				cellArray[i][j] = new GridCell(this, cellSize, i, j);
			}
		}
	}

	public void resize(int newRowNum, int newColNum) {
		GridCell[][] newCells = new GridCell[newColNum][newRowNum];
		for (int i = 0; i < newColNum; i++) {
			for (int j = 0; j < newRowNum; j++) {
				newCells = assignCell(newCells, i, j);
			}
		}
		rows = newRowNum;
		cols = newColNum;
		cellArray = newCells;
		makeGrid();
	}

	public int getCellSize() {
		return cellSize;
	}

	private GridCell[][] assignCell(GridCell[][] newCells, int i, int j) {
		if (i < cellArray.length && j < cellArray[0].length) {
			newCells[i][j] = cellArray[i][j];
		}
		else {
			newCells[i][j] = new GridCell(this, cellSize, i, j);
		}
		return newCells;
	}

	/**
	 * Zooms in by making GridCells larger.
	 */
	public void zoomIn() {
		cellSize = cellSize + CELL_INCREMENT;
		makeGrid();
	}

	/**
	 * Zooms out by making GridCells smaller.
	 */
	public void zoomOut() {
		cellSize = cellSize - CELL_INCREMENT;
		makeGrid();
	}

	/**
	 * Resets all GridCells in the ScrollingGrid.
	 */
	public void deleteCells() {
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				GridCell cell = cellArray[i][j];
				if (cell.isSelected()) {
					if (cell.getObject() != null) {
						System.out.println("item removed: " + cell.getObject().getClass());
						myLevel.removeObject(cell.getObject());
					}
					cell.reset();
					GridPane.setRowSpan(cell, cell.getYDim());
					GridPane.setColumnSpan(cell, cell.getXDim());
				}
			}
		}
	}

	/**
	 * Sets the given cell's state
	 * @param cell is the cell to modify
	 * @param ID is the ID of the new state
	 */
	public void setCellElement(GridCell cell, String ID) {
		setCellElement(cell, ID, true);
	}

	/**
	 * Sets the given cell's state
	 * @param cell is the cell to modify
	 * @param ID is the ID of the new state
	 * @param fromFile checks whether grid is being made in GUI or if being read from file, which determines if cell contents need to call addObject()
	 */
	public void setCellElement(GridCell cell, String ID, boolean fromFile) {
		if (cell.isSelected()) {
			checkMultipleCells(ID);
		} else {
			cell.setImage(ID);
			GridPane.setRowSpan(cell, cell.getYDim());
			GridPane.setColumnSpan(cell, cell.getXDim());
		}
		cell.setObject(myLevel.addObject(ID, cell.getPosition().getX(), cell.getPosition().getY(), cellSize, fromFile));
	}

	private void checkMultipleCells(String ID) {
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				GridCell checkCell = cellArray[i][j];
				if (checkCell.isSelected()) {
					checkCell.setObject(myLevel.addObject(ID, checkCell.getPosition().getX(), checkCell.getPosition().getY(), cellSize));
					checkCell.setImage(ID);
					this.setRowSpan(checkCell, checkCell.getYDim());
					this.setColumnSpan(checkCell, checkCell.getXDim());
					checkCell.deselect();
				}
			}
		}
	}

	/**
	 * Gets the current GridCell array.
	 * @return the array of GridCells in the ScrollingGrid.
	 */
	public GridCell[][] getCellArray()	{
		return cellArray;
	}

	/**
	 * Gets the Document associated with a given ID
	 * @param ID is the ID of the object to get
	 * @return the XML Document associated with the ID
	 */
	Document parseElementXML(String ID) {
		return getDocument(ID, ELEMENT_DATA_PATH);
	}


}
