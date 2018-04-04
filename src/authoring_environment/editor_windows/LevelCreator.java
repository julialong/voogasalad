package authoring_environment.editor_windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    private static final String LEVEL_NAME = "Level name";
    private static final String SET_NAME = "Set name";

    /**
     * Creates and launches a new LevelCreator window
     */
    public LevelCreator() {
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

    private void addFields() {
        myRoot.setTop(createTop());
    }

    private HBox createTop() {
        HBox box = new HBox();
        box.getStyleClass().add("level-creator");
        Label instruction = new Label(LEVEL_NAME);
        box.getChildren().add(instruction);
        TextField name = new TextField();
        box.getChildren().add(name);
        createSubmitButton(box, name, instruction);
        return box;
    }

    private HBox createRight() {
        return new HBox();
    }

    private HBox createCenter() {
        return new HBox();
    }

    private void createSubmitButton(Pane box, TextField name, Label instruction) {
        Button submitButton = new Button(SET_NAME);
        submitButton.setOnAction(e -> {
            box.getChildren().removeAll(name,submitButton,instruction);
            box.getChildren().add(new Text(name.getText()));
        });
        box.getChildren().add(submitButton);
    }

}
