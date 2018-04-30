package authoring_environment.attribute_editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class BasicAttribute extends Attribute  {
	
	private static final String BASIC = "Basic";
	private Map<String, TextField> dataFields;

	/**
	 * Create the basic attribute container which holds all of the specifications
	 * which can be entered to edit the basic attributes of an game entity.
	 */
	public BasicAttribute(Group targetLocation) {
		dataFields = new HashMap<>();
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
			dataFields.put(instruction.getText(), input);
			inputLine.getChildren().addAll(instruction, input);
			container.getChildren().add(inputLine);
		}
		return container;
	}

	/**
	 * Returns a map of option to a list of data fields that the user inputs.
	 * If there are no data fields to input then it is an empty list.
	 */
	@Override
	public Map<String, List<String>> getAttributeContent() {
		Map<String, List<String>> contents = new HashMap<>();
		for(String option : dataFields.keySet()){
			String input = dataFields.get(option).getText();
			List<String> contentList = new ArrayList<>();
			contentList.add(input);
			contents.put(option, contentList);
		}
		return contents;
	}
}
