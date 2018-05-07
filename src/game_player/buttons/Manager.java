package game_player.buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * This interface wraps the ButtonManager class. The ButtonManager class
 * has significantly more public methods whereas the interface it implements
 * only defines one. Since the ButtonManager class uses reflection to create the
 * functionality within the buttons the methods used must be public. To counter
 * this gap in privacy it is wrapped by a Manager interface. This interface
 * also gives another way to add Buttons to the menubar without using the
 * resource file.
 */
public interface Manager {

    /**
     * Adds a button with a specified button name and some sort of
     * function that will occur on the click of the button
     */
    void addButton(String buttonName, EventHandler<ActionEvent> buttonEvent);
}
