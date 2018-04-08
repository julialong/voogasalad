package authoring_environment.toolbars.buttons;

import authoring_environment.AuthoredElementCreator;
import javafx.scene.control.Button;

import java.net.MalformedURLException;

import authoring_environment.AttributeEditor;

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
