package authoring_environment.toolbars;

import authoring_environment.toolbars.buttons.AddElementButton;
import authoring_environment.toolbars.buttons.AddLevelButton;
import authoring_environment.toolbars.labels.SideLabel;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * The right pane manages adding various elements and dealing with the structure of levels
 * within the game.
 *
 * @author Julia Long
 * Date started: April 01 18
 */
public class RightBar extends SplitPane{

    private static final int PANE_WIDTH = 300;

    private Pane elementPane;
    private Pane levelPane;

    /**
     * Creates a new right toolbar with appropriate buttons and panels.
     */
    public RightBar() {
        super();
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPrefWidth(PANE_WIDTH);
        this.setOrientation(Orientation.VERTICAL);
        splitPanes();
        addLabels();
        addButtons();
        addScrollScreens();
    }

    private void splitPanes() {
        elementPane = new Pane();
        this.getItems().add(elementPane);
        levelPane = new Pane();
        this.getItems().add(levelPane);
    }

    private void addLabels() {
        elementPane.getChildren().add(new SideLabel("Add Elements"));
        levelPane.getChildren().add(new SideLabel("Manage Levels"));
    }

    private void addButtons() {
        Button elementButton = new AddElementButton();
        elementButton.setLayoutY(50);
        elementPane.getChildren().add(elementButton);
        Button levelButton = new AddLevelButton();
        levelButton.setLayoutY(50);
        levelPane.getChildren().add(levelButton);
    }

    private void addScrollScreens() {
        // TODO: @MICHAEL add your element chooser here!!!
        // TODO: add Level chooser here
    }

    
}
