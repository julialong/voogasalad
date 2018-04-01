package authoring_environment.toolbars.buttons;

import javafx.scene.control.MenuButton;

/**
 * The LoadButton, when clicked, triggers a drop-down menu that allows the user
 * to choose whether they would like to load a game or level for editing
 *
 * @author julialong
 */
public class LoadButton extends MenuButton {

    private static final String LOAD = "Load";

    /**
     * Creates a simple Load menu button with the appropriate drop down items
     */
    public LoadButton() {
        super(LOAD);
    }
}
