package authoring_environment.toolbars.choosers;

import authoring_environment.editor_windows.CreatorView;
import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


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

    private static final int FONT_SIZE = 15;
    private static final String DELETE_LEVEL = "Delete level";

    LevelChoice(CreatorView window, ScrollPane scrollPane) {
        myWindow = window;
        myScrollPane = scrollPane;
        setDragDrop();
    }

    public void setDragDrop() {
        setOnDragDetected(event -> {
            if (getItem() == null) {
                return;
            }

            ObservableList<AuthoredLevel> items = getListView().getItems();

            Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            //content.put(getItem());
            dragboard.setDragView(
                    birdImages.get(
                            items.indexOf(
                                    getItem()
                            )
                    )
            );
            dragboard.setContent(content);

            event.consume();
        });

        setOnDragOver(event -> {
            if (event.getGestureSource() != thisCell &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });

        setOnDragEntered(event -> {
            if (event.getGestureSource() != thisCell &&
                    event.getDragboard().hasString()) {
                setOpacity(0.3);
            }
        });

        setOnDragExited(event -> {
            if (event.getGestureSource() != thisCell &&
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
                ObservableList<String> items = getListView().getItems();
                int draggedIdx = items.indexOf(db.getString());
                int thisIdx = items.indexOf(getItem());

                Image temp = birdImages.get(draggedIdx);
                birdImages.set(draggedIdx, birdImages.get(thisIdx));
                birdImages.set(thisIdx, temp);

                items.set(draggedIdx, getItem());
                items.set(thisIdx, db.getString());

                List<String> itemscopy = new ArrayList<>(getListView().getItems());
                getListView().getItems().setAll(itemscopy);

                success = true;
            }
            event.setDropCompleted(success);

            event.consume();
        });

        setOnDragDone(DragEvent::consume);
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
