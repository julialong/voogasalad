package authoring_environment.editor_windows.buttons;

import authoring_environment.editor_windows.CreatorView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The Save Settings Button allows the user to save the 
 *
 * @author Judi Sanchez 
 * Data Started: April 22 2018 
 */
public class SaveSettingsButton extends Button{

    private static final String SAVE_SETTINGS = "Save Game Settings";
    private String gameName;
    private String description;

    /**
     * Creates a new SaveGameButton
     */
    public SaveSettingsButton(CreatorView window, Stage stage, TextField gameNameField, TextField descriptionField) {
        super(SAVE_SETTINGS);
        this.setOnAction(e -> saveSettings(window, stage, gameNameField, descriptionField));
    }

    private void saveSettings(CreatorView myWindow, Stage stage, TextField gameNameField, TextField descriptionField) {
        gameName = gameNameField.getText();
        description = descriptionField.getText();
    		myWindow.getGame().rename(gameName);
    		myWindow.getGame().setDescription(description);
    		System.out.println(myWindow.getGame().getName());
    		System.out.println(myWindow.getGame().getDescription());
    		stage.close();
    	
    }

}
