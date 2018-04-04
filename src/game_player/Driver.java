/**
 * Main class called to run the game player application.
 */
package game_player;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Driver extends Application {
	
	private VController myController;
	private VView myView;
	
	
	private static final String TITLE = "GAME PLAYER";
	
	/**
	 * Creates the UI for the application
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		setScreenBounds(primaryStage);
		myController = new VController();
		myView = new VView();
		Scene scene = new Scene(myView);
		addUserInputFinders(scene);
		//TODO: uncomment once we create the style sheet
		//scene.getStylesheets().add("styleSheet.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle(TITLE);
		primaryStage.show();
		
	}
	
	private void addUserInputFinders(Scene scene) {
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(key.getCode()==KeyCode.A) {
				//pass controller the input
				myController.passKeyInput(key.getCode());
				//get result from controller
				myView.updateView();
				//update the view with output from the controller
			}
		});
		
		scene.addEventFilter(MouseEvent.MOUSE_CLICKED, (click) -> {
			if (click.getButton() == MouseButton.PRIMARY) {
				myController.passMouseInput(click.getButton());
				myView.updateView();
			}
		});
	}

	/**
	 * Makes the application take up the entire window of the computer.
	 * @param primaryStage
	 */
	private void setScreenBounds(Stage primaryStage) {
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX(primaryScreenBounds.getMinX());
		primaryStage.setY(primaryScreenBounds.getMinY());
		primaryStage.setWidth(primaryScreenBounds.getWidth());
		primaryStage.setHeight(primaryScreenBounds.getHeight());
	}

	/**
	 * Runs the Game Player application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
