package game_player.buttons;
import game_player_api.GamePlayerButton;
import game_player_api.MenuBar;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * Represents the menubar class which contains all the buttons
 *
 * @Author Kelley Scroggs
 */
public class VMenuBar implements MenuBar {

	private HBox myMenuBar;
	
	public VMenuBar() {
		initMenuBar();
	}
	
	/**
	 * creates a new menubar and adds styling.
	 */
	private void initMenuBar() {
		HBox bar = new HBox();
		myMenuBar = bar;
		myMenuBar.getStyleClass().add("menubar");
	}

	/**
	 * Returns the menubar so it can be added to a scene.
	 */
	@Override
	public Node getNode() {
		return myMenuBar;
	}

	/**
	 * Adds a button to the menubar for display.
	 */
	@Override
	public void addButton(GamePlayerButton b) {
		myMenuBar.getChildren().add((Node) b);
	}
}
