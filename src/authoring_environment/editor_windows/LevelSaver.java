package authoring_environment.editor_windows;

import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The level saver class allows the user to save their current level.
 *
 * @author Julia Long
 * Date started
 */
public class LevelSaver {

    private Stage myStage;
    private Pane myRoot;
    private CreatorView myWindow;
    private TextField fileName;
    private TextField fileDescription;
    private CheckBox playableGame;

    private static final String CSS = "GAE.css";
    private static final String SAVE_LEVEL = "Save Level";

    private static final String CHOOSE = "Save this level:";
    private static final String NAME = "File name:";
    private static final String DESCRIPTION = "File description:";

    /**
     * Creates a new Level Saver windoe
     * @param window is the new window
     */
    public LevelSaver(CreatorView window) {
        myWindow = window;
        myStage = new Stage();
        myRoot = new VBox();
        Scene myScene = new Scene(myRoot);
        myScene.getStylesheets().add(CSS);
        myStage.setScene(myScene);
        myStage.setTitle(SAVE_LEVEL);
        myStage.show();
        myStage.centerOnScreen();
    }

    // TODO: construct the window
}
