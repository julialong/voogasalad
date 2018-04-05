package game_player;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Class that acts as the main controller for the game player application
 * 
 * @author Kelley Scroggs, Dorian Barber
 *
 */
public class VController {
	private VView view;

	//private BACKEND_CLASS myModel;

	/**
	 * initializes the controller
	 */
	public VController() {
		//myModel = new BACKENDCLASS();
	}

	/**
	 * Constructor which takes in a scene for the controller to
	 * take in user inputs
	 */
	public VController(VView curView){
		view = curView;
		addKeyInputs();
		addMouseInputs();
	}

	/**
	 * Connects all of the user key bindings to corresponding actions
	 * for the controller to execute
	 */
	private void addKeyInputs() {
		view.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(key.getCode()==KeyCode.A) {
				passKeyInput(key.getCode());
				view.updateView();
			}
		});
	}

	/**
	 * Connects user click inputs with specific actions to be
	 * performed
	 */
	private void addMouseInputs() {
		view.addEventFilter(MouseEvent.MOUSE_CLICKED, (click) -> {
			if (click.getButton() == MouseButton.PRIMARY) {
				passMouseInput(click.getButton());
				view.updateView();
			}
		});
	}

	/**
	 * Dummy method for key inputs
	 */
	private void passKeyInput(KeyCode code) {
		// TODO Auto-generated method stub
		System.out.println("TODO: pass " + code + " to backend");
		//myModel.unknow_method();
		
	}

	/**
	 * Dummy method for mouse inputs
	 */
	private void passMouseInput(MouseButton button) {
		// TODO Auto-generated method stub
		System.out.println("TODO: pass " + button + " to backend");
		//myModel.unknow_method();
	}

}
