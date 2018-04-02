package authoring_environment;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * 
 * @author Judi Sanchez
 * Date Started: April 1 2018
 */
public class ScrollingGrid extends ScrollPane {
	// TODO: Change this based on level size
	private static final int NUMBER_OF_ROWS = 50;
	private static final int NUMBER_OF_COLUMNS = 60;
	
	private GridPane gridpane;
	private ScrollPane scrollpane;

	public ScrollingGrid() {
		gridpane = new GridPane();
		scrollpane = new ScrollPane(gridpane);
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

	}

	public ScrollPane getScrollingGridPane() {
		return scrollpane;
	}

}
