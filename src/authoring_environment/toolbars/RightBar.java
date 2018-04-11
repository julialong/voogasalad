package authoring_environment.toolbars;

import authoring_environment.AuthoredGame;
import authoring_environment.ScrollingGrid;
import authoring_environment.editor_windows.ElementPicker;
import authoring_environment.toolbars.buttons.creator_view_buttons.AddElementButton;
import authoring_environment.toolbars.buttons.creator_view_buttons.AddLevelButton;
import authoring_environment.toolbars.buttons.creator_view_buttons.DeleteGridCellButton;
import authoring_environment.toolbars.choosers.LevelChooser;
import authoring_environment.toolbars.labels.SideLabel;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * The right pane manages adding various elements and dealing with the structure of levels
 * within the game.
 *
 * @author Julia Long
 * Date started: April 01 18
 */
public class RightBar extends SplitPane{

    private static String CSS = "GAE.css";

    private static final int PANE_WIDTH = 300;

    private Pane elementPane;
    private Pane levelPane;
    private DeleteGridCellButton myDeleteButton;
    private AuthoredGame myGame;
    private ScrollingGrid myGrid;
    private ScrollPane myScrollPane;
    private LevelChooser myLevelChooser;

    /**
     * Creates a new right toolbar with appropriate buttons and panels.
     */
    public RightBar(AuthoredGame game, ScrollingGrid grid, ScrollPane scroller) {
        super();
        this.getStylesheets().add(CSS);
        this.getStyleClass().add("rightbar");
        myGame = game;
        myGrid = myGame.getCurrentLevel().getGrid();
        myScrollPane = scroller;
        myLevelChooser = new LevelChooser(myGame, myScrollPane, this);
        splitPanes();
        addLabels();
        addButtons();
        addScrollScreens();
    }

    public void update() {
        myLevelChooser.update();
        myGrid = myGame.getCurrentLevel().getGrid();
        myDeleteButton.changeGrid(myGrid);
    }

    private void splitPanes() {
        elementPane = new VBox();
        elementPane.getStyleClass().add("side-pane");
        this.getItems().add(elementPane);
        levelPane = new VBox();
        levelPane.getStyleClass().add("side-pane");
        this.getItems().add(levelPane);
    }

    private void addLabels() {
        elementPane.getChildren().add(new SideLabel("Add Elements"));
        levelPane.getChildren().add(new SideLabel("Manage Levels"));
    }

    private void addButtons() {
        Button elementButton = new AddElementButton();
        myDeleteButton = new DeleteGridCellButton(myGrid);
        elementPane.getChildren().add(elementButton);
        elementPane.getChildren().add(myDeleteButton);
        Button levelButton = new AddLevelButton(myGame);
        levelPane.getChildren().add(levelButton);
    }

    private void addScrollScreens() {
        Button updateButton = new Button("Update levels");
        LevelChooser levelChooser = new LevelChooser(myGame, myScrollPane, this);
        updateButton.setOnAction(e -> levelChooser.update());
        ScrollPane levelChooserPane = new ScrollPane();
        levelChooserPane.setContent(levelChooser);
        levelPane.getChildren().add(updateButton);
        levelPane.getChildren().add(levelChooserPane);
    	ElementPicker elementPicker = new ElementPicker();
    	ScrollPane pickerPane = elementPicker.getElementPane();
        elementPane.getChildren().add(pickerPane);
        levelPane.getChildren().add(myLevelChooser);
    }
    
}
