package authoring_environment.toolbars.choosers;

import engine.level.Level;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


/**
 * The Level Choice objects hold the relevant information about a class that allows users to choose different
 *
 * @author Julia Long
 * Date started: April 04 18
 */
public class LevelChoice extends HBox{

    /**
     * Creates a new LevelChoice object
     * TODO: change Object to Level
     * @param level
     */
    public LevelChoice(Level level) {
        super();
        this.setPrefHeight(30);
        this.setPrefWidth(100);
        // TODO: add level icon to object
        GridPane gridCopy = level.getGrid();
        gridCopy.setMaxHeight(30);
        gridCopy.setMaxWidth(30);
        this.getChildren().add(gridCopy);
        // TODO: add level name to object
        this.getChildren().add(new Text(level.getName()));
    }

}
