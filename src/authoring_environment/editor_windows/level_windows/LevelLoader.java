package authoring_environment.editor_windows.level_windows;

import authoring_environment.DataAlert;
import authoring_environment.editor_windows.CreatorView;
import authoring_environment.game_elements.AuthoredLevel;
import data.fileReading.GAEGameFileReader;
import data.fileReading.JSONtoGAE;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

/**
 * Loads a given level to include in the current game.
 *
 * @author Julia Long
 */
public class LevelLoader implements DataAlert {

    private Stage myStage;
    private Pane myRoot;
    private ListView<String> myView;
    private JSONtoGAE myReader;
    private CreatorView myWindow;

    private static final String CSS = "GAE.css";
    private static final String LOAD_LEVEL = "Load Level";
    private static final String CHOOSE = "Choose a level to add to your game:";
    private static final String ADD_LEVEL = "Add level to game";

    /**
     * Creates a new LevelLoader window
     * @param window is the current editor window
     */
    public LevelLoader(CreatorView window) {
        myWindow = window;
        setScreen();
        addFields();
        myStage.show();
        myStage.centerOnScreen();
    }

    private void setScreen() {
        myStage = new Stage();
        myRoot = new VBox();
        Scene myScene = new Scene(myRoot);
        myScene.getStylesheets().add(CSS);
        myStage.setScene(myScene);
        myStage.setTitle(LOAD_LEVEL);
    }

    private void addFields() {
        myRoot.getStyleClass().add("game-chooser");
        Text chooseText = new Text(CHOOSE);
        myRoot.getChildren().add(chooseText);
        getLevels();
        addButton();
    }

    private void getLevels() {
        myReader = new GAEGameFileReader();
        List<String> strayLevels = myReader.loadAuthoredLevelNames();
        showLevels(strayLevels);
    }

    private void showLevels(List<String> list) {
        myView = new ListView<>(FXCollections.observableArrayList(list));
        myRoot.getChildren().add(myView);
    }

    private void addButton() {
        Button addLevelButton = new Button(ADD_LEVEL);
        addLevelButton.setOnMouseClicked(e -> addLevel());
        myRoot.getChildren().add(addLevelButton);
    }

    private void addLevel() {
        try {
            AuthoredLevel loadedLevel = myReader.loadAuthoredLevel(myView.getSelectionModel().getSelectedItem());
            myWindow.getGame().addLevel(loadedLevel);
            myStage.close();
        }
        catch (Exception e) {
            saveAlert(e);
        }
    }

}
