package authoring_environment.toolbars.choosers;

import authoring_environment.AuthoredGame;
import engine.level.Level;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;


/**
 * The level chooser allows users to select an available level to edit.
 *
 * @author Julia Long
 * Date started: April 04 18
 */
public class LevelChooser extends ScrollPane {

    private AuthoredGame myGame;

    /**
     * Creates a scrollpane that allows users to choose a level to edit.
     * @param game is the current game
     */
    public LevelChooser(AuthoredGame game) {
        super();
        myGame = game;
        update();
    }

    public int update() {
        int i = 0;
        for (Level level : myGame.getLevels()) {
            Pane thisLevelChoice = new LevelChoice(level);
            thisLevelChoice.setOnMouseClicked(e -> myGame.setCurrentLevel(level));
            this.getChildren().add(thisLevelChoice);
            i++;
        }
        return i;
    }

}
