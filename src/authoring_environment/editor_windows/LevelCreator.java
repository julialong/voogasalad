package authoring_environment.editor_windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

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
    // private Level newLevel;

    File selectedImageFile;

    private static final String LEVEL_NAME = "Level name";
    private static final String SET_NAME = "Set name";
    private static final String UPLOAD_BACKGROUND_IMAGE = "Upload background image";

    /**
     * Creates and launches a new LevelCreator window
     */
    public LevelCreator() {
        createNewLevel();
        myStage = new Stage();
        myRoot = new BorderPane();
        addFields();
        myScene = new Scene(myRoot);
        myScene.getStylesheets().add("GAE.css");

        myStage.setScene(myScene);
        myStage.setTitle("Level Creator");
        myStage.show();
        myStage.centerOnScreen();
    }

    private void createNewLevel() {
        // TODO: create New level object
        // newLevel = new BasicLevel();
    }

    private void addFields() {
        myRoot.setTop(createTop());
        myRoot.setRight(createRight());
        myRoot.setCenter(createCenter());
    }

    private HBox createTop() {
        HBox box = new HBox();
        box.getStyleClass().add("level-top");
        Label instruction = new Label(LEVEL_NAME);
        box.getChildren().add(instruction);
        TextField name = new TextField();
        box.getChildren().add(name);
        createSubmitButton(box, name, instruction);
        return box;
    }

    private HBox createRight() {
        HBox box = new HBox();
        box.getStyleClass().add("level-right");
        Button uploadImageButton = createUploadImageButton();
        box.getChildren().add(uploadImageButton);
        return box;
    }

    private HBox createCenter() {
        HBox box = new HBox();
        box.getStyleClass().add("level-center");
        // TODO: show level display
        return box;
    }

    private void createSubmitButton(Pane box, TextField name, Label instruction) {
        Button submitButton = new Button(SET_NAME);
        submitButton.setOnAction(e -> {
            box.getChildren().removeAll(name,submitButton,instruction);
            box.getChildren().add(new Text(name.getText()));
            // TODO: assign the text as the Level object name
            // newLevel.setName(name.getText());
        });
        box.getChildren().add(submitButton);
    }

    private Button createUploadImageButton() {
        Button backgroundImage = new Button(UPLOAD_BACKGROUND_IMAGE);
        backgroundImage.setOnAction(e -> {
            FileChooser imageChooser = new FileChooser();
            imageChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
            selectedImageFile =  imageChooser.showOpenDialog(myStage);
            // TODO: set Level background to file
        });
        return backgroundImage;
    }

}
