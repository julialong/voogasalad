package authoring_environment.editor_windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
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
    private List<String> gameNames;
    private List<HBox> rows;

    private static final String CSS = "GAE.css";
    private static final String LOAD_GAME = "Load Game";
    private static final String CHOOSE = "Choose a game to edit:";
    private static final int BUTTONS_PER_ROW = 5;
    private static final int BUTTON_SIZE = 50;

    /**
     * 
     */
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
        gameNames = fetchGameNames();
        createHBoxes();
        addButtons();
    }

    private void addFields() {
        myRoot.getStyleClass().add("game-chooser");
        Text chooseText = new Text(CHOOSE);
        myRoot.getChildren().add(chooseText);
    }
    
    private List<String> fetchGameNames() {
    		gameNames= new ArrayList<String>();
    		FileRetriever fileRetriever = new FileRetriever();
    		List<String> gamePaths= fileRetriever.retrieveAllGamePaths();
    		for(String gamePath : gamePaths) {
    			gameNames.add((gamePath.substring(gamePath.lastIndexOf("/")+1)));
    		}
    		return gameNames;
    }
    
    private void createHBoxes() {
    		rows = new ArrayList<HBox>();
    		for(int i=0; i < gameNames.size()/BUTTONS_PER_ROW; i++){
    			rows.add(new HBox());
    		}
    		myRoot.getChildren().addAll(rows);
    }
    
    private void addButtons() {
    		for(int i =0; i< rows.size(); i++) {
    			HBox box = rows.get(i);
    			for(int j=0; j<BUTTONS_PER_ROW; j++) {
    				Button button = new Button(gameNames.get(i*BUTTONS_PER_ROW + j));
    				button.setMinSize(BUTTON_SIZE, BUTTON_SIZE);
    				box.getChildren().add(button);
    				
    			}
    			
    		}
    }

    
}
