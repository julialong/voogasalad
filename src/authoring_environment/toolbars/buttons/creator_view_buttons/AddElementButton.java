package authoring_environment.toolbars.buttons.creator_view_buttons;

import authoring_environment.attribute_editor.BlockAttributeEditor;
import authoring_environment.attribute_editor.EnemyAttributeEditor;
import authoring_environment.attribute_editor.PlayerAttributeEditor;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 */
public class AddElementButton extends MenuButton {

    private static final String ADD_ELEMENT= "Create new element";
    private static final String BLOCK = "Create Block";
    private static final String PLAYER = "Create Player";
    private static final String ENEMY = "Create Enemy";

    /**
     * Creates a simple add element button.
     */
    public AddElementButton() {
    		super(ADD_ELEMENT);
        this.getItems().addAll(createBlock(), createPlayer(), createEnemy());
                

    }
    
    private MenuItem createBlock() {
        MenuItem item = new MenuItem(BLOCK);
        item.setOnAction(e -> new BlockAttributeEditor());
        return item;
    }

    private MenuItem createPlayer() {
        MenuItem item = new MenuItem(PLAYER);
        item.setOnAction(e -> new PlayerAttributeEditor());
        return item;
    }

    private MenuItem createEnemy() {
        MenuItem item = new MenuItem(ENEMY);
        item.setOnAction(e -> new EnemyAttributeEditor());
        return item;
    }

}
