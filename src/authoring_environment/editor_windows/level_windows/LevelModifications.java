package authoring_environment.editor_windows.level_windows;

import authoring_environment.game_elements.AuthoredLevel;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * The LevelModifications class allows the user to modify a level's background image and color
 *
 * @author Julia Long
 */
abstract class LevelModifications {


    private File selectedImageFile;

    static final String LEVEL_NAME = "Level name";
    private static final String SET_NAME = "Set name";
    private static final String UPLOAD_BACKGROUND_IMAGE = "Upload background image";
    private static final String CHOOSE_FILE = "Choose file";
    private static final String CHOOSE_COLOR = "Choose background color";
    static final int SMALL_FONT = 15;
    static final int LARGE_FONT = 20;

    /**
     * Creates the submit button that changes the level name
     * @param box is the pane to add the level name field to
     * @param newLevel is the level that is being edited
     * @param name is the textfield that contains the name of the level
     * @param instruction is the label that indicates that this field is for the level
     */
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

    /**
     * Creates the button to upload a background image
     * @param myStage is the current stage
     * @param newLevel is the level that is being edited
     * @param pane is the pane to add the upload image name to
     */
    void createUploadImageButton(Stage myStage, AuthoredLevel newLevel, Pane pane) {
        Button backgroundImage = new Button(CHOOSE_FILE);
        backgroundImage.setOnAction(e -> {
            FileChooser imageChooser = new FileChooser();
            imageChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
            selectedImageFile =  imageChooser.showOpenDialog(myStage);
            backgroundImage.setText(selectedImageFile.getPath());
            newLevel.setBackground(new Background(new BackgroundImage(new Image(selectedImageFile.toURI().toString()),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT)));
            newLevel.setImagePath(selectedImageFile.getPath());
        });
        Text uploadImage = new Text(UPLOAD_BACKGROUND_IMAGE);
        uploadImage.setFont(new Font(SMALL_FONT));
        pane.getChildren().add(uploadImage);
        pane.getChildren().add(backgroundImage);
    }

    /**
     * Creates the color picker to choose background image
     * @param newLevel is the level that is being edited
     * @param pane is the pane to add the color picker to
     */
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
