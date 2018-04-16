package authoring_environment.toolbars.choosers;

import authoring_environment.editor_windows.CreatorView;
import authoring_environment.game_elements.AuthoredGame;
import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.toolbars.RightBar;
import engine.level.Level;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 * The level chooser allows users to select an available level to edit.
 *
 * @author Julia Long
 * Date started: April 04 18
 */
public class LevelChooser extends VBox {

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
        update();
    }

    /**
     * Updates the current list of levels
     */
    public void update() {
        this.getChildren().removeAll(this.getChildren());
        for (AuthoredLevel level : myWindow.getGame().getLevels()) {
            Pane thisLevelChoice = new LevelChoice(level);
            thisLevelChoice.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    if (e.isControlDown())
                        addRightClickButtonBehavior(thisLevelChoice, level);
                    else
                        addClickButtonBehavior(level);
                }
            });
            this.getChildren().add(thisLevelChoice);
        }
    }

    private void addClickButtonBehavior(AuthoredLevel level) {
        myWindow.getGame().setCurrentLevel(level);
        myScrollPane.setContent(myWindow.getGame().getCurrentLevel().getScrollingGrid());
    }

    private void addRightClickButtonBehavior(Pane levelChoice, AuthoredLevel level) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete level");
        delete.setOnAction(e -> setDeleteBehavior(level));
        contextMenu.getItems().add(delete);
        contextMenu.show(levelChoice, Side.RIGHT, 0 ,0 );
    }

    private void setDeleteBehavior(AuthoredLevel level) {
        myWindow.getGame().removeLevel(level);
    }

}
