package authoring_environment.attribute_editor;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;

public class InteractionAttribute extends Attribute {
	
	 private static final String INTERACTION= "Interaction";
		
	 
	 public InteractionAttribute(Group targetLocation){
	        targetLocation.getChildren().add(setupInputs(INTERACTION));
	    }
	 
	 @Override
	 
	 public VBox setupInputs(String attributeType) {
		 VBox container = new VBox();
	        List<String> behaviorAttributesOptions = super.loadAttributes(attributeType);
	        ObservableList<String> options = FXCollections.observableArrayList(behaviorAttributesOptions);
	        ListView<String> chooser = new ListView<>(options);
	        container.getChildren().add(chooser);
	        chooser.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	        chooser.setOnMouseClicked(event -> {
	            List<String> selectedItems = chooser.getSelectionModel().getSelectedItems();
	            for(String option : selectedItems){
	                container.getChildren().add(getOptionContents(option));
	            }
	        });
	        return container;
	}

}
