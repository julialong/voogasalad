package authoring_environment.editor_windows.buttons;

import authoring_environment.editor_windows.CreatorView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The Save Settings Button allows the user to save the 
 *
 * @author Judi Sanchez 
 * Data Started: April 22 2018 
 */
public class SaveSettingsButton extends Button{

    private static final String SAVE_SETTINGS = "Save Game Settings";
    private static final String GAME_NAME = "Game name: ";
    private static final int LARGE_FONT = 20;
    private Color textColor = Color.BLACK;
    private String gameName;

    /**
     * Creates a new SaveGameButton
     */
    public SaveSettingsButton(CreatorView window, Pane pane, TextField fileName) {
        super(SAVE_SETTINGS);
        this.setOnAction(e -> saveSettings(window, pane, fileName));
    }

    private void saveSettings(CreatorView myWindow, Pane pane, TextField fileName) {
    		pane.getChildren().removeAll(fileName);
        gameName = fileName.getText();
        Text name = new Text(gameName + GAME_NAME );
        name.setFont(new Font(LARGE_FONT));
        name.setFill(textColor);
        pane.getChildren().add(name);
    		myWindow.getGame().rename(gameName);
    		System.out.println(myWindow.getGame().getName());
    	
    }

}
