package game_player;
import game_player_api.*;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class VMenuBar implements MenuBar{

	private HBox myMenuBar;
	
	public VMenuBar() {
		initMenuBar();
	}
	
	private void initMenuBar() {
		// TODO Auto-generated method stub
		HBox bar = new HBox();
		myMenuBar = bar;
	}

	@Override
	public Node getNode() {
		// TODO Auto-generated method stub
		return myMenuBar;
	}

	@Override
	public void addButton(GamePlayerButton b) {
		// TODO Auto-generated method stub
		myMenuBar.getChildren().add((Node) b);
	}

}
