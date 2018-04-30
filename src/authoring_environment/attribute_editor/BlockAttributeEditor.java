package authoring_environment.attribute_editor;

import authoring_environment.TreeNode;
import javafx.scene.Group;

public class BlockAttributeEditor extends AttributeEditor {
	
	
	public BlockAttributeEditor() {
		super();
	}

	@Override
	void organizeEditor() {
		Group basic = new Group();
		BasicAttribute basicAttribute = new BasicAttribute(basic);
		myAttributePane.getChildren().add(basic);
		
		Group behavior = new Group();
		BehaviorAttribute behaviorAttribute = new BehaviorAttribute(behavior);
		myAttributePane.getChildren().add(behavior);
		
		Group interaction = new Group();
		InteractionAttribute interactionAttribute = new InteractionAttribute(interaction);
		myAttributePane.getChildren().add(interaction);
		
		Group movement = new Group();
		MovementAttribute movementAttribute = new MovementAttribute(movement);
		myAttributePane.getChildren().add(movement);
		
		
	}

	@Override
	void saveData() {
		TreeNode attributes = new TreeNode("attributes");
		attributes.addChild(new TreeNode("Basic"));
		
		
	}

}
