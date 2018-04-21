package authoring_environment.toolbars.buttons.creator_view_buttons;

import authoring_environment.editor_windows.EditorWindow;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * The New Game Button, when clicked, will launch a window to edit a new game.
 *
 * @author Julia Long
 * Date started: April 18 18
 */
public class NewGameButton extends Button{

    private static final String NEW_GAME = "New game";

    /**
     * Creates the new game button.
     */
    public NewGameButton() {
        super(NEW_GAME);
        this.setOnAction(e -> new EditorWindow(new Stage()));
    }
}
