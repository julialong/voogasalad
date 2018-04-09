package authoring_environment;

import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CustomElementSaver {
	
	private DocumentBuilder builder;
	
	CustomElementSaver(GameEntity gameElment, HashMap<String, String> attributes){
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void makeNodes() {
		for(attribute: attributes.keySet())
		
	}

}
