package game_player;

import game_player_api.GameView;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VGameView implements GameView{
	
	private HBox myGV;
	
	private int temp = 0;
	
	public VGameView() {
		myGV = new HBox();
		myGV.getChildren().add(new Rectangle(200,200, Color.RED));
	}
	
	
	@Override
	public Node getNode() {
		// TODO Auto-generated method stub
		return myGV;
	}

	@Override
	public void updateGame() {
		// TODO Auto-generated method stub
		myGV.getChildren().clear();
		temp++;
		if(temp%2 == 0) {
			myGV.getChildren().add(new Rectangle(200,200, Color.BLUE));
		} else {
			myGV.getChildren().add(new Rectangle(200,200, Color.GREEN));
		}
	}

}
