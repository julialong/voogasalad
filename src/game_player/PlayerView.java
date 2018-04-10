package game_player;


import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Map;

public class PlayerView extends BorderPane{
	private Map<String, List<Object>> gameMaterial;
	private VMenuBar myMenuBar;
	private VoogaGameView myGameView;
	
	public PlayerView() {
		super();
		createMenuBar();
		createGView();
		setViewTop();
		setMiddle();
	}

	public PlayerView(Map<String, List<Object>> game){
		this();
		gameMaterial = game;
	}

	public PlayerView(String name){
		this();
		System.out.println(name);
	}
	
	private void createGView() {
		// TODO Auto-generated method stub
		myGameView = new VoogaGameView();
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
		// TODO Auto-generated method stub
		myMenuBar.addButton(new VButton("High Scores"));
		myMenuBar.addButton(new VButton("Replay"));
		myMenuBar.addButton(new VButton("Switch Game"));
		myMenuBar.addButton(new VButton("Save Game"));
		myMenuBar.addButton(new VButton("Set Preferences"));
	}

	/**
	 * Adds the menubar to the top of the game player UI.
	 */
	private void setViewTop() {
		//this.setTop(new Rectangle(100, 100, Color.BLUE));
		//TODO: menubar class
		this.setTop(myMenuBar.getNode());
	}
	
	/**
	 * Adds the game image to the middle of the game player UI.
	 */
	private void setMiddle() {
		this.setCenter(myGameView.getNode());
		//TODO: gameView class
	}
	
	/**
	 * update the game view
	 * 
	 */
	public void updateView() {
		myGameView.updateGame();
	}
}
