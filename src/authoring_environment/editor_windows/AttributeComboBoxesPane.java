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
	public AttributeComboBoxesPane(Map<String, List<String>> attributes, Map<String, String> chosenAttributes) {
		attributeBoxes = makeComboBoxList(attributes, chosenAttributes);
	}
	
	private List<ComboBox<String>> makeComboBoxList(Map<String, List<String>> attributes,  Map<String, String> chosenAttributes) {
		List<ComboBox<String>> boxes = new ArrayList<>();
		Set<String> categories = new HashSet<>(attributes.keySet());
		for (String category : categories) {
			ComboBox<String> attributeBox = new ComboBox<>();
			attributeBox.getItems().addAll(attributes.get(category));
			attributeBox.getSelectionModel().select(category);
			attributeBox.getStyleClass().add("combobox");
			attributeBox.setOnAction(e -> {try {
				updateAttribute(category, attributeBox.getValue(), chosenAttributes);
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
	
	private void updateAttribute(String category, String chosenAttribute,  Map<String, String> chosenAttributes) throws TransformerException {
		chosenAttributes.put(category, chosenAttribute);
	}
	
	
}
