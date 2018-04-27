package authoring_environment.editor_windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import authoring_environment.editor_windows.buttons.GameChooserButton;
import data.fileReading.GAEGameFileReader;
import data.resources.DataFileException;

/**
 * @author Julia Long
 * @author Judi Sanchez 
 * Date started: April 24 2018
 */
// TODO: edit styling and move styling specifications to css
public class GameChooser {

    private Stage myStage;
    private Pane myRoot;
    private Scene myScene;
    private Map<String, String> gameNames;
    private TilePane myTiles;

    private static final String CSS = "GAE.css";
    private static final String LOAD_GAME = "Load Game";
    private static final String CHOOSE = "Choose a game to edit:";
    private static final int BUTTON_SIZE = 120;

    /**
     * This class opens the window to choose a previously created game for re-editing 
     * @throws DataFileException 
     */
    public GameChooser() throws DataFileException {
        myStage = new Stage();
        myRoot = new VBox();
        myScene = new Scene(myRoot);
        myScene.getStylesheets().add(CSS);
        addFields();
        myStage.setScene(myScene);
        myStage.setTitle(LOAD_GAME);
        myStage.show();
        myStage.centerOnScreen();
        myTiles = new TilePane();
        myRoot.getChildren().add(myTiles);
        gameNames = fetchGameNames();
        addButtons();
    }

    private void addFields() {
        myRoot.getStyleClass().add("game-chooser");
        Text chooseText = new Text(CHOOSE);
        myRoot.getChildren().add(chooseText);
    }
    
    /**
     * Gets a list of all of the current games names by looking at the FileRetriever's file paths for
     * games and removing all but the name
     * @throws DataFileException 
     */
    private Map<String, String> fetchGameNames() throws DataFileException {
    		gameNames= new HashMap<String, String>();
    		GAEGameFileReader fileRetriever = new GAEGameFileReader();
    		gameNames= fileRetriever.getGameNames();
    		return gameNames;
    }
    
    /**
     * Creates a tile representation of the buttons to choose a game to re-load 
     * The method to actually load the chosen game is in GameChooserButton
     */
    private void addButtons() {
    		for(String gameName: gameNames.keySet()) {
    				Button button = new GameChooserButton(gameName);
    				button.setMinSize(BUTTON_SIZE, BUTTON_SIZE);
    				button.setMaxSize(BUTTON_SIZE, BUTTON_SIZE);
    				myTiles.getChildren().add(button);
    		}
    				
    }

}
