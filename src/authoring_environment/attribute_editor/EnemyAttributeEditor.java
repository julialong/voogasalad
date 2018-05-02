package authoring_environment.attribute_editor;

import javafx.scene.Group;

public class EnemyAttributeEditor extends AttributeEditor{

	private static final String ENEMY = "Enemy";
	
	public EnemyAttributeEditor() {
		super();
		myType= ENEMY;
	}
	
	@Override
	void organizeEditor() {
		
		Group basic = new Group();
		BasicAttribute basicAttribute = new BasicAttribute(basic);
		myAttributePane.getChildren().add(basic);
		attributeList.add(basicAttribute);
		
		Group behavior = new Group();
		BehaviorAttribute behaviorAttribute = new BehaviorAttribute(behavior);
		myAttributePane.getChildren().add(behavior);
		attributeList.add(behaviorAttribute);
		
		Group interaction = new Group();
		InteractionAttribute interactionAttribute = new InteractionAttribute(interaction);
		myAttributePane.getChildren().add(interaction);
		attributeList.add(interactionAttribute);
		
		Group movement = new Group();
		MovementAttribute movementAttribute = new MovementAttribute(movement);
		myAttributePane.getChildren().add(movement);
		attributeList.add(movementAttribute);
	
	}

}
