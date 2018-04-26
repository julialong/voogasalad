package authoring_environment.editor_windows;

import authoring_environment.game_elements.AuthoredLevel;
import data.fileReading.GAEGameFileReader;
import data.fileReading.JSONtoGAE;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class LevelLoader {

    private Stage myStage;
    private Pane myRoot;
    private Scene myScene;
    private ListView<String> myView;
    private Map<String, String> myChoices;
    private JSONtoGAE myReader;
    private CreatorView myWindow;

    private static final String CSS = "GAE.css";
    private static final String LOAD_LEVEL = "Load Level";
    private static final String CHOOSE = "Choose a level to add to your game:";

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
        myScene = new Scene(myRoot);
        myScene.getStylesheets().add(CSS);
        myStage.setScene(myScene);
        myStage.setTitle(LOAD_LEVEL);
    }

    private void addFields() {
        myRoot.getStyleClass().add("game-chooser");
        Text chooseText = new Text(CHOOSE);
        myRoot.getChildren().add(chooseText);
        getLevels();
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



}
