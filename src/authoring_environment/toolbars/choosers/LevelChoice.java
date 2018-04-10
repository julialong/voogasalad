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

    /**
     * Creates a new LevelChoice object
     * @param level
     */
    public LevelChoice(Level level) {
        super();
        this.setPrefHeight(30);
        this.setPrefWidth(200);
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        // TODO: add level icon to object
        Text levelName = new Text(level.getName());
        levelName.setTextAlignment(TextAlignment.CENTER);
        levelName.setFont(new Font(20));
        this.getChildren().add(levelName);
    }

}
