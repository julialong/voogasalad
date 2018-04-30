package authoring_environment.editor_windows.level_windows;

import authoring_environment.editor_windows.CreatorView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The level saver class allows the user to save their current level.
 *
 * @author Julia Long
 */
public class LevelSaver {

    private Stage myStage;
    private Pane myRoot;
    private CreatorView myWindow;

    private static final String CSS = "GAE.css";
    private static final String CSS_CLASS = "level-saver";
    private static final String SAVE_LEVEL = "Save Level";
    private static final String CHOOSE = "Save this level: ";
    private static final int FONT_SIZE = 20;

    /**
     * Creates a new Level Saver window
     * @param window is the new window
     */
    public LevelSaver(CreatorView window) {
        myWindow = window;
        myStage = new Stage();
        myRoot = new VBox();
        Scene myScene = new Scene(myRoot);
        setStyle();
        showCurrentName();
        saveButton();
        myStage.setScene(myScene);
        myStage.setTitle(SAVE_LEVEL);
        myStage.show();
        myStage.centerOnScreen();
    }

    private void setStyle() {
        myRoot.getStylesheets().add(CSS);
        myRoot.getStyleClass().add(CSS_CLASS);
    }

    private void showCurrentName() {
        HBox nameBox = new HBox();
        nameBox.getStyleClass().add(CSS_CLASS);
        Text nameText = new Text(CHOOSE + myWindow.getGame().getCurrentLevel().getName());
        nameText.setFont(new Font(FONT_SIZE));
        nameBox.getChildren().add(nameText);
        myRoot.getChildren().add(nameBox);
    }

    private void saveButton() {
        Button saveButton = new Button(SAVE_LEVEL);
        saveButton.setOnMouseClicked(e -> {
            myWindow.getGame().saveLevel(myWindow.getGame().getCurrentLevel());
            myStage.close();
        });
        myRoot.getChildren().add(saveButton);
    }

}
