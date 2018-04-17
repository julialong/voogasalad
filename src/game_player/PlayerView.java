package game_player;


import engine.level.Level;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import java.util.List;

import data.gamefiles.GameFileReader;
import data.gamefiles.JSONtoObject;

/**
 * The main application for the game player. Here is where the MVC design pattern is used.
 * The Player contains a menubar and a game view for buttons and to display the actual game.
 * This class simply acts as the container for those parts.
 *
 * @Author Dorian Barber, Kelley Scroggs
 */
public class PlayerView extends VBox{
	private List<Level> gameMaterial;
	private VMenuBar myMenuBar;
	private VoogaGameView myGameView;
	private JSONtoObject reader = new GameFileReader();
	private String myName;
	public PlayerView() {
		super();
		createGView();
		createMenuBar();
		setViewTop();
		setMiddle();
	}

	public PlayerView(List<Level> game, String name){
		super();
		gameMaterial = game;
		myName = name;
		createGView();
		createMenuBar();
		setViewTop();
		setMiddle();
	}

	//	public PlayerView(String name){
	//		this();
	//		System.out.println(name);
	//	}
	
	/**
	 * Resets the game
	 */
	private void resetGame() {
		this.getChildren().remove(myGameView.getNode());
		resetGView();
		setMiddle();
	}
	
	
	/**
	 * Reloads the game materials;
	 */
	private void resetGView() {
		myGameView = new VoogaGameView(reader.loadCompleteGame(myName));
	}
	
	
	/**
	 * Creates the view where the game is displayed.
	 */
	private void createGView() {
		myGameView = new VoogaGameView(gameMaterial);
	}

	/**
	 * create the menubar
	 */
	private void createMenuBar() {
		// TODO Auto-generated method stub
		myMenuBar = new VMenuBar();
		addButtons();
	}

	/**
	 * add buttons to my menubar
	 */
	private void addButtons() {
		myMenuBar.addButton(new VButton("High Scores"));
		myMenuBar.addButton(new VButton("Replay"));
		myMenuBar.addButton(new VButton("Switch Game"));
		myMenuBar.addButton(new VButton("Save Game"));
		myMenuBar.addButton(new VButton("Set Preferences"));
		
		VButton resumeButton = new VButton("Resume Game");
		resumeButton.setOnMouseClicked(e -> myGameView.resumeGame());
		myMenuBar.addButton(resumeButton);
		
		VButton pauseButton = new VButton("Pause Game");
		pauseButton.setOnMouseClicked(e -> myGameView.pauseGame());
		myMenuBar.addButton(pauseButton);
		
		//TODO: new interface here
		VButton keysButton = new VButton("Change Bindings");
		keysButton.setOnMouseClicked(e -> new KeyBindingWindow(myGameView));
		myMenuBar.addButton(keysButton);
		
		VButton resetButton = new VButton("Reset");
		resetButton.setOnMouseClicked(e -> resetGame());
		myMenuBar.addButton(resetButton);
	}

	/**
	 * Adds the menubar to the top of the game player UI.
	 */
	private void setViewTop() {
		//this.setTop(new Rectangle(100, 100, Color.BLUE));
		//TODO: menubar class
		this.getChildren().add(myMenuBar.getNode());
	}
	
	/**
	 * Adds the game image to the middle of the game player UI.
	 */
	private void setMiddle() {
		this.getChildren().add(myGameView.getNode());
		myGameView.startGame();
		//TODO: gameView class
	}
		
	public void startKey(KeyCode keyCode) {
		myGameView.keyPressed(keyCode);
	}
	
	public void endKey(KeyCode keyCode) {
		myGameView.keyUnPressed(keyCode);
	}
	
}
