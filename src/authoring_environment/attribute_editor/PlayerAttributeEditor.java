package authoring_environment.attribute_editor;

import javafx.scene.Group;

public class PlayerAttributeEditor extends AttributeEditor {

	private static final String PLAYER = "Player";
	
	public PlayerAttributeEditor() {
		super();
		myType= PLAYER;
	}
	
	@Override
	void organizeEditor() {
		Group basic = new Group();
		BasicAttribute basicAttribute = new BasicAttribute(basic);
		myAttributePane.getChildren().add(basic);
		attributeList.add(basicAttribute);
		
		Group movement = new Group();
		MovementAttribute movementAttribute = new MovementAttribute(movement);
		myAttributePane.getChildren().add(movement);
		attributeList.add(movementAttribute);
		
		
	}

}
