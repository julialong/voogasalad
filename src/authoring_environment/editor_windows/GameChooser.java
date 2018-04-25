package authoring_environment.editor_windows;

import data.fileReading.GAEGameFileReader;
import data.fileReading.GPGameFileReader;
import data.fileReading.JSONtoGAE;
import data.fileReading.JSONtoGP;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

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
        getGames();
    }

    private void getGames() {
        JSONtoGAE gameReader = new GAEGameFileReader();
        // TODO: delete JSONtoGP
        JSONtoGP fakeGameReader = new GPGameFileReader();
        try {
            Map<String, String> gameNames = fakeGameReader.getGameNames();
            myRoot.getChildren().add(showGames(gameNames));
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(e.getCause().toString());
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    private ListView<String> showGames(Map<String, String> games) {
        ObservableList<String> gameNames = FXCollections.observableArrayList(games.keySet());
        return new ListView<>(gameNames);
    }
}
