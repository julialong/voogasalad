package authoring_environment.editor_windows.level_windows;

import authoring_environment.editor_windows.CreatorView;
import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import engine.level.BasicLevel;
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
 * The LevelCreator is a separate window that manages the creation of new levels within the game.
 *
 * @author Julia Long
 * Date started: April 02 18
 */
public class LevelCreator extends LevelModifications{

    private Stage myStage;
    private BorderPane myRoot;
    private AuthoredLevel newLevel;
    private CreatorView myWindow;

    private TextField indexInput;
    private TextField xSizeInput;
    private TextField ySizeInput;

    private static final String CSS = "GAE.css";
    private static final String LEVEL_CREATOR = "Level Creator";
    private static final String LEVEL_NAME = "Level name";
    private static final String SET_SIZE = "Set size";
    private static final String SAVE_LEVEL = "Save level";
    private static final int SMALL_FONT = 15;

    /**
     * Creates and launches a new LevelCreator window
     */
    public LevelCreator(CreatorView window) {
        myWindow = window;
        createNewLevel();
        myStage = new Stage();
        myRoot = new BorderPane();
        addFields();
        Scene myScene = new Scene(myRoot);
        myScene.getStylesheets().add(CSS);
        myStage.setScene(myScene);
        myStage.setTitle(LEVEL_CREATOR);
        myStage.show();
        myStage.centerOnScreen();
    }

    /**A
     * Creates a new level with default size.
     */
    private void createNewLevel() {
        newLevel = new AuthoredLevel(new BasicLevel(), new ScrollingGrid());
    }

    /**
     * Adds top, right, and center panes to the level creator window.
     */
    private void addFields() {
        myRoot.setTop(createTop());
        myRoot.setRight(createRight());
        myRoot.setCenter(createCenter());
    }

    private HBox createTop() {
        HBox box = new HBox();
        box.getStyleClass().add("level-top");
        Label instruction = new Label(LEVEL_NAME);
        instruction.setFont(new Font(SMALL_FONT));
        box.getChildren().add(instruction);
        TextField name = new TextField();
        box.getChildren().add(name);
        createSubmitButton(box, newLevel, name, instruction);
        return box;
    }

    private Pane createRight() {
        Pane right = new VBox();
        right.getStyleClass().add("level-right");
        createUploadImageButton(myStage, newLevel, right);
        createBackgroundColorPicker(newLevel, right);
        createSizeChooser(right);
        createIndexChooser(right);
        createSaveButton(right);
        return right;
    }

    private Pane createCenter() {
        Pane center = new HBox();
        center.getStyleClass().add("level-center");
        // TODO: show grid preview
        return center;
    }

    private void createSizeChooser(Pane pane) {
        Text xField = new Text("Y size: ");
        xSizeInput = new TextField("50");
        HBox x = new HBox(xField, xSizeInput);
        Text yField = new Text("X size: ");
        ySizeInput = new TextField("100");
        HBox y = new HBox(yField, ySizeInput);
        Text setSize = new Text(SET_SIZE);
        setSize.setFont(new Font(SMALL_FONT));
        pane.getChildren().addAll(setSize, x, y);
    }

    private void createIndexChooser(Pane pane) {
        Label chooseLocation = new Label("Location");
        chooseLocation.setFont(new Font(SMALL_FONT));
        indexInput = new TextField(Integer.toString(myWindow.getGame().getLevels().size()));
        pane.getChildren().addAll(chooseLocation, indexInput);
    }

    private void createSaveButton(Pane pane) {
        Button saveButton = new Button(SAVE_LEVEL);
        saveButton.setOnAction(e -> {
            newLevel.setSize(Integer.parseInt(xSizeInput.getText()), Integer.parseInt(ySizeInput.getText()));
            myWindow.getGame().addLevel(Integer.parseInt(indexInput.getText()), newLevel);
            myStage.close();
        });
        pane.getChildren().add(saveButton);
    }
}
