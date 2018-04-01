package authoring_environment;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * EditorWindow implements the CreatorView interface and manages the UI of the editing environment
 *
 * @author julialong
 */
public class EditorWindow implements CreatorView {

    private Stage myStage;
    private Scene myScene;
    private Pane myRoot;

    public EditorWindow(Stage stage) {
        myStage = stage;
        openNewWindow();
    }

    @Override
    public void updateGrid() {
    }

    @Override
    public void updateGridView(double location) {

    }

    @Override
    public void openAttributeEditor(AuthoredElement elem) {

    }

    @Override
    public void openNewWindow() {
        setupNewWindow();
        myStage.setScene(myScene);
        // TODO: read from Resource file
        myStage.setTitle("Game Authoring Environment");
        myStage.show();
    }


    private void setupNewWindow() {
        myRoot = new BorderPane();
        // TODO: read these from Resource file
        myScene = new Scene(myRoot);
        myStage.setMaximized(true);
        // TODO: add Toolbars

        // TODO: add Grid
    }
}
