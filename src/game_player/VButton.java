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
	 * construct a game button using the default button constructor.
	 * 
	 * @param s
	 */
	public VButton(String s) {
		super(s);
		myCommand = s;
		setButtonAction();
		this.getStyleClass().add("Button");
	}

	/**
	 * makes button print name when pressed.
	 */
	private void setButtonAction() {
		this.setOnMouseClicked(event -> {
			System.out.println(myCommand);
		});
	}

	/**
	 * returns the button command.
	 */
	@Override
	public String getCommandFromButton() {
		return myCommand;
	}

	/**
	 * Stops the button from printing/returning it's command.
	 */
	@Override
	public void clearButton() {
		myCommand = null;
	}
}
