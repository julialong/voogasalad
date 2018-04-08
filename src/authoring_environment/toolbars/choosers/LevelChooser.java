package authoring_environment.toolbars.choosers;

import engine.level.Level;
import javafx.scene.control.ScrollPane;

import java.util.List;

/**
 * The level chooser allows users to select an available level to edit.
 *
 * @author Julia Long
 * Date started: April 04 18
 */
public class LevelChooser extends ScrollPane {

    private List<Level> myLevels;

    /**
     * Creates a scrollpane that allows users to choose a level to edit.
     * @param levels is the list of levels to display
     */
    public LevelChooser(List<Level> levels) {
        super();
        myLevels = levels;
        for (Level level : myLevels) {
            // TODO: display all levels
            this.getChildren().add(new LevelChoice(level));
        }
    }
}
