package authoring_environment.editor_windows;

import authoring_environment.authored_elements.AuthoredElement;
import authoring_environment.game_elements.AuthoredGame;
import authoring_environment.grid.ScrollingGrid;
import authoring_environment.toolbars.RightBar;
import authoring_environment.toolbars.TopBar;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * EditorWindow implements the CreatorView interface and manages the UI of the
 * editing environment
 *
 * @author Julia Long, Judi Sanchez
 * Date started: March 30 18
 */
public class EditorWindow implements CreatorView {

	private Stage myStage;
	private Scene myScene;
	private BorderPane myRoot;
	private ScrollingGrid myGrid;
	private ScrollPane myScrollPane;

	private AuthoredGame myGame;

	/**
	 * Creates a new EditorWindow
	 * 
	 * @param stage
	 *            The stage where EditorWindow should be presented
	 */
	public EditorWindow(Stage stage) {
		myStage = stage;
		myGame = new AuthoredGame();
		openNewWindow();
	}

	@Override
	public AuthoredGame getGame() {
		return myGame;
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
		myGrid = myGame.getCurrentLevel().getScrollingGrid();
		myRoot.setTop(new TopBar(this));
		myRoot.setCenter(setCenterGrid());
		myRoot.setRight(setRightBar());

	}

	private SplitPane setRightBar() {
		SplitPane rightBar = new RightBar(this, myGrid, myScrollPane);
		rightBar.getStyleClass().add("side-pane");
		return rightBar;
	}

	private ScrollPane setCenterGrid() {
    	 myScrollPane = new ScrollPane(myGrid);
    	 myScrollPane.getStyleClass().add("center-pane");
    	 return myScrollPane;
    }
}