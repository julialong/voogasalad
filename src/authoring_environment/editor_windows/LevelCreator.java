package authoring_environment.editor_windows;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The LevelCreator is a separate window that manages the creation of new levels within the game.
 *
 * @author Julia Long
 * Date started: April 02 18
 */
public class LevelCreator {

    private Stage myStage;
    private Scene myScene;
    private BorderPane myRoot;

    /**
     * Creates and launches a new LevelCreator window
     */
    public LevelCreator() {
        myStage = new Stage();
        myRoot = new BorderPane();
        myScene = new Scene(myRoot);
        myStage.show();
        myStage.centerOnScreen();
    }


}
