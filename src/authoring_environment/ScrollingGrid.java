package authoring_environment;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class ScrollingGrid {
	// TODO: Change this based on level size
	private static final int NUMBER_OF_ROWS = 50;
	private static final int NUMBER_OF_COLUMNS = 60;
	// TODO: Get this from resource file
	private static final double SCROLL_PANE_WIDTH = 900;
	private static final double SCROLL_PANE_HEIGHT = 800;

	private GridPane gridpane;
	private ScrollPane scrollpane;

	public ScrollingGrid() {
		gridpane = new GridPane();
		scrollpane = new ScrollPane(gridpane);
		scrollpane.setMaxWidth(SCROLL_PANE_WIDTH);
		scrollpane.setMaxHeight(SCROLL_PANE_HEIGHT);
		makeGrid();

	}

	private void makeGrid() {
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
