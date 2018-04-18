package authoring_environment.game_elements;

import authoring_environment.grid.ScrollingGrid;
import data.gamefiles.GAEtoJSON;
import data.gamefiles.GameFileWriter;
import engine.entity.GameEntity;
import engine.entity.GameObject;
import engine.level.BasicLevel;
import engine.level.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<AuthoredLevel> myLevels;
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
        myName = gameName;
        myDescription = DEFAULT_DESCRIPTION;
        myLevels = new ArrayList<>();
        Level tempLevel = new BasicLevel(0);
        currentLevel = new AuthoredLevel(tempLevel, new ScrollingGrid());
        myGameWriter = new GameFileWriter(myName);
        isReady = false;
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
        myName = name;
        myGameWriter.renameGame(name);
    }

    public String getName() {
        return myName;
    }

    public void setDescription(String description) {
        myDescription = description;
    }

    public String getDescription() {
        return myDescription;
    }

    public void setPlayable(boolean playable) {
        isReady = playable;
    }

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

    /**
     * Updates the state of the game
     */
    public void update() {
        // myGameWriter.update(myLevels);
        myGameWriter.updateMeta(isReady, myDescription);
        System.out.println("level saved");
    }
}