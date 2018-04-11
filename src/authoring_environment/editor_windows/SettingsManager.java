package authoring_environment.editor_windows;

import authoring_environment.AuthoredGame;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The settings manager allows the user to change the settings for the current game they
 * are editing.
 *
 * @author Julia Long
 * Date started: April 04 18
 */
public class SettingsManager implements MetaManager {

    private Stage myStage;
    private Pane myRoot;
    private Scene myScene;

    private AuthoredGame myGame;
    private TextField fileName;

    private static final String CSS = "GAE.css";
    private static final String SETTINGS_MANAGER = "Settings Manager";
    private static final String UPDATE_FILE_NAME = "File name:";
    private static final String SUBMIT = "Submit";

    /**
     * Creates a new settings manager window.
     */
    public SettingsManager(AuthoredGame game) {
        myStage = new Stage();
        myRoot = new VBox();
        myScene = new Scene(myRoot);
        myScene.getStylesheets().add(CSS);
        myRoot.getStyleClass().add("game_saver");
        myGame = game;

        myStage.setScene(myScene);
        myStage.setTitle(SETTINGS_MANAGER);
        myStage.show();
        myStage.centerOnScreen();

        setName(fileName, myGame, myRoot);
    }

    private void saveSettings() {
        Button submitButton = new Button(SUBMIT);
        submitButton.setOnAction(e -> {
            myGame.rename(fileName.getText());

        });
    }

}
