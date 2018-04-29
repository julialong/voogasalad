package authoring_environment.editor_windows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.TransformerException;

import javafx.scene.control.ComboBox;

/**
 * 
 * @author Judi Sanchez 
 * Date started: April 18 2018
 *
 */
public class AttributeComboBoxesPane {
	
	private List<ComboBox<String>> attributeBoxes;
	
	/**
	 * 
	 * @param attributes
	 * @param chosenAttributes
	 */
	public AttributeComboBoxesPane(Map<String, List<String>> attributes, AttributeEditor editor) {
		attributeBoxes = makeComboBoxList(attributes, editor );
	}
	
	public AttributeComboBoxesPane(Map<String, List<String>> attributes, Map<String, String> chosenAttributes, AttributeEditor editor) {
		attributeBoxes = makeComboBoxList(attributes, editor );
		setPreviousChoices(chosenAttributes);
	}
	
	
	private List<ComboBox<String>> makeComboBoxList(Map<String, List<String>> attributes,  AttributeEditor editor) {
		List<ComboBox<String>> boxes = new ArrayList<>();
		Set<String> categories = new HashSet<>(attributes.keySet());
		for (String category : categories) {
			ComboBox<String> attributeBox = new ComboBox<>();
			attributeBox.getItems().addAll(attributes.get(category));
			attributeBox.getSelectionModel().select(category);
			attributeBox.getStyleClass().add("combobox");
			attributeBox.setOnAction(e -> {try {
				editor.updateAttribute(category, attributeBox.getValue() );
			} catch (TransformerException e1) {
				// TODO Handle this exception
				e1.printStackTrace();
			}});
			boxes.add(attributeBox);
		}
		return boxes;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<ComboBox<String>> getAttributeBoxes(){
		return attributeBoxes;
	}
	
	private void setPreviousChoices(Map<String, String> choices) {
		for( ComboBox<String> box : attributeBoxes) {
			String category = box.getValue();
			if(choices.containsKey(category)) {
				box.getSelectionModel().select(choices.get(category));
			}
		}
	}
	
}
