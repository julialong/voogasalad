package authoring_environment.attribute_editor;

import java.util.List;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class BasicAttribute extends Attribute  {
	
	private static final String BASIC = "BasicInfo";
	/**
	 * Create the basic attribute container which holds all of the specifications
	 * which can be entered to edit the basic attributes of an game entity.
	 */
	public BasicAttribute(Group targetLocation) {
		targetLocation.getChildren().add(setupInputs(BASIC));
	}

	/**
	 * Create all textfield inputs with a label that will specify the purpose of the textfield.
	 * i.e. label = X Dimension => textfield = (some integer which will represent the x dimension)
	 */
	@Override
	public VBox setupInputs(String attributeType) {
		List<String> basicAttributesOptions = super.loadAttributes(attributeType);
		VBox container = new VBox();
		for(String option : basicAttributesOptions){
			System.out.println(option);
			HBox inputLine = new HBox();
			Label instruction = new Label(option);
			TextField input = new TextField();
			inputLine.getChildren().addAll(instruction, input);
			container.getChildren().add(inputLine);
		}
		return container;
	}
}
