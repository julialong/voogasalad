package authoring_environment.attribute_editor;

import javafx.scene.Group;

public class EnemyAttributeEditor extends AttributeEditor{

	@Override
	void organizeEditor() {
		
		Group basic = new Group();
		BasicAttribute basicAttribute = new BasicAttribute(basic);
		myAttributePane.getChildren().add(basic);
		
		Group behavior = new Group();
		BehaviorAttribute behaviorAttribute = new BehaviorAttribute(behavior);
		myAttributePane.getChildren().add(behavior);
		
		
	}

	@Override
	void saveData() {
		// TODO Auto-generated method stub
		
	}

}
