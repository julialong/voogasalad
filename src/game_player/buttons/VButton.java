package game_player.buttons;

import game_player_api.GamePlayerButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.lang.reflect.Method;

/**
 * Class that represents the buttons that are entered into the menubar
 *
 * @Author Kelley Scroggs
 */
public class VButton extends Button implements GamePlayerButton {

	private String myCommand;

	/**
	 * construct a game button using the default button constructor
	 */
	public VButton(String s) {
		super(s);
		myCommand = s;
		this.getStyleClass().add("Button");
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
