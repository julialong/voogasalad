package authoring_environment.grid;

import authoring_environment.game_elements.AuthoredLevel;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

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
	private static final String ELEMENT_DATA_PATH = "./data/authoredElementData/";
	private static final int CELL_INCREMENT = 5;

	
	private int cellSize;
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
		cellSize = cellSize + CELL_INCREMENT;
		makeGrid();
	}
	
	public void zoomOut() {
		cellSize = cellSize - CELL_INCREMENT;
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
	
	public void setCellImage(GridCell cell, String ID) {
		if (cell.isSelected()) {
			checkMultipleCells(ID);
		} else {
			cell.setImage(ID);
		}
	}

	private void checkMultipleCells(String ID) {
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
				GridCell checkCell = cellArray[j][i];
				if (checkCell.isSelected()) {
					myLevel.addObject(ID);
					checkCell.setImage(ID);
				}
			}
		}
	}
	
	public GridCell[][] getCellArray()	{
		return cellArray;
	}
	
	public Document parseElementXML(String ID) {
		try {
		File file = new File(ELEMENT_DATA_PATH + ID + ".xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		db = dbf.newDocumentBuilder();
		Document elementDoc = db.parse(file);
		return elementDoc;
		} catch (Exception e) {
			// TODO: handle this exception
		    e.printStackTrace();
		    return null;
	    }
	}
}