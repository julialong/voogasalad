package authoring_environment.editor_windows;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
	private static final String ELEMENT_DATA_PATH = "./data/authoredElementData/";
	
	private List<ComboBox<String>> attributeBoxes;
	private List<String> defaults;
	
	/**
	 * 
	 * @param attributes
	 * @param chosenAttributes
	 */
	public AttributeComboBoxesPane(Map<String, List<String>> attributes, AttributeEditor editor) {
		attributeBoxes = makeComboBoxList(attributes, editor );
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
	
	/*private void setDefaults() {
		File parentFolder = new File(ELEMENT_DATA_PATH);
		List<File> files = Arrays.asList(parentFolder.listFiles());
		List<String> fileNames = new ArrayList<String>();
		for (File file : files) {
			file.to
		}
		if(files.contains(File("hi"))){
			
		}
	}*/
	
}
