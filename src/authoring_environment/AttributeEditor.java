package authoring_environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ComboBox;

/**
 * This class 
 * @author Judi Sanchez
 * Date started: April 3 2018
 *
 */
public class AttributeEditor {
	
	private static final String ATTRIBUTE_FILE = "HI";
	private ComboBox[] attributeBoxes;
	
	public List<String> loadAttributes() {
		File attributesFile = new File(ATTRIBUTE_FILE);
		List<String> attributeNames = new ArrayList<String>();
		return attributeNames;
	}
	

}
