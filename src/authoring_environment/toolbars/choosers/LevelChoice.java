package authoring_environment.toolbars.choosers;

import authoring_environment.editor_windows.CreatorView;
import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The Level Choice objects hold the relevant information about a class that allows users to choose different
 *
 * @author Julia Long
 * Date started: April 04 18
 *
 * setDragDrop method came from: https://stackoverflow.com/questions/20412445/how-to-create-a-reorder-able-tableview-in-javafx
 */
public class LevelChoice extends ListCell<AuthoredLevel> {

    private CreatorView myWindow;
    private ScrollPane myScrollPane;

    private ObservableList<AuthoredLevel> levels;
    private Map<String, AuthoredLevel> levelMap;

    private static final int FONT_SIZE = 15;
    private static final String DELETE_LEVEL = "Delete level";

    LevelChoice(CreatorView window, ScrollPane scrollPane) {
        super();
        myWindow = window;
        myScrollPane = scrollPane;
        setDragDrop();
    }

    private void setDragDrop() {

        setOnDragDetected(this::handleDragDetected);

        setOnDragOver(event -> {
            if (event.getGestureSource() != this &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });

        setOnDragEntered(event -> {
            if (event.getGestureSource() != this &&
                    event.getDragboard().hasString()) {
                setOpacity(0.3);
            }
        });

        setOnDragExited(event -> {
            if (event.getGestureSource() != this &&
                    event.getDragboard().hasString()) {
                setOpacity(1);
            }
        });

        setOnDragDropped(event -> {
            if (getItem() == null) {
                return;
            }

            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString()) {
                int draggedIdx = levels.indexOf(levelMap.get(db.getString()));
                int thisIdx = levels.indexOf(getItem());

                AuthoredLevel temp = levels.get(draggedIdx);
                levels.set(draggedIdx, levels.get(thisIdx));
                levels.set(thisIdx, temp);

                levels.set(draggedIdx, getItem());
                levels.set(thisIdx, levelMap.get(db.getString()));

                List<AuthoredLevel> copy = new ArrayList<>(getListView().getItems());
                getListView().getItems().setAll(copy);

                success = true;
            }
            event.setDropCompleted(success);

            event.consume();
        });

        setOnDragDone(Event::consume);
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

    private Map<String, AuthoredLevel> makeMap() {
        Map<String, AuthoredLevel> nameMap = new HashMap<>();
        for (AuthoredLevel level : getListView().getItems()){
            nameMap.put(level.getName(), level);
        }
        return nameMap;
    }

    private void handleDragDetected(Event e) {
        levels = getListView().getItems();
        levelMap = makeMap();
        if (getItem() == null) {
            return;
        }
        Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.put(new DataFormat(getItem().getName()), getItem().getName());
        dragboard.setContent(content);
        e.consume();
    }
}
