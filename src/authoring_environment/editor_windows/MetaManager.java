package authoring_environment.editor_windows;

import authoring_environment.game_elements.AuthoredGame;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Contains default methods needed to modify the game name, description, and playable state.
 *
 * @author Julia Long
 * Date started: April 10 18
 */
public interface MetaManager {

    String UPDATE_FILE_NAME = "File name:";

    default void setName(TextField fileName, AuthoredGame myGame, Pane myRoot) {
        Pane nameBox = new HBox();
        nameBox.getStyleClass().add("game-saver");
        fileName = new TextField(myGame.getName());
        nameBox.getChildren().addAll(new Text(UPDATE_FILE_NAME), fileName);
        myRoot.getChildren().add(nameBox);
    }
}
