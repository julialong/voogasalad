package authoring_environment.toolbars.labels;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The SideLabel class manages the labels in the right toolbar.
 *
 * @author Julia Long
 * Date started: April 02 18
 */
public class SideLabel extends HBox{

    private static final int PADDING = 10;
    private static final int FONT_SIZE = 20;

    /**
     * SideLabel creates a new HBox containing the desired text.
     * @param text is the text that should be shown in the label
     */
    public SideLabel(String text) {
        super();
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(PADDING));
        Text thisText = new Text(text);
        thisText.setFont(new Font(FONT_SIZE));
        this.getChildren().add(thisText);
    }
}
