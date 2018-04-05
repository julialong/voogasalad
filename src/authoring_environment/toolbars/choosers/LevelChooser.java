package authoring_environment.toolbars.choosers;

import javafx.scene.control.ScrollPane;

import java.util.List;

/**
 * The level chooser allows users to select an available level to edit.
 *
 * @author Julia Long
 * Date started: April 04 18
 */
public class LevelChooser extends ScrollPane {

    private List<Object> myLevels;

    /**
     * Creates a scrollpane that allows users to choose a level to edit.
     * TODO: Change Object to Level
     * @param levels is the list of levels to display
     */
    public LevelChooser(List<Object> levels) {
        super();
        myLevels = levels;
        for (Object level : myLevels) {
            // TODO: display all levels
            this.getChildren().add(new LevelChoice(level));
        }
    }
}
