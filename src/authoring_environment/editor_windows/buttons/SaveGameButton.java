package authoring_environment.editor_windows.buttons;

import authoring_environment.editor_windows.CreatorView;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
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
    public SaveGameButton(CreatorView window, Stage current, TextField name, TextField description, CheckBox playable) {
        super(SAVE_GAME);
        this.setOnAction(e -> setGameSaved(window, current, name.getText(), description.getText(), playable.isSelected()));
    }

    private void setGameSaved(CreatorView window, Stage current, String name, String description, boolean playable) {
        System.out.println(name + ", " + description);
        window.getGame().rename(name);
        window.getGame().setDescription(description);
        window.getGame().setPlayable(playable);
        window.getGame().update();
        current.close();
    }
}
