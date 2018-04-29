package authoring_environment;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public interface AttributeGetter {
	
	String ATTRIBUTE_RESOURCES = "resources/attributes";

	default Map<String, List<String>> loadAttributes() {
		HashMap<String, List<String>> attributes = new HashMap<>();
		ResourceBundle resources = ResourceBundle.getBundle(ATTRIBUTE_RESOURCES);
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
}
