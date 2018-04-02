package authoring_environment.toolbars.buttons;

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
    public SettingsButton() {
        super(SETTINGS);
        // TODO: open settings manager when button is clicked
        // this.setOnAction(e -> new SettingsManager());
    }

}
