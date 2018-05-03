package authoring_environment.attribute_editor;

import javafx.scene.control.Button;

public class CreatePlayerButton extends Button{
	
	public static final String CREATE_NEW_PLAYER = "Create Player";
	
	public CreatePlayerButton() {
		super(CREATE_NEW_PLAYER);
		this.setOnAction(e-> {
			new PlayerAttributeEditor();
		});
	}

}
