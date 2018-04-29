package authoring_environment.editor_windows.game_windows;

import authoring_environment.DataAlert;
import authoring_environment.editor_windows.EditorWindow;
import authoring_environment.game_elements.AuthoredGame;
import authoring_environment.game_elements.AuthoredLevel;
import data.fileReading.GAEGameFileReader;
import data.fileReading.JSONtoGAE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.Map;

/**
 * Allows the user to pick a saved game to load into their authoring environment.
 *
 * @author Julia Long
 *
 * Date started: April 24 18
 */
public class GameChooser implements DataAlert {

    private Stage myStage;
    private Pane myRoot;
    private ListView<String> myView;
    private Map<String, String> myChoices;
    private JSONtoGAE myReader;

    private static final String CSS = "GAE.css";
    private static final String LOAD_GAME = "Load Game";
    private static final String CHOOSE = "Choose a game to edit:";

    public GameChooser() {
        myStage = new Stage();
        myRoot = new VBox();
        Scene myScene = new Scene(myRoot);
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
        addSubmitButton();
    }

    private void getGames() {
        myReader = new GAEGameFileReader();
        try {
            myChoices = myReader.getGameNames();
            showGames();
            myRoot.getChildren().add(myView);
        }
        catch (Exception e) {
            saveAlert(e);
        }
    }

    private void showGames() {
        ObservableList<String> gameNames = FXCollections.observableArrayList(myChoices.keySet());
        myView = new ListView<>(gameNames);
    }

    private void addSubmitButton() {
        Button submitButton = new Button(LOAD_GAME);
        submitButton.setOnMouseClicked(e -> loadGame());
        myRoot.getChildren().add(submitButton);
    }

    private void loadGame() {
        try {
            String chosenGame = myView.getSelectionModel().getSelectedItem();
            List<AuthoredLevel> loadLevels = myReader.loadCompleteAuthoredGame(chosenGame);
            System.out.println(chosenGame + ": " + loadLevels.size());
            new EditorWindow(new Stage(), new AuthoredGame(chosenGame, myChoices.get(chosenGame), loadLevels));
            myStage.close();
        }
        catch (Exception e) {
            saveAlert(e);
        }
    }
}
