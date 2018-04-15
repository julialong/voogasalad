import authoring_environment.editor_windows.EditorWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is the entry point to the Graphic Authoring Environment and
 * launches the necessary classes to start game editing
 *
 * @author Julia Long
 * Date started: March 31 18
 */
public class EditorMain extends Application {

    /**
     * Calls the necessary classes to open the editing environment
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        new EditorWindow(primaryStage);
    }

    /**
     * Runs the Editing environment
     */
    public static void main(String[] args) {
        launch(args);
    }
}
