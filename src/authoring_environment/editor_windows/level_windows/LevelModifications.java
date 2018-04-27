package authoring_environment.editor_windows.level_windows;

import authoring_environment.game_elements.AuthoredLevel;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

abstract class LevelModifications {


    private File selectedImageFile;

    private static final String CSS = "GAE.css";
    private static final String LEVEL_CREATOR = "Level Creator";
    protected static final String LEVEL_NAME = "Level name";
    private static final String SET_NAME = "Set name";
    private static final String UPLOAD_BACKGROUND_IMAGE = "Upload background image";
    private static final String CHOOSE_FILE = "Choose file";
    private static final String CHOOSE_COLOR = "Choose background color";
    private static final String SET_SIZE = "Set size";
    private static final String SAVE_LEVEL = "Save level";
    protected static final int SMALL_FONT = 15;
    protected static final int LARGE_FONT = 20;

    void createSubmitButton(Pane box, AuthoredLevel newLevel, TextField name, Label instruction) {
        Button submitButton = new Button(SET_NAME);
        submitButton.setOnAction(e -> {
            box.getChildren().removeAll(name,submitButton,instruction);
            Text levelName = new Text(name.getText());
            levelName.setFont(new Font(LARGE_FONT));
            box.getChildren().add(levelName);
            newLevel.setName(name.getText());
        });
        box.getChildren().add(submitButton);
    }

    void createUploadImageButton(Stage myStage, AuthoredLevel newLevel, Pane pane) {
        Button backgroundImage = new Button(CHOOSE_FILE);
        backgroundImage.setOnAction(e -> {
            FileChooser imageChooser = new FileChooser();
            imageChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
            selectedImageFile =  imageChooser.showOpenDialog(myStage);
            newLevel.setBackground(new Background(new BackgroundImage(new Image(selectedImageFile.toURI().toString()),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT)));
        });
        Text uploadImage = new Text(UPLOAD_BACKGROUND_IMAGE);
        uploadImage.setFont(new Font(SMALL_FONT));
        pane.getChildren().add(uploadImage);
        pane.getChildren().add(backgroundImage);
    }

    void createBackgroundColorPicker(AuthoredLevel newLevel, Pane pane) {
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(e -> {
            Color chosenColor = colorPicker.getValue();
            newLevel.setBackground(new Background(new BackgroundFill(chosenColor, CornerRadii.EMPTY, Insets.EMPTY)));
            newLevel.setColor(chosenColor);
        });
        Text chooseColor = new Text(CHOOSE_COLOR);
        chooseColor.setFont(new Font(SMALL_FONT));
        pane.getChildren().add(chooseColor);
        pane.getChildren().add(colorPicker);
    }

}
