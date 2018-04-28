package authoring_environment.toolbars.choosers;

import authoring_environment.editor_windows.CreatorView;
import authoring_environment.game_elements.AuthoredLevel;
import javafx.scene.control.ListView;

/**
 * The level chooser allows users to select an available level to edit.
 *
 * @author Julia Long
 * Date started: April 04 18
 * Resources used: https://docs.oracle.com/javafx/2/ui_controls/list-view.htm
 */
public class LevelChooser extends ListView<AuthoredLevel> {

    private CreatorView myWindow;


    /**
     * Creates a scrollpane that allows users to choose a level to edit.
     * @param window is the current window
     */
    public LevelChooser(CreatorView window) {
        super(window.getGame().getObservableLevels());
        myWindow = window;
        this.setPrefHeight(200);
        this.setItems(window.getGame().getObservableLevels());
        changeFormat();
    }

    private void changeFormat() {
        this.setCellFactory(param -> new LevelChoice(myWindow));
    }
}
