package authoring_environment.toolbars.choosers;

import engine.level.Level;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


/**
 * The Level Choice objects hold the relevant information about a class that allows users to choose different
 *
 * @author Julia Long
 * Date started: April 04 18
 */
public class LevelChoice extends HBox{

    private static String CSS = "GAE.css";


    /**
     * Creates a new LevelChoice object
     * @param level
     */
    public LevelChoice(Level level) {
        super();
        setStyle();
        // TODO: add level icon to object
        this.getChildren().add(new Text(level.getName()));
    }

    private void setStyle() {
        this.getStylesheets().add(CSS);
        this.getStyleClass().add("level-choice");
    }

}
