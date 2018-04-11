package game_player;


import engine.controls.Controls;
import engine.entity.Block;
import engine.interaction.PreventClipping;
import engine.level.BasicLevel;
import engine.level.Level;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * The main application for the game player. Here is where the MVC design pattern is used.
 * The Player contains a menubar and a game view for buttons and to display the actual game.
 * This class simply acts as the container for those parts.
 *
 * @Author Dorian Barber, Kelley Scroggs
 */
public class PlayerView extends VBox {
    private List<Level> gameMaterial;
    private VMenuBar myMenuBar;
    private VoogaGameView myGameView;

    /**
     * No arg constructor primarily used for testing.
     * Utilizes the no arg constructor from VoogaGameView
     * to test without using actual level data from the program.
     */
    public PlayerView() {
        super();
        createMenuBar();
        //myGameView = new VoogaGameView();
        setViewTop();
        //setMiddle();
    }

    public PlayerView(List<Level> game) {
        super();
        gameMaterial = game;
        createMenuBar();
        createGView();
        setViewTop();
        setMiddle();
    }

    private void createGView() {
        // TODO Auto-generated method stub
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

    /**
     * update the game view
     */
    public void updateView() {
        myGameView.updateGame();
    }



}
