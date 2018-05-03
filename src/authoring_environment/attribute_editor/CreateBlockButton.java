package authoring_environment.attribute_editor;

import javafx.scene.control.Button;

public class CreateBlockButton extends Button {
	public static final String CREATE_NEW_BLOCK = "Create Block";
	
	public CreateBlockButton() {
		super(CREATE_NEW_BLOCK);
		this.setOnAction(e->{
			new BlockAttributeEditor();
		});
	}

}
