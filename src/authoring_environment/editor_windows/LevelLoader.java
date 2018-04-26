package authoring_environment.editor_windows;

import data.fileReading.JSONtoGAE;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    }

    private void setScreen() {
        myStage = new Stage();
        myRoot = new VBox();
        myScene = new Scene(myRoot);
        myScene.getStylesheets().add(CSS);
        myStage.setScene(myScene);
        myStage.setTitle(LOAD_LEVEL);
        myStage.show();
        myStage.centerOnScreen();
    }

}
