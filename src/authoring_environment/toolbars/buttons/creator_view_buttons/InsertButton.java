package authoring_environment.toolbars.buttons.creator_view_buttons;

import authoring_environment.attribute_editor.BlockAttributeEditor;
import authoring_environment.editor_windows.CreatorView;
import authoring_environment.editor_windows.level_windows.LevelCreator;
import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import engine.level.BasicLevel;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 * The InsertButton, when clicked, triggers a drop-down menu that allows the user
 * to choose to insert a level, splash screen, or object.
 *
 * @author julialong
 * Date started: April 01 18
 */
public class InsertButton extends MenuButton {

    private CreatorView myWindow;

    private static final String INSERT = "Insert";
    private static final String LEVEL = "Level";
    private static final String SPLASH_SCREEN = "Splash Screen";
    private static final String OBJECT = "Object";

    /**
     * Creates a simple Insert menu button with the appropriate drop down items
     */
    public InsertButton(CreatorView window) {
        super(INSERT);
        myWindow = window;
        this.getItems().addAll(createLevelItem(),
                                createSplashItem(),
                                createObject());
    }

    private MenuItem createLevelItem() {
        MenuItem levelItem = new MenuItem(LEVEL);
        levelItem.setOnAction(e -> new LevelCreator(myWindow));
        return levelItem;
    }

    private MenuItem createSplashItem() {
        MenuItem splashItem = new MenuItem(SPLASH_SCREEN);
        splashItem.setOnAction(e -> addLevel());
        return splashItem;
    }

    private MenuItem createObject() {
        MenuItem objectItem = new MenuItem(OBJECT);
        objectItem.setOnAction(e -> new AddElementButton());
        return objectItem;
    }

    private void addLevel() {
        AuthoredLevel splashScreen = new AuthoredLevel(new BasicLevel(), new ScrollingGrid());
        splashScreen.setName(SPLASH_SCREEN);
        splashScreen.setSize(15, 25);
        myWindow.getGame().addLevel(splashScreen);
    }

}
