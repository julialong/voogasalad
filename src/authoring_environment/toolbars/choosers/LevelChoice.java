package authoring_environment.toolbars.choosers;

import authoring_environment.editor_windows.CreatorView;
import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * The Level Choice objects hold the relevant information about a class that allows users to choose different
 *
 * @author Julia Long
 * Date started: April 04 18
 */
public class LevelChoice extends ListCell<AuthoredLevel> {

    private CreatorView myWindow;
    private ScrollPane myScrollPane;

    private static final int FONT_SIZE = 15;
    private static final String DELETE_LEVEL = "Delete level";

    LevelChoice(CreatorView window, ScrollPane scrollPane) {
        myWindow = window;
        myScrollPane = scrollPane;
    }

    @Override
    public void updateItem(AuthoredLevel item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            setText(item.getName());
            setFont(new Font(FONT_SIZE));
            setOnMouseClicked(e -> {
                if (e.isControlDown()) {
                    addRightClickButtonBehavior(this, item);
                }
                else {
                    addClickButtonBehavior(item);
                }
            });
        }
        else {
            setText("");
        }
    }

    private void addClickButtonBehavior(AuthoredLevel level) {
        myWindow.getGame().setCurrentLevel(level);
        myScrollPane.setContent(myWindow.getGame().getCurrentLevel().getScrollingGrid());
    }

    private void addRightClickButtonBehavior(Node cell, AuthoredLevel level) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem delete = new MenuItem(DELETE_LEVEL);
        delete.setOnAction(e -> setDeleteBehavior(level));
        contextMenu.getItems().add(delete);
        contextMenu.show(cell, Side.RIGHT, 0, 0);
        }

    private void setDeleteBehavior(AuthoredLevel level) {
        myWindow.getGame().removeLevel(level);
        myScrollPane.setContent(new ScrollingGrid());
    }
}
