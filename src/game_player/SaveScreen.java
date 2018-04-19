package game_player;

import java.util.List;
import data.gamefiles.GameFileWriter;
import engine.level.Level;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SaveScreen {
	private final int MIN_WINDOW_WIDTH = 600;
	private List<Level> myLevels;
	private String myName;
	private Stage myStage;

	/**
	 * Launches the save game screen.
	 * 
	 * @param gameMaterial
	 * @param name
	 */
	public SaveScreen(List<Level> gameMaterial, String name) {
		myLevels = gameMaterial;
		myName = name;
		setUpStage();
	}

	/**
	 * sets up the save game screen.
	 */
	private void setUpStage() {
		myStage = new Stage();
		myStage.setTitle("Save My Progress");
		myStage.setMinWidth(MIN_WINDOW_WIDTH);
		Scene scene = new Scene(this.displayFields(), MIN_WINDOW_WIDTH, MIN_WINDOW_WIDTH);
		scene.getStylesheets().add("../data/styling/styleSheet.css");
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
		TextField textField = new TextField();
		Button save = new Button("Save");
		save.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
             public void handle(ActionEvent e) {
             	if(textField.getText() != null && textField.getText() != "") {
             		String currName = textField.getText();
        			GameFileWriter gfw = new GameFileWriter("Playing", myName + "_" + currName);
        			gfw.saveData(currName, myLevels);
        			gfw.updateMeta(true, "game being played by " + currName, 0);	// TODO: change 0 to level player wants so end at
        			myStage.close();
             	}
             }
		});
		hbox.getChildren().addAll(textField, save);
		return hbox;
	}
}
