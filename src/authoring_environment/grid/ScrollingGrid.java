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
	private static final int DEFAULT_ROWS = 50;
	private static final int DEFAULT_COLUMNS = 20;
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
		cellArray = new GridCell[x][y];
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
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				GridCell cell = cellArray[i][j];
				this.add(cell,i,j);
			}
		}
	}
	
	private void initCells() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				cellArray[i][j] = new GridCell(this, cellSize, i, j);
			}
		}
	}

	public void resize(int x, int y) {
		super.resize(x,y);
		GridCell[][] newCells = new GridCell[x][y];
		for (int i = 0; i < x && i < cellArray.length; i++) {
			for (int j = 0; j < y && j < cellArray[0].length; j++) {
				newCells[i][j] = cellArray[i][j];
			}
		}
		rows = x;
		cols = y;
		cellArray = newCells;
		makeGrid();
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
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				GridCell cell = cellArray[i][j];
				if (cell.isSelected()) {
					System.out.println("item removed: " + cell.getObject().getClass());
					myLevel.removeObject(cell.getObject());
					cell.reset();
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
		if (cell.isSelected()) {
			checkMultipleCells(ID);
		} else {
			cell.setImage(ID);
		}
		cell.setObject(myLevel.addObject(ID, cell.getPosition().getX(), cell.getPosition().getY()));
	}

	private void checkMultipleCells(String ID) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				GridCell checkCell = cellArray[i][j];
				if (checkCell.isSelected()) {
					checkCell.setObject(myLevel.addObject(ID, checkCell.getPosition().getX(), checkCell.getPosition().getY()));
					checkCell.setImage(ID);
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
	 * TODO: why can't we have all of the parsing done by
	 * TODO: these methods, and return a contructed object?
	 * Gets the Document associated with a given ID
	 * @param ID is the ID of the object to get
	 * @return the XML Document associated with the ID
	 */
	Document parseElementXML(String ID) {
		return getDocument(ID, ELEMENT_DATA_PATH);
	}


}
