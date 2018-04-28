package game_player;
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
	
	private void initMenuBar() {
		HBox bar = new HBox();
		myMenuBar = bar;
		myMenuBar.getStyleClass().add("menubar");
	}

	@Override
	public Node getNode() {
		return myMenuBar;
	}

	@Override
	public void addButton(GamePlayerButton b) {
		myMenuBar.getChildren().add((Node) b);
	}
}
