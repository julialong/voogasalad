package authoring_environment.attribute_editor;

import javafx.scene.control.Button;

/**
 * 
 * The CloseAttributeEditorButton, when clicked, will trigger the CustomElementSaver which
 * will save the attributes of a given element in an xml file with its ID as the name. 
 * It also closes the stage that holds the AttributeEditor
 *
 * @author Judi Sanchez
 * Date started: April 9 2018
 * 
 *
 */
public class CloseAttributeEditorButton extends Button{
	
	public static final String SAVE = "Save Attributes";

	public CloseAttributeEditorButton(AttributeEditor editor) {
		super(SAVE);
		this.setOnAction(e -> {
				editor.saveData();
		});
	}

}
