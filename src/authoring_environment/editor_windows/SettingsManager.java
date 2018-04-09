package authoring_environment.editor_windows;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The settings manager allows the user to change the settings for the current game they
 * are editing.
 *
 * @author Julia Long
 * Date started: April 04 18
 */
public class SettingsManager {

    private Stage myStage;
    private Pane myRoot;
    private Scene myScene;

    private static final String CSS = "GAE.css";
    private static final String SETTINGS_MANAGER = "Settings Manager";

    /**
     * Creates a new settings manager window.
     */
    public SettingsManager() {
        myStage = new Stage();
        myRoot = new VBox();
        myScene = new Scene(myRoot);
        myScene.getStylesheets().add(CSS);

        myStage.setScene(myScene);
        myStage.setTitle(SETTINGS_MANAGER);
        myStage.show();
        myStage.centerOnScreen();
    }

    private void saveName() {

    }

}
