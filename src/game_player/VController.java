package game_player;

import javafx.scene.input.KeyEvent;

/**
 * Class that acts as the main controller for the game player application
 * 
 * @author Kelley Scroggs, Dorian Barber
 *
 */
public class VController {
	private PlayerView view;

	/**
	 * initializes the controller
	 */
	public VController() {
		//do something
	}

	/**
	 * Constructor which takes in a scene for the controller to
	 * take in user inputs
	 */
	public VController(PlayerView curView){
		view = curView;
		passKeyInput();
	}

	/**
	 * Dummy method for key inputs
	 */
	private void passKeyInput() {
		view.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {
			view.startKey(key.getCode());
		});
		view.addEventFilter(KeyEvent.KEY_RELEASED, (key) -> {
			view.endKey(key.getCode());
		});
	}
}
