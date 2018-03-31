package authoring_environment.toolbars;

import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;

public class TopBar extends ToolBar{

    private Pane myRoot;

    public TopBar(Pane pane) {
        super();
        myRoot = pane;
    }
}
