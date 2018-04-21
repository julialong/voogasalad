package authoring_environment.editor_windows.buttons;

import authoring_environment.editor_windows.CreatorView;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * The Save Game Button allows the user to save their game to a file.
 *
 * @author Julia Long
 */
public class SaveGameButton extends Button{

    private static final String SAVE_GAME = "Save Game";

    /**
     * Creates a new SaveGameButton
     */
    public SaveGameButton(CreatorView window, Stage current, String name, String description, boolean playable) {
        super(SAVE_GAME);
        this.setOnAction(e -> setGameSaved(window, current, name, description, playable));
    }

    private void setGameSaved(CreatorView window, Stage current, String name, String description, boolean playable) {
        window.getGame().rename(name);
        window.getGame().setDescription(description);
        window.getGame().setPlayable(playable);
        window.getGame().update();
        current.close();
    }
}
