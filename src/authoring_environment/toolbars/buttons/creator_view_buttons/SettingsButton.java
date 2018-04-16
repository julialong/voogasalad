package authoring_environment.toolbars.buttons.creator_view_buttons;

import authoring_environment.editor_windows.CreatorView;
import authoring_environment.game_elements.AuthoredGame;
import authoring_environment.editor_windows.SettingsManager;
import javafx.scene.control.Button;

/**
 * The SettingsButton, when clicked, opens a settings menu that allows the user
 * to manipulate the settings for the game.
 *
 * @author julialong
 * Date started: April 01 18
 */
public class SettingsButton extends Button{


    private static final String SETTINGS = "Settings";

    /**
     * Creates a simple Settings menu button
     */
    public SettingsButton(CreatorView window) {
        super(SETTINGS);
        this.setOnAction(e -> new SettingsManager(window));
    }

}
