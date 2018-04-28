package game_player;

import game_player_api.GamePlayerButton;
import javafx.scene.control.Button;

/**
 * Class that represents the buttons that are entered into the menubar
 *
 * @Author Kelley Scroggs
 */
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
		this.setOnMouseClicked(event -> {
		    // Button was clicked, do something...
		    System.out.println(myCommand);
		});
	}

	@Override
	public String getCommandFromButton() {
		return myCommand;
	}

	@Override
	public void clearButton() {
	}
}
