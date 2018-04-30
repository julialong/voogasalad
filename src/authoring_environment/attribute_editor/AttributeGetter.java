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
	
	default Group createInputBox(String attribute) {
		Group group = new Group();
		return group;
	}
}
