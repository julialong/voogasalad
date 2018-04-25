package authoring_environment.editor_windows.buttons;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import data.fileReading.GAEGameFileReader;
import data.resources.DataFileException;

import java.util.List;

import authoring_environment.editor_windows.EditorWindow;
import authoring_environment.game_elements.AuthoredGame;
import authoring_environment.game_elements.AuthoredLevel;

/**
 * 
 * @author Judi Sanchez
 * Date started; April 24 2018
 */
public class GameChooserButton extends Button{
	
	/**
	 * This is a special button that calls the loadGame method to open a new editing window
	 * for the game to be loaded
	 */
	public GameChooserButton(String gameName){
		super(gameName);
		GAEGameFileReader fileReader= new GAEGameFileReader();
		this.setOnAction(e -> {
			try {
				loadGame(gameName, fileReader);
			} catch (DataFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
	//TODO : fix because broken as fuck
	/**
	 * Is supposed to open a new Editor and load a game by using the methods from GAEGameFileReader
	 * @param gameName: The name of the game that is to be loaded
	 * @param fileReade: 
	 * @throws DataFileException
	 */
	private void loadGame(String gameName, GAEGameFileReader fileReader) throws DataFileException {
		Stage stage = new Stage();
		EditorWindow newWindow = new EditorWindow(stage);
		AuthoredGame game = newWindow.getGame();
		List<AuthoredLevel> levels = fileReader.loadCompleteAuthoredGame(gameName);
		for (AuthoredLevel level: levels) {
			game.addLevel(level);
		}
		game.setCurrentLevel(levels.get(0));
		game.getDescription();
	}

}
