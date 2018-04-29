package authoring_environment.attribute_editor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.Group;


public interface AttributeGetter {
	
	String ATTRIBUTE_RESOURCES = "resources/";

	default Map<String, List<String>> loadAttributes(String file) {
		HashMap<String, List<String>> attributes = new HashMap<>();
		ResourceBundle resources = ResourceBundle.getBundle(ATTRIBUTE_RESOURCES + file);
		Enumeration<String> attributeOptions = resources.getKeys();
		while (attributeOptions.hasMoreElements()) {
			String option = attributeOptions.nextElement();
			String type = resources.getString(option);
			if(attributes.containsKey(type)) {
				List<String> optionList = attributes.get(type);
				optionList.add(option);
				attributes.put(type, optionList);
			}
			else {
				List<String> optionList = new ArrayList<>();
				optionList.add(option);
				attributes.put(type, optionList);
			}
		}
		return attributes;
	}
	
	default Group createInputBox(String attribute) {
		Group group = new Group();
		return group;
	}
}
