package authoring_environment.toolbars.buttons.creator_view_buttons;

import authoring_environment.AuthoredGame;
import authoring_environment.ScrollingGrid;
import authoring_environment.editor_windows.LevelCreator;
import javafx.scene.control.Button;

public class DeleteGridCellButton extends Button {
	private static final String LABEL = "Delete";
	private ScrollingGrid myGrid;

    /**
     * Creates a simple add element button.
     */
    public DeleteGridCellButton(ScrollingGrid grid) {
        super(LABEL);
        myGrid = grid;
        this.setOnAction(e -> myGrid.deleteCells());
    }
    
    public void changeGrid(ScrollingGrid grid) {
    	myGrid = grid;
    }

}
