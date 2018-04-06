package authoring_environment.editor_windows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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

    private Pane right;
    private Pane center;

    private File selectedImageFile;

    private static final String CSS = "GAE.css";
    private static final String LEVEL_CREATOR = "Level Creator";
    private static final String LEVEL_NAME = "Level name";
    private static final String SET_NAME = "Set name";
    private static final String UPLOAD_BACKGROUND_IMAGE = "Upload background image";
    private static final String CHOOSE_FILE = "Choose file";
    private static final String CHOOSE_COLOR = "Choose background color";
    private static final String SET_SIZE = "Set size";
    private static final String SAVE_LEVEL = "Save level";

    /**
     * Creates and launches a new LevelCreator window
     */
    public LevelCreator() {
        createNewLevel();
        myStage = new Stage();
        myRoot = new BorderPane();
        addFields();
        myScene = new Scene(myRoot);
        myScene.getStylesheets().add(CSS);

        myStage.setScene(myScene);
        myStage.setTitle(LEVEL_CREATOR);
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
        instruction.setFont(new Font(15));
        box.getChildren().add(instruction);
        TextField name = new TextField();
        box.getChildren().add(name);
        createSubmitButton(box, name, instruction);
        return box;
    }

    private Pane createRight() {
        right = new VBox();
        right.getStyleClass().add("level-right");
        createUploadImageButton(right);
        createBackgroundColorPicker(right);
        createSizeChooser(right);
        createSaveButton(right);
        return right;
    }

    private Pane createCenter() {
        center = new HBox();
        center.getStyleClass().add("level-center");
        // TODO: show level display
        return center;
    }

    private void createSubmitButton(Pane box, TextField name, Label instruction) {
        Button submitButton = new Button(SET_NAME);
        submitButton.setOnAction(e -> {
            box.getChildren().removeAll(name,submitButton,instruction);
            Text levelName = new Text(name.getText());
            levelName.setFont(new Font(20));
            box.getChildren().add(levelName);
            // TODO: assign the text as the Level object name
            // newLevel.setName(name.getText());
        });
        box.getChildren().add(submitButton);
    }

    private void createUploadImageButton(Pane pane) {
        Button backgroundImage = new Button(CHOOSE_FILE);
        backgroundImage.setOnAction(e -> {
            FileChooser imageChooser = new FileChooser();
            imageChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
            selectedImageFile =  imageChooser.showOpenDialog(myStage);
            // TODO: set Level background to file
            // TODO: update level view
        });
        Text uploadImage = new Text(UPLOAD_BACKGROUND_IMAGE);
        uploadImage.setFont(new Font(15));
        pane.getChildren().add(uploadImage);
        pane.getChildren().add(backgroundImage);
    }

    private void createBackgroundColorPicker(Pane pane) {
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(e -> {
            Color chosenColor = colorPicker.getValue();
            center.setBackground(new Background(new BackgroundFill(chosenColor, CornerRadii.EMPTY, Insets.EMPTY)));
            // TODO: set Level background color to chosenColor
            // TODO: update level view
        });
        Text chooseColor = new Text(CHOOSE_COLOR);
        chooseColor.setFont(new Font(15));
        pane.getChildren().add(chooseColor);
        pane.getChildren().add(colorPicker);
    }

    private void createSizeChooser(Pane pane) {
        HBox xSize = new HBox();
        TextField xNumber = new TextField();
        Button submitX = new Button("Set X");
        submitX.setOnAction(e -> {
            // set x size of grid to xNumber
        });
        xSize.getChildren().addAll(xNumber, submitX);
        HBox ySize = new HBox();
        TextField yNumber = new TextField();
        Button submitY = new Button("Set Y");
        submitY.setOnAction(e -> {
            // set y size of grid to yNumber
        });
        ySize.getChildren().addAll(yNumber, submitY);
        Text setSize = new Text(SET_SIZE);
        setSize.setFont(new Font(15));
        pane.getChildren().addAll(setSize, xSize, ySize);
    }

    private void createSaveButton(Pane pane) {
        Button saveButton = new Button(SAVE_LEVEL);
        pane.getChildren().add(saveButton);
    }
}
