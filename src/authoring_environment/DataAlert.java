package authoring_environment;

import javafx.scene.control.Alert;

/**
 * DataAlert shows an alert box when an error occurs.
 *
 * @author Belanie Nagiel, Maya Messinger, Julia Long
 */
public interface DataAlert {

    /**
     * Shows the alert box for a given exception.
     * @param e is the exception thrown
     */
    default void saveAlert(Exception e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setContentText("Bad");
        alert.show();
    }
}
