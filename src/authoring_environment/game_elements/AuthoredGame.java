package authoring_environment.game_elements;

import authoring_environment.grid.ScrollingGrid;
import data.gamefiles.GAEtoJSON;
import data.gamefiles.GameFileWriter;
import data.resources.DataFileException;
import engine.level.BasicLevel;
import engine.level.Level;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The Authored Game class holds the current state of the game, including the current levels
 * and their objects.
 *
 * @author Julia Long
 * Date started: April 07 18
 */
public class AuthoredGame {

    private String myName;
    private String myDescription;
    private ObservableList<AuthoredLevel> myLevels;
    private AuthoredLevel currentLevel;
    private GAEtoJSON myGameWriter;
    private boolean isReady;

    private static final String DEFAULT_NAME = "Untitled_Game";
    private static final String DEFAULT_DESCRIPTION = "A basic game.";

    /**
     * Creates a new authored game
     * @param gameName is the name of the game
     */
    public AuthoredGame(String gameName) {
        try {
            myName = gameName;
            myDescription = DEFAULT_DESCRIPTION;
            myLevels = FXCollections.observableArrayList();
            Level tempLevel = new BasicLevel(0);
            currentLevel = new AuthoredLevel(tempLevel, new ScrollingGrid());
            myGameWriter = new GameFileWriter("User2", myName);
            isReady = false;
        }
        catch (DataFileException e) {
            saveAlert(e);
        }
    }

    /**
     * Creates a new authored game with a default name.
     */
    public AuthoredGame() {
        this(DEFAULT_NAME);
    }

    /**
     * Renames the game to a new name.
     * @param name is the new name for the game
     */
    public void rename(String name) {
        try {
            myName = name;
            myGameWriter.renameGame(name);
        }
        catch (DataFileException e)    {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(e.getCause().toString());
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    /**
     * Gets the name of the game
     * @return the assigned game name
     */
    public String getName() {
        return myName;
    }

    /**
     * Sets the description of the game
     * @param description is the desired description
     */
    public void setDescription(String description) {
        myDescription = description;
    }

    /**
     * Gets the description of the game
     * @return the assigned description
     */
    public String getDescription() {
        return myDescription;
    }

    /**
     * Designates the game as playable
     * @param playable is true if the game is ready to play
     */
    public void setPlayable(boolean playable) {
        isReady = playable;
    }

    /**
     * Checks if the game is ready to play
     * @return true if the game is ready to play, false otherwise
     */
    public boolean isPlayable() {
        return isReady;
    }

    /**
     * Adds a level to the game
     * @param level is the new level
     */
    public void addLevel(AuthoredLevel level) {
        myLevels.add(level);
    }

    /**
     * Removes a level from the game
     * @param level is the level to remove
     */
    public void removeLevel(AuthoredLevel level) {
        myLevels.remove(level);
    }

    /**
     * Gets the levels of the game
     * @return the list of level objects
     */
    public List<AuthoredLevel> getLevels() {
        return myLevels;
    }

    /**
     * Gets the observable list of levels in the game
     * @return the observable list of level objects
     */
    public ObservableList<AuthoredLevel> getObservableLevels() {
        return myLevels;
    }

    /**
     * Sets the current level
     * @param currentLevel is the level to set as the current level
     */
    public void setCurrentLevel(AuthoredLevel currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * Gets the current level
     * @return the current level
     */
    public AuthoredLevel getCurrentLevel() {
        return currentLevel;
    }

    public void saveLevel(AuthoredLevel level) {
        try {
            myGameWriter.saveIndivLevel(level);
        }
        catch (Exception e) {
            saveAlert(e);
        }
    }

    /**
     * Updates the state of the game
     */
    public void update() {
        try {
            myGameWriter.update(myLevels);
            myGameWriter.updateMeta(isReady, myDescription);
            System.out.println("level saved");
        }
        catch (DataFileException e)    {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(e.getCause().toString());
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    private void saveAlert(Exception e) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(e.getCause().toString());
        alert.setContentText(e.getMessage());
        alert.show();
    }
}
