package authoring_environment.toolbars;

import authoring_environment.editor_windows.EditorWindow;
import authoring_environment.grid.ScrollingGrid;
import authoring_environment.toolbars.choosers.ElementPicker;
import authoring_environment.toolbars.choosers.ElementTypeBox;
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
 * @author Julia Long, Michael Acker
 * Date started: April 01 18
 */
public class RightBar extends VBox{

    private static String CSS = "GAE.css";

    private Pane elementPane;
    private Pane levelPane;
    private DeleteGridCellButton myDeleteButton;
    private ElementTypeBox myTypeBox;
    private EditorWindow myWindow;
    private ScrollingGrid myGrid;
    private ElementPicker myElementPicker;

    /**
     * Creates a new right toolbar with appropriate buttons and panels.
     */
    public RightBar(EditorWindow window, ScrollPane scroller) {
        super();
        this.getStylesheets().add(CSS);
        this.getStyleClass().add("rightbar");
        myWindow = window;
        myGrid = myWindow.getGame().getCurrentLevel().getScrollingGrid();
        splitPanes();
        addLabels();
        addButtons();
        addScrollScreens();
    }

    public void update() {
        myGrid = myWindow.getGame().getCurrentLevel().getScrollingGrid();
        myDeleteButton.changeGrid(myGrid);
    }

    private void splitPanes() {
        elementPane = new VBox();
        elementPane.getStyleClass().add("rightbar");
        this.getChildren().add(elementPane);
        levelPane = new VBox();
        levelPane.getStyleClass().add("rightbar");
        this.getChildren().add(levelPane);
    }

    private void addLabels() {
        elementPane.getChildren().add(new SideLabel("Add Elements"));
        levelPane.getChildren().add(new SideLabel("Manage Levels"));
    }

    private void addButtons() {
        Button elementButton = new AddElementButton();
        myDeleteButton = new DeleteGridCellButton(myGrid);
        myTypeBox = new ElementTypeBox(this);
        elementPane.getChildren().add(elementButton);
        elementPane.getChildren().add(myTypeBox);
        elementPane.getChildren().add(myDeleteButton);
        Button levelButton = new AddLevelButton(myWindow);
        levelPane.getChildren().add(levelButton);
    }

    private void addScrollScreens() {
        LevelChooser levelChooser = new LevelChooser(myWindow);
        levelPane.getChildren().add(levelChooser);
    	myElementPicker = new ElementPicker(this);
    	ScrollPane pickerPane = myElementPicker.getElementPane();
        elementPane.getChildren().add(pickerPane);
    }
    
    public ElementPicker getElementPicker() {
    	return myElementPicker;
    }
    
}
