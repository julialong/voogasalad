package authoring_environment.toolbars.buttons.creator_view_buttons;

import authoring_environment.editor_windows.EditorWindow;
import authoring_environment.grid.ScrollingGrid;
import javafx.scene.control.Button;

public class DeleteGridCellButton extends Button {
	private static final String LABEL = "Delete";
	private EditorWindow myWindow;

    /**
     * Creates a simple add element button.
     */
    public DeleteGridCellButton(EditorWindow window) {
        super(LABEL);
        myWindow = window;
        this.setOnAction(e -> myWindow.getGame().getCurrentLevel().getScrollingGrid().deleteCells());
    }
}
