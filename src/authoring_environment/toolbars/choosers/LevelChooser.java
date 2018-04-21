package authoring_environment.toolbars.choosers;

import authoring_environment.editor_windows.CreatorView;
import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import engine.level.BasicLevel;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;

/**
 * The level chooser allows users to select an available level to edit.
 *
 * @author Julia Long
 * Date started: April 04 18
 * Resources used: https://docs.oracle.com/javafx/2/ui_controls/list-view.htm
 */
public class LevelChooser extends ListView<AuthoredLevel> {

    private CreatorView myWindow;
    private ScrollPane myScrollPane;

    /**
     * Creates a scrollpane that allows users to choose a level to edit.
     * @param window is the current window
     */
    public LevelChooser(CreatorView window, ScrollPane grid) {
        super();
        myWindow = window;
        myScrollPane = grid;
        this.setItems(window.getGame().getObservableLevels());
        changeFormat();
    }

    private void changeFormat() {
        this.setCellFactory(param -> new LevelChoice(myWindow, myScrollPane));
    }
}
