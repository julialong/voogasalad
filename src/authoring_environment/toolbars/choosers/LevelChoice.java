package authoring_environment.toolbars.choosers;

import authoring_environment.game_elements.AuthoredLevel;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


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
    public LevelChoice(AuthoredLevel level) {
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
