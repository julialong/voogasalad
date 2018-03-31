import authoring_environment.CreatorView;
import authoring_environment.EditorWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is the entry point to the Graphic Authoring Environment and
 * launches the necessary classes to start game editing
 *
 * @author julialong
 */
public class EditorMain extends Application{

    /**
     * Calls the necessary classes to open the editing environment
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        new EditorWindow();
    }

    /**
     * Runs the Editing environment
     */
    public static void main(String[] args) {
        launch(args);
    }
}
