package authoring_environment.attribute_editor;

import javafx.scene.Group;
import javafx.scene.layout.VBox;

public class InteractionAttribute extends Attribute {
	
	 private static final String INTERACTION= "Interaction";
		
	 
	 public InteractionAttribute(Group targetLocation){
	        targetLocation.getChildren().add(setupInputs(INTERACTION));
	    }
	 
	 @Override
	 
	 public VBox setupInputs(String attributeType) {
		// TODO Auto-generated method stub
		return null;
	}

}
