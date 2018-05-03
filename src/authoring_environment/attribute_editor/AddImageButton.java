package authoring_environment.attribute_editor;

import javafx.scene.control.Button;

import java.net.MalformedURLException;


/**
 * 
 * @author Judi Sanchez
 * Date started: April 8 2018
 *
 */
public class AddImageButton extends Button {

	public static final String UPLOAD_IMAGE = "Upload Image";

	public AddImageButton(AttributeEditor editor) {
		super(UPLOAD_IMAGE);
		this.setOnAction(e -> {
			try {
				editor.openFileChooser();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
}
