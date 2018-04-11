package authoring_environment.toolbars.choosers;

import authoring_environment.AuthoredGame;
import authoring_environment.toolbars.RightBar;
import engine.level.Level;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;


/**
 * The level chooser allows users to select an available level to edit.
 *
 * @author Julia Long
 * Date started: April 04 18
 */
public class LevelChooser extends VBox {

    private AuthoredGame myGame;
    private ScrollPane myScrollPane;

    private int notify;

    /**
     * Creates a scrollpane that allows users to choose a level to edit.
     * @param game is the current game
     */
    public LevelChooser(AuthoredGame game, ScrollPane grid, RightBar rightBar) {
        super();
        myGame = game;
        myScrollPane = grid;
        addListener();
    }

    private void addListener() {
        myGame.getObservableLevels().addListener((ListChangeListener<Level>) change -> {
            while (change.next())
                if (change.wasAdded()) {
                    update();
                }
        });
    }

    /**
     * Updates the current list of levels
     */
    public void update() {
        this.getChildren().removeAll(this.getChildren());
        System.out.println("size " + myGame.getLevels().size());
        for (Level level : myGame.getLevels()) {
            System.out.println(level.getName());
            Pane thisLevelChoice = new LevelChoice(level);
            thisLevelChoice.setOnMouseClicked(e -> {
                myGame.setCurrentLevel(level);
                System.out.println("Current level: " + myGame.getCurrentLevel().getName());
                myScrollPane.setContent(myGame.getCurrentLevel().getGrid());
            });
            this.getChildren().add(thisLevelChoice);
        }
    }

}
