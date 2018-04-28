package authoring_environment.editor_windows.game_windows;


import authoring_environment.editor_windows.CreatorView;
import authoring_environment.editor_windows.MetaManager;
import authoring_environment.editor_windows.buttons.SaveSettingsButton;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

/**
 * The settings manager allows the user to change the settings for the current game they
 * are editing.
 *
 * @author Julia Long
 * @author Judi Sanchez 
 * Date started: April 04 18
 */
// TODO: Fix Styling 
public class SettingsManager implements MetaManager {

    private Stage myStage;
    private Pane myRoot;
    private Scene myScene;

    private CreatorView myWindow;
    private TextField gameNameField;
    private TextField descriptionField;

    private static final String CSS = "GAE.css";
    private static final String SETTINGS_MANAGER = "Settings Manager";
    private static final String GAME_NAME_PROMPT =  "Game Name: ";
    private static final String DESCRIPTION_PROMPT =  "Description: ";
    private static final Color TEXT_COLOR = Color.WHITE;
    

    /**
     * Creates a new settings manager window.
     */
    public SettingsManager(CreatorView window) {
        myStage = new Stage();
        myRoot = new VBox();
        myScene = new Scene(myRoot);
        myScene.getStylesheets().add(CSS);
        myRoot.getStyleClass().add("settings-saver");
        myWindow = window;
        myStage.setScene(myScene);
        myStage.setTitle(SETTINGS_MANAGER);
        myStage.show();
        myStage.centerOnScreen();
        addNameBox();
        addDescriptionBox();
        SaveSettingsButton saveButton = new SaveSettingsButton(myWindow, myStage, gameNameField, descriptionField);
        myRoot.getChildren().add(saveButton);
    }
    
    private void addNameBox() {
    	 	Text prompt = new Text(GAME_NAME_PROMPT);
         prompt.setFill(TEXT_COLOR);
         myRoot.getChildren().add(prompt);
         gameNameField = new TextField(myWindow.getGame().getName());
         myRoot.getChildren().add(gameNameField);
    }
    
    private void addDescriptionBox() {
   	 	Text descriptionPrompt = new Text(DESCRIPTION_PROMPT);
        descriptionPrompt.setFill(TEXT_COLOR);
        myRoot.getChildren().add(descriptionPrompt);
        descriptionField = new TextField(myWindow.getGame().getDescription());
        descriptionField.getStyleClass().add("description-field");
        myRoot.getChildren().add(descriptionField);
   }

  

}
