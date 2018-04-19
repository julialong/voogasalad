package game_player;

import game_player_api.GamePlayerButton;
import javafx.scene.control.Button;

public class VButton extends Button implements GamePlayerButton {

	private String myCommand;
	
	/**
	 * construct a game button using the default button constructor
	 * @param s
	 */
	public VButton(String s) {
		super(s);
		myCommand = s;
		setButtonAction();
		this.getStyleClass().add("Button");
	}
	
	private void setButtonAction() {
		// TODO Auto-generated method stub
		this.setOnMouseClicked((event) -> {
		    // Button was clicked, do something...
		    System.out.println(myCommand);
		});
	}

	@Override
	public String getCommandFromButton() {
		// TODO Auto-generated method stub
		return myCommand;
	}

	@Override
	public void clearButton() {
		// TODO Auto-generated method stub
		
	}
}
