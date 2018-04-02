package authoring_environment;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Grid {
	private static final int NUMBER_OF_ROWS= 50;
	private static final int NUMBER_OF_COLUMNS= 50;
	private GridPane gridpane;
	private ScrollPane scrollpane;
	
	public Grid() {
		 gridpane = new GridPane();
		 scrollpane = new ScrollPane(gridpane);
		 makeGrid();
		 
	}
	
	private void makeGrid() {
		gridpane.setGridLinesVisible(true);
		for(int i =0; i<NUMBER_OF_ROWS; i++) {
	    	 gridpane.getRowConstraints().add(new RowConstraints(250));
	    }
	    for(int i =0; i<NUMBER_OF_COLUMNS; i++) {
	   	 gridpane.getColumnConstraints().add(new ColumnConstraints(25));
	   }
		
	}

}
