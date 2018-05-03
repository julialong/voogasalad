package authoring_environment.attribute_editor;

import javafx.scene.control.Button;

public class CreateEnemyButton extends Button {
	public static final String CREATE_NEW_ENEMY = "Create Enemy";
	
	public CreateEnemyButton() {
		super(CREATE_NEW_ENEMY);
		this.setOnAction(e->{
			new EnemyAttributeEditor();
		});
	}

}
