package authoring_environment;

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
    private List<Level> myLevels;
    private Level currentLevel;
    private GameFileWriter myGameWriter;

    private static final String DEFAULT_NAME = "Untitled_Game";

    /**
     * Creates a new authored game
     * @param gameName is the name of the game
     */
    public AuthoredGame(String gameName) {
        myName = gameName;
        myLevels = new ArrayList<>();
        currentLevel = new BasicLevel();
        myGameWriter = new GameFileWriter(myName);
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
        // TODO: change name in myGameWriter
    }

    /**
     * Adds a level to the game
     * @param level is the new level
     */
    public void addLevel(Level level) {
        myLevels.add(level);
    }

    /**
     * Gets the levels of the game
     * @return the list of level objects
     */
    public List<Level> getLevels() {
        return myLevels;
    }

    /**
     * Sets the current level
     * @param currentLevel is the level to set as the current level
     */
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * Gets the current level
     * @return the current level
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Updates the state of the game
     */
    public void update() {
        myGameWriter.update(extractObjects());
    }

    private Map<Level, List<GameEntity>> extractObjects() {
        Map<Level, List<GameEntity>> unpacked = new HashMap<>();
        for (Level level : myLevels) {
            unpacked.put(level, level.getObjects());
        }
        return unpacked;
    }

}
