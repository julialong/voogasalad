package authoring_environment.editor_windows;

import data.fileReading.GAEGameFileReader;
import data.fileReading.JSONtoGAE;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Map;

/**
 *
 */
public class GameChooser {

    private Stage myStage;
    private Pane myRoot;
    private Scene myScene;

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
    }

    private void addFields() {
        myRoot.getStyleClass().add("game-chooser");
        Text chooseText = new Text(CHOOSE);
        myRoot.getChildren().add(chooseText);
    }

    private void getGames() {
        JSONtoGAE gameReader = new GAEGameFileReader();
        //Map <String, String> gameNames = gameReader.getGameNames();
    }
}
