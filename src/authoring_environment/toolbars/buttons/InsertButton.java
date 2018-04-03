package authoring_environment.toolbars.buttons;

import authoring_environment.editor_windows.LevelCreator;
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

    private static final String INSERT = "Insert";
    private static final String LEVEL = "Level";
    private static final String SPLASH_SCREEN = "Splash Screen";
    private static final String OBJECT = "Object";

    /**
     * Creates a simple Insert menu button with the appropriate drop down items
     */
    public InsertButton() {
        super(INSERT);
        this.getItems().addAll(createLevelItem(),
                                createSplashItem(),
                                createObject());
    }

    private MenuItem createLevelItem() {
        MenuItem levelItem = new MenuItem(LEVEL);
        levelItem.setOnAction(e -> new LevelCreator());
        return levelItem;
    }

    private MenuItem createSplashItem() {
        MenuItem splashItem = new MenuItem(SPLASH_SCREEN);
        // TODO: open splash screen creator when menu item is clicked
        // gameItem.setOnAction(e -> new SplashCreator());
        return splashItem;
    }

    private MenuItem createObject() {
        MenuItem objectItem = new MenuItem(OBJECT);
        // TODO: open element creator when menu item is clicked
        // gameItem.setOnAction(e -> new AuthoredElementCreator());
        return objectItem;
    }

}
