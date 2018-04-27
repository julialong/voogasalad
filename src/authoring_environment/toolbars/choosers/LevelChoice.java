package authoring_environment.toolbars.choosers;

import authoring_environment.editor_windows.CreatorView;
import authoring_environment.editor_windows.level_windows.LevelEditor;
import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;


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
    private static final String SAVE_LEVEL = "Save level";
    private static final String EDIT_LEVEL = "Edit level";

    LevelChoice(CreatorView window) {
        myWindow = window;
        myScrollPane = myWindow.getPane();
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
        MenuItem save = new MenuItem(SAVE_LEVEL);
        save.setOnAction(e -> setSaveBehavior(level));
        MenuItem edit = new MenuItem(EDIT_LEVEL);
        edit.setOnAction(e -> setEditBehavior(level));
        contextMenu.getItems().add(delete);
        contextMenu.getItems().add(save);
        contextMenu.getItems().add(edit);
        contextMenu.show(cell, Side.RIGHT, 0, 0);
        }

    private void setDeleteBehavior(AuthoredLevel level) {
        myWindow.getGame().removeLevel(level);
        myScrollPane.setContent(new ScrollingGrid());
    }

    private void setSaveBehavior(AuthoredLevel level) {
        myWindow.getGame().saveLevel(level);
    }

    private void setEditBehavior(AuthoredLevel level) {
        new LevelEditor(myWindow, level);
    }
}
