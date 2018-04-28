package game_player;

import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import data.gamefiles.GameFileWriter;
import data.resources.DataFileException;
import engine.level.Level;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * The interface used for saving the current status of the game
 */
public class SaveScreen {
	private static final int MIN_WINDOW_WIDTH = 600;
	private List<Level> myLevels;
	private String myName;
	private Stage myStage;
	private ResourceBundle rb;
	private int window_width;

	/**
	 * Launches the save game screen.
	 * 
	 * @param gameMaterial
	 * @param name
	 */
	public SaveScreen(List<Level> gameMaterial, String name) {
		rb = ResourceBundle.getBundle("game_player.resources.saves_screen");
		window_width = Integer.parseInt(getResourceValue("window_width"));
		myLevels = gameMaterial;
		myName = name;
		setUpStage();
	}

	/**
	 * sets up the save game screen.
	 */
	private void setUpStage() {
		myStage = new Stage();
		myStage.setTitle(getResourceValue("title"));
		myStage.setMinWidth(window_width);
		Scene scene = new Scene(this.displayFields(), window_width, window_width);
		scene.getStylesheets().add(getResourceValue("style_sheet"));
		myStage.setScene(scene);
		myStage.show();
	}

	/**
	 * Adds elements to the save game screen.
	 * 
	 * @return
	 */
	private HBox displayFields() {
		HBox hbox = new HBox();
		hbox.getStyleClass().add(getResourceValue("hbox_style"));
		TextField textField = new TextField();
		Button save = new Button("Save");
		save.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
             public void handle(ActionEvent e) {
				try	{
					if(textField.getText() != null && textField.getText() != "") {
						String currName = textField.getText();
						GameFileWriter gfw = new GameFileWriter(getResourceValue("username"), myName + getResourceValue("game_connector") + currName);
						gfw.saveData(currName, myLevels);
						gfw.updateMeta(true, getResourceValue("metadata") + currName, 0);	// TODO: change 0 to level player wants so end at
						myStage.close();
					}
				}
				catch (DataFileException err)	{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle(err.getCause().toString());
					alert.setContentText(err.getMessage());
					alert.show();
				}
             }
		});
		hbox.getChildren().addAll(textField, save);
		return hbox;
	}


	/**
	 * Returns the information withheld in the overview.properties file
	 * The parameter is a string which represents the key for the properties file.
	 */
	private String getResourceValue(String key){
		try{
			return rb.getString(key);
		}
		catch (NullPointerException|MissingResourceException |ClassCastException e){
			return "";
		}
	}
}
