package authoring_environment.editor_windows;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import data.fileReading.FileRetriever;
import data.fileReading.GAEGameFileReader;

/**
 * @autho Julia Long
 * @author Judi Sanchez 
 * Date started: April 24 2018
 */
public class GameChooser {

    private Stage myStage;
    private Pane myRoot;
    private Scene myScene;
    private List<Text> myOptions;

    private static final String CSS = "GAE.css";
    private static final String LOAD_GAME = "Load Game";
    private static final String CHOOSE = "Choose a game to edit:";

    public GameChooser() {
        myStage = new Stage();
        myRoot = new VBox();
        myScene = new Scene(myRoot);
        myScene.getStylesheets().add(CSS);
        addFields();
        myStage.setScene(myScene);
        myStage.setTitle(LOAD_GAME);
        myStage.show();
        myStage.centerOnScreen();
        List<String> names = fetchGameNames();
        addOptions(names);
        myRoot.getChildren().addAll(myOptions);
    }

    private void addFields() {
        myRoot.getStyleClass().add("game-chooser");
        Text chooseText = new Text(CHOOSE);
        myRoot.getChildren().add(chooseText);
    }
    
    private List<String> fetchGameNames() {
    		List<String> gameNames= new ArrayList<String>();
    		FileRetriever fileRetriever = new FileRetriever();
    		List<String> gamePaths= fileRetriever.retrieveAllGamePaths();
    		for(String gamePath : gamePaths) {
    			gameNames.add((gamePath.substring(gamePath.lastIndexOf("/")+1)));
    		}
    		return gameNames;
    }
    
    private void addOptions(List<String> gameNames) {
    		myOptions= new ArrayList<Text>();
    		for(String gameName: gameNames) {
    			myOptions.add(new Text(gameName));
    			System.out.println(gameName);
    		}
    }

    
}
