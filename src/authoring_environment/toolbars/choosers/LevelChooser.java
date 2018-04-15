package authoring_environment.toolbars.choosers;

import authoring_environment.game_elements.AuthoredGame;
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

    private AuthoredGame myGame;
    private ScrollPane myScrollPane;

    /**
     * Creates a scrollpane that allows users to choose a level to edit.
     * @param game is the current game
     */
    public LevelChooser(AuthoredGame game, ScrollPane grid, RightBar rightBar) {
        super();
        myGame = game;
        myScrollPane = grid;
        update();
    }

    /**
     * Updates the current list of levels
     */
    public void update() {
        this.getChildren().removeAll(this.getChildren());
        for (Level level : myGame.getLevels()) {
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

    private void addClickButtonBehavior(Level level) {
        myGame.setCurrentLevel(level);
        myScrollPane.setContent(myGame.getCurrentLevel().getGrid());
    }

    private void addRightClickButtonBehavior(Pane levelChoice, Level level) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete level");
        delete.setOnAction(e -> setDeleteBehavior(level));
        contextMenu.getItems().add(delete);
        contextMenu.show(levelChoice, Side.RIGHT, 0 ,0 );
    }

    private void setDeleteBehavior(Level level) {
        myGame.removeLevel(level);
    }

}
