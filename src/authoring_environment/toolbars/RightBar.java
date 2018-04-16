package authoring_environment.toolbars;

import authoring_environment.editor_windows.CreatorView;
import authoring_environment.game_elements.AuthoredGame;
import authoring_environment.grid.ScrollingGrid;
import authoring_environment.toolbars.choosers.ElementPicker;
import authoring_environment.toolbars.buttons.creator_view_buttons.AddElementButton;
import authoring_environment.toolbars.buttons.creator_view_buttons.AddLevelButton;
import authoring_environment.toolbars.buttons.creator_view_buttons.DeleteGridCellButton;
import authoring_environment.toolbars.choosers.LevelChooser;
import authoring_environment.toolbars.labels.SideLabel;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;

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
    private CreatorView myWindow;
    private ScrollingGrid myGrid;
    private ScrollPane myScrollPane;
    private LevelChooser myLevelChooser;

    /**
     * Creates a new right toolbar with appropriate buttons and panels.
     */
    public RightBar(CreatorView window, ScrollingGrid grid, ScrollPane scroller) {
        super();
        this.getStylesheets().add(CSS);
        this.getStyleClass().add("rightbar");
        myWindow = window;
        myGrid = myWindow.getGame().getCurrentLevel().getScrollingGrid();
        myScrollPane = scroller;
        myLevelChooser = new LevelChooser(myWindow, myScrollPane);
        splitPanes();
        addLabels();
        addButtons();
        addScrollScreens();
    }

    public void update() {
        myLevelChooser.update();
        myGrid = myWindow.getGame().getCurrentLevel().getScrollingGrid();
        myDeleteButton.changeGrid(myGrid);
    }

    private void splitPanes() {
        elementPane = new VBox();
        elementPane.getStyleClass().add("rightbar");
        this.getItems().add(elementPane);
        levelPane = new VBox();
        levelPane.getStyleClass().add("rightbar");
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
        Button levelButton = new AddLevelButton(myWindow);
        levelPane.getChildren().add(levelButton);
    }

    private void addScrollScreens() {
        Button updateButton = new Button("Update levels");
        LevelChooser levelChooser = new LevelChooser(myWindow, myScrollPane);
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
