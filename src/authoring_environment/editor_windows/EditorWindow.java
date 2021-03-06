package authoring_environment.editor_windows;

import authoring_environment.game_elements.AuthoredGame;
import authoring_environment.grid.ScrollingGrid;
import authoring_environment.toolbars.RightBar;
import authoring_environment.toolbars.TopBar;
import data.fileReading.GAEGameFileReader;
import data.fileReading.JSONtoGAE;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * EditorWindow implements the CreatorView interface and manages the UI of the
 * editing environment
 *
 * @author Julia Long, Judi Sanchez, Michael Acker
 * Date started: March 30 18
 */
public class EditorWindow implements CreatorView {

	private Stage myStage;
	private Scene myScene;
	private ScrollingGrid myGrid;
	private ScrollPane myScrollPane;

	private AuthoredGame myGame;
	private String myAuthor;

	/**
	 * Creates a new EditorWindow with a specific game
	 * @param stage is the stage where EditorWindow should be presented
	 * @param game is the game to load
	 */
	public EditorWindow(Stage stage, AuthoredGame game) {
		myStage = stage;
		myGame = game;
		openNewWindow();
	}

	/**
	 * Creates a new EditorWindow with a specific game name
	 * This constructor is specifically for use by the Player, who does not have access
	 * to AuthoredGame objects.
	 * @param stage is the current stage
	 * @param gameName is the game name
	 * @param gameDescription is the game description
	 */
	public EditorWindow(Stage stage, String gameName, String gameDescription) {
		JSONtoGAE reader = new GAEGameFileReader();
		myStage = stage;
		try {
			myGame = new AuthoredGame(gameName, gameDescription, reader.loadCompleteAuthoredGame(gameName));
		}
		catch (Exception e) {
			myGame = new AuthoredGame();
			myGame.setAuthorName(myAuthor);
		}
		openNewWindow();
	}

	/**
	 * Creates a new EditorWindow with no game
	 * 
	 * @param stage
	 *            The stage where EditorWindow should be presented
	 */
	public EditorWindow(Stage stage) {
		this(stage, new AuthoredGame());
	}

	@Override
	public AuthoredGame getGame() {
		return myGame;
	}

	@Override
	public void changeCurrentGame(AuthoredGame newGame) {
		myGame = newGame;
	}

	@Override
	public ScrollPane getPane() {
		return myScrollPane;
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
		BorderPane myRoot = new BorderPane();
		myScene = new Scene(myRoot);
		myScene.getStylesheets().add("GAE.css");
		myStage.setMaximized(true);
		myStage.getIcons().add(new Image("./authoring_environment/editor_windows/PencilIcon.png"));
		myGrid = myGame.getCurrentLevel().getScrollingGrid();
		myRoot.setTop(new TopBar(this));
		myRoot.setCenter(setCenterGrid());
		myRoot.setRight(setRightBar());

	}

	private Pane setRightBar() {
		Pane rightBar = new RightBar(this, myScrollPane);
		rightBar.getStyleClass().add("side-pane");
		return rightBar;
	}

	private ScrollPane setCenterGrid() {
    	 myScrollPane = new ScrollPane(myGrid);
    	 myScrollPane.getStyleClass().add("center-pane");
    	 return myScrollPane;
    }
	
	public void setAuthor(String author) {
		myAuthor = author;
		myGame.setAuthorName(myAuthor);
	}
	
}
