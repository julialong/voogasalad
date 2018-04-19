package game_player;

import java.util.List;
import data.gamefiles.GameFileWriter;
import engine.level.Level;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SaveScreen {
	private final int MIN_WINDOW_WIDTH = 600;
	private List<Level> myLevels;
	private String myName;
	private TextField myTextField;
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
		myTextField = new TextField();
		Button save = new Button("Save");
		setAction(save);
		hbox.getChildren().addAll(myTextField, save);
		return hbox;
	}

	/**
	 * Adds functionality to the save button. When clicked it should call the data
	 * team's method that saves the state of the current game under the name of the
	 * current player.
	 * 
	 * @param save
	 */
	private void setAction(Button save) {
		Button saveButton = save;
		GameFileWriter gfw = new GameFileWriter(myName);
		if (myTextField.getText() != null && myTextField.getText() != "") {
			String currName = myTextField.getText();
			// TODO: confirm with data team that this is the correct API call
			saveButton.setOnMouseClicked(e -> gfw.saveData(currName, myLevels));
			// TODO: Do we want the stage to close on save?
			System.out.println("Closing window...");
			myStage.close();
			System.out.println("...Window closed");
		}
	}

}
