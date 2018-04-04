package authoring_environment;

import authoring_environment.toolbars.RightBar;
import authoring_environment.toolbars.TopBar;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * EditorWindow implements the CreatorView interface and manages the UI of the
 * editing environment
 *
 * @author Julia Long, Judi Sanchez
 * Date started: March 30 18
 */
public class EditorWindow implements CreatorView {

	// List<Level> myLevels;

	private Stage myStage;
	private Scene myScene;
	private BorderPane myRoot;

	/**
	 * Creates a new EditorWindow
	 * 
	 * @param stage
	 *            The stage where EditorWindow should be presented
	 */
	public EditorWindow(Stage stage) {
		myStage = stage;

		openNewWindow();
	}

	/**
	 * Updates the current GUI representation of the AuthoredElements locations on a
	 * particular level's grid
	 */
	@Override
	public void updateGrid() {
	}

	/**
	 * Updates the portion of thr grid that is currently showm on the program's
	 * screen. This will be accomplished with a scrollbar that has a value which
	 * will denote the leftmost x coordindate
	 * 
	 * @param location
	 *            The value of the leftmost coordinate on the viewable grid
	 */
	@Override
	public void updateGridView(double location) {

	}

	/**
	 * Opens an editor to change the atributes of a specific AuthoredElement
	 * 
	 * @param elem
	 *            is the AuthoredElement for which an editor window will be opened
	 */
	@Override
	public void openAttributeEditor(AuthoredElement elem) {

	}

	/**
	 * Opens a new window to allow for multi-window editing of the same level or
	 * different levels of the game
	 */
	@Override
	public void openNewWindow() {
		setupNewWindow();
		myStage.setScene(myScene);
		// TODO: read from Resource file
		myStage.setTitle("Game Authoring Environment");
		myStage.show();
	}

	/**
	 * Sets up the new window by creating the new borderpane and adding the
	 * appropriate toolbars and the grid
	 */
	private void setupNewWindow() {
		myRoot = new BorderPane();
		myScene = new Scene(myRoot);
		myScene.getStylesheets().add("GAE.css");
		myStage.setMaximized(true);

		myRoot.setTop(new TopBar());
		myRoot.setRight(setRightBar());

		myRoot.setCenter(setCenterGrid());

	}

	private SplitPane setRightBar() {
		SplitPane rightBar = new RightBar();
		rightBar.getStyleClass().add("side-pane");
		return rightBar;
	}

	private ScrollPane setCenterGrid() {
    	 ScrollingGrid grid = new ScrollingGrid();
    	 ScrollPane centerPane= grid.getScrollingGridPane();
    	 centerPane.getStyleClass().add("center-pane");
    	 return centerPane;
    	
    }
}
