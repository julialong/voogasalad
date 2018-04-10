package authoring_environment.editor_windows;

import authoring_environment.AuthoredGame;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The game saver class allows the user to save their current game.
 *
 * @author Julia Long
 * Date started: April 08 18
 */
public class GameSaver {


    private Stage myStage;
    private Pane myRoot;
    private Scene myScene;
    private AuthoredGame myGame;

    private TextField fileName;
    private TextField fileDescription;
    private CheckBox playableGame;

    private static final String CSS = "GAE.css";
    private static final String SAVE_GAME = "Save Game";

    private static final String CHOOSE = "Save your file:";
    private static final String NAME = "File name:";
    private static final String UNTITLED = "Untitled_Game";
    private static final String DESCRIPTION = "File description:";
    private static final String DEFAULT_DESCRIPTION = "A basic game.";
    private static final String PUBLISH = "Publish game?";

    /**
     * Creates a new game saver window
     * @param game is the current game class
     */
    public GameSaver(AuthoredGame game) {
        myGame = game;
        myStage = new Stage();
        myRoot = new VBox();
        myScene = new Scene(myRoot);
        myScene.getStylesheets().add(CSS);
        addFields();
        myStage.setScene(myScene);
        myStage.setTitle(SAVE_GAME);
        myStage.show();
        myStage.centerOnScreen();
    }

    private void addFields() {
        myRoot.getStyleClass().add("game-saver");
        Text chooseText = new Text(CHOOSE);
        chooseText.setFont(new Font(20));
        myRoot.getChildren().add(chooseText);
        setName();
        setDescription();
        setPlayable();
        addSaveButton();
    }

    private void setName() {
        Pane nameBox = new HBox();
        nameBox.getStyleClass().add("game-saver");
        fileName = new TextField(UNTITLED);
        nameBox.getChildren().addAll(new Text(NAME), fileName);
        myRoot.getChildren().add(nameBox);
    }

    private void setDescription() {
        Pane descriptionBox = new HBox();
        descriptionBox.getStyleClass().add("game-saver");
        fileDescription = new TextField(DEFAULT_DESCRIPTION);
        fileDescription.getStyleClass().add("game-description");
        descriptionBox.getChildren().addAll(new Text(DESCRIPTION), fileDescription);
        myRoot.getChildren().add(descriptionBox);
    }

    private void setPlayable() {
        Pane publishBox = new HBox();
        publishBox.getStyleClass().add("game-saver");
        playableGame = new CheckBox(PUBLISH);
        publishBox.getChildren().add(playableGame);
        myRoot.getChildren().add(publishBox);
    }

    private void addSaveButton() {
        Button saveButton = new Button(SAVE_GAME);
        saveButton.setOnAction(e -> {
            myGame.rename(fileName.getText());
            myGame.setDescription(fileDescription.getText());
            myGame.update();
        });
        myRoot.getChildren().add(saveButton);
    }


}
