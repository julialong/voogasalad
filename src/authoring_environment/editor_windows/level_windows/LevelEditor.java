package authoring_environment.editor_windows.level_windows;

import authoring_environment.editor_windows.CreatorView;
import authoring_environment.game_elements.AuthoredLevel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Allows the user to modify level attributes after the level has been
 * created.
 *
 * @author Julia Long
 * Date started: April 26 18
 */
public class LevelEditor extends LevelModifications{

    private static final String CSS = "GAE.css";
    private static final String EDITING = "Editing: ";
    private static final String SAVE_EDITS = "Save edited level";

    private static final String SET_SIZE = "Set size";

    private Stage myStage;
    private Scene myScene;
    private BorderPane myRoot;
    private CreatorView myWindow;

    private AuthoredLevel myLevel;
    private TextField indexInput;
    private TextField xSizeInput;
    private TextField ySizeInput;

    public LevelEditor(CreatorView window, AuthoredLevel level) {
        myWindow = window;
        myLevel = level;
        myStage = new Stage();
        myRoot = new BorderPane();
        addFields();
        myScene = new Scene(myRoot);
        myScene.getStylesheets().add(CSS);
        myStage.setScene(myScene);
        myStage.setTitle(EDITING + myLevel.getName());
        myStage.show();
        myStage.centerOnScreen();
    }

    private void addFields(){
        createTop();
        createCenter();
    }

    private void createTop() {
        HBox box = new HBox();
        box.getStyleClass().add("level-top");
        Label instruction = new Label(LEVEL_NAME);
        instruction.setFont(new Font(SMALL_FONT));
        box.getChildren().add(instruction);
        TextField name = new TextField(myLevel.getName());
        box.getChildren().add(name);
        createSubmitButton(box, myLevel, name, instruction);
        myRoot.setTop(box);
    }

    private void createCenter() {
        Pane center = new VBox();
        center.getStyleClass().add("level-editor");
        createUploadImageButton(myStage, myLevel, center);
        createBackgroundColorPicker(myLevel, center);
        createSizeChooser(center);
        createIndexChooser(center);
        createSaveButton(center);
        myRoot.setCenter(center);
    }

    private void createSizeChooser(Pane pane) {
        Text xField = new Text("X size: ");
        xSizeInput = new TextField(Integer.toString(myLevel.getGridSize()[1]));
        HBox x = new HBox(xField, xSizeInput);
        Text yField = new Text("Y size: ");
        ySizeInput = new TextField(Integer.toString(myLevel.getGridSize()[0]));
        HBox y = new HBox(yField, ySizeInput);
        Text setSize = new Text(SET_SIZE);
        setSize.setFont(new Font(SMALL_FONT));
        pane.getChildren().addAll(setSize, x, y);
    }

    private void createIndexChooser(Pane pane) {
        Label chooseLocation = new Label("Location");
        chooseLocation.setFont(new Font(SMALL_FONT));
        indexInput = new TextField(Integer.toString(myWindow.getGame().getLevels().indexOf(myLevel)));
        pane.getChildren().addAll(chooseLocation, indexInput);
    }

    private void createSaveButton(Pane pane) {
        Button saveButton = new Button(SAVE_EDITS);
        saveButton.setOnMouseClicked(e -> saveEdits());
        pane.getChildren().add(saveButton);
    }

    private void saveEdits() {
        myLevel.setSize(Integer.parseInt(xSizeInput.getText()), Integer.parseInt(ySizeInput.getText()));
        myWindow.getGame().removeLevel(myLevel);
        myWindow.getGame().addLevel(Integer.parseInt(indexInput.getText()), myLevel);
        myStage.close();
    }

}
