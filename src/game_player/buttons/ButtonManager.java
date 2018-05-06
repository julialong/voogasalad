package game_player.buttons;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import authoring_environment.editor_windows.EditorWindow;
import engine.level.Level;
import game_player.OverViewDriver;
import game_player.PlayerView;
import game_player.SaveScreen;
import game_player.ScoreKeeperManager;
import game_player.KeyBindingWindow;
import game_player.ReplayScreen;
import game_player_api.GamePlayerButton;
import game_player_api.GameViewMenu;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * This class represents good design for a series of reasons. First of all
 * this class eliminate hard coding completely. The only thing hard coded is
 * the actual functionality of the buttons. The String which gives the name
 * of the button and the String which corresponds to the method name are
 * all kept within a resource file. Additionally the order of the resource
 * file determines the order in which the buttons appear. This format proves
 * to be easily extendable since the only two changes that need to be made
 * are adding a method which will preform the action and then adding a line
 * in the resource file which has the desired name of the button corresponding
 * to the exact method name. If the user makes an error in typing then the button
 * does nothing, making this code easy to misuse.
 */
public class ButtonManager {
    private PlayerView myPlayer;
    private VMenuBar myMenuBa;
    private GameViewMenu myGameView;
    private String myName;
    private String myDescription;
    private List<Level> myGameLevels;
    private ResourceBundle rb = ResourceBundle.getBundle("game_player.buttons.buttons");

    public ButtonManager(PlayerView playerView, VMenuBar menuBar, GameViewMenu gameView, String name,
                         String description, List<Level> gameMaterial) {
        myPlayer = playerView;
        myMenuBa = menuBar;
        myGameView = gameView;
        myName = name;
        myDescription = description;
        myGameLevels = gameMaterial;
        createButtons();
    }

    /**
     * Creates all the buttons from the resource file by iterating through
     * each key, which gives the name of the button, and the corresponding
     * method name, which gives the button functionality. If either of those
     * two exception are caught the button simply does nothing.
     */
    private void createButtons() {
        List<String> buttonNames = (List<String>) rb.getKeys();
        for(String buttonName : buttonNames){
            Button button = new VButton(buttonName);
            Method buttonFunction = getButtonMethod(getResourceValue(buttonName));
            button.setOnAction(event -> {
                try {
                    myGameView.pauseGame();
                    buttonFunction.invoke(this);
                } catch (IllegalAccessException|InvocationTargetException e) {
                    //The button does nothing
                }
            });
            myMenuBa.addButton((GamePlayerButton)button);
        }
    }


    /**
     * This will return the method that corresponds to the @param methodName
     * In the event that the method does not exist a null method which does
     * nothing is returned. This method does certainly exist so the second
     * catch has nothing within it.
     */
    private Method getButtonMethod(String methodName){
        try {
            return this.getClass().getMethod(methodName);
        } catch (NoSuchMethodException e) {
            Method nullMethod = null;
            try {
                nullMethod = this.getClass().getMethod("nullMethod");
            } catch (NoSuchMethodException e1) { }
            return nullMethod;
        }
    }


    /**
     * Launches a new homescreen
     */
    private void goHome() {
        OverViewDriver relaunch = new OverViewDriver();
        try {
            relaunch.start(new Stage());
        } catch (Exception e) {
            System.out.println("Failed to relaunch");
        }
    }


    /**
     * Sends the user to the save screen where they can
     * save the current state of the game
     */
    private void saveGame(){
        new SaveScreen(myGameLevels, myName);
    }


    /**
     * Sends the user to an interface where they
     * can keep track of their score
     */
    private void setScore(){
        new ScoreKeeperManager(myGameView);
    }


    /**
     * Allows the user to resume the game if it is
     * paused
     */
    private void resumeGame(){
        myGameView.resumeGame();
    }


    /**
     * Pauses the game
     */
    private void pauseGame(){
        myGameView.pauseGame();
    }


    /**
     * Opens an interface where the user can
     * change the keybindings that represent
     * the control for the game
     */
    private void changeBindings(){
        new KeyBindingWindow(myGameView);
    }


    /**
     * Opens the game authoring environment interface
     * to allow the user to edit the game they are playing
     */
    private void editGame(){
        Stage stage = new Stage();
        new EditorWindow(stage, myName, myDescription);
    }


    /**
     * Resets the game to the original starting location
     * of the first level
     */
    private void resetGame(){
        myPlayer.resetGame();
    }


    /**
     * Opens an interface which show the replay of the user
     * playing the level
     */
    private void replayGame(){
        new ReplayScreen(myGameView.getReplayList());
    }


    /**
     * This null method will do nothing and represents what happens
     * when the method name within the resource file does not match
     * the method name in this program
     */
    private void nullMethod(){}


    /**
     * Returns the information withheld in the buttons.properties file
     * The parameter is a string which represents the key for the properties file.
     * If either the key is invalid or the value in the properties file is formatted
     * incorrectly a string "nullMethod" is returned. This string corresponds to
     * the nullMethod within this program.
     */
    private String getResourceValue(String key){
        try{
            return rb.getString(key);
        }
        catch (NullPointerException|MissingResourceException |ClassCastException e){
            return "nullMethod";
        }
    }
}
