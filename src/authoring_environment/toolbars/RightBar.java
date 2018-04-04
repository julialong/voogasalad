package authoring_environment.toolbars;

import authoring_environment.toolbars.buttons.AddElementButton;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * The right pane manages adding various elements and dealing with the structure of levels
 * within the game.
 *
 * @author julialong
 * Date started: April 01 18
 */
public class RightBar extends VBox{

    private static final int PANE_WIDTH = 300;

    /**
     * Creates a new right toolbar with appropriate buttons and panels.
     */
    public RightBar() {
        super();
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setMinWidth(PANE_WIDTH);
        addButtons();
    }

    private void addButtons() {
        this.getChildren().add(new AddElementButton());
    }

    
}
