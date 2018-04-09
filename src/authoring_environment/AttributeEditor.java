package authoring_environment;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.xml.transform.TransformerException;

import authoring_environment.toolbars.buttons.AddImageButton;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import authoring_environment.CustomElementSaver;


/**
 * This class
 * 
 * @author Judi Sanchez
 * Date started: April 3 2018
 *
 */
public class AttributeEditor {

	private static final String ATTRIBUTE_RESOURCES = "resources/attributes";
	private static final double IMAGE_WIDTH = 200;
	private static final double IMAGE_HEIGHT = 200;
	private GameEntity gameElement;
	private Double elementID; 
	private HashMap<String, List<String>> attributes;
	private List<ComboBox<String>> attributeBoxes;
	private HashMap<String, String> chosenAttributes; 
	private BorderPane myRoot;
	private VBox myAttributePane;
	private VBox myImagePane;
	private HBox myTitlePane;
	private FileChooser filechooser;
	private ImageView image;

	public AttributeEditor(GameEntity element, Double id) {
		elementID= id;
		chosenAttributes = new HashMap<String, String>() ;  
		gameElement= element;
		setUpEditorWindow();
		attributes = loadAttributes();
		makeComboBoxList(attributes);
		organizeEditor();
		
	}

	private HashMap<String, List<String>> loadAttributes() {
		HashMap<String, List<String>> attributes = new HashMap<String, List<String>>();
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
				List<String> optionList = new ArrayList<String>();
				optionList.add(option);
				attributes.put(type, optionList);
			}
		}
		return attributes;
	}

	private void makeComboBoxList(HashMap<String, List<String>> attributes) {
		attributeBoxes = new ArrayList<ComboBox<String>>();
		Set<String> categories = new HashSet<String>(attributes.keySet());
		for (String category : categories) {
			ComboBox<String> attributeBox = new ComboBox<String>();
			attributeBox.getItems().addAll(attributes.get(category));
			attributeBox.getSelectionModel().select(category);
			attributeBox.getStyleClass().add("combobox");
			attributeBox.setOnAction(e -> {try {
				updateAttributes(category, attributeBox.getValue());
			} catch (TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}});
			attributeBoxes.add(attributeBox);
		}
	}
	
	private void setUpEditorWindow() {
		myRoot= new BorderPane();
		myRoot.getStyleClass().add("attribute-editor");
		myAttributePane = new VBox();
		myImagePane= new VBox();
		myTitlePane= new HBox();
		Scene editor= new Scene(myRoot);
		editor.getStylesheets().add("GAE.css");
		myAttributePane.getStyleClass().add("attribute-pane");
		myImagePane.getStyleClass().add("image-pane");
		myTitlePane.getStyleClass().add("attribute-title-pane");
		myRoot.setLeft(myAttributePane);
		myRoot.setRight(myImagePane);
		myRoot.setTop(myTitlePane);
		
		Stage window= new Stage();
		window.setScene(editor);
		window.setTitle("Attribute Editor");
		window.show();
	}
	
	private void organizeEditor() {
		for(int i=0; i<attributeBoxes.size(); i++) {
			myAttributePane.getChildren().add(attributeBoxes.get(i));
			
		}
		
		myImagePane.getChildren().add(new AddImageButton(this));
	}
	
	public void openFileChooser() throws MalformedURLException {
		myImagePane.getChildren().remove(image);
		Stage fileWindow= new Stage();
		filechooser = new FileChooser();
		File file= filechooser.showOpenDialog(fileWindow);
		URI uri= file.toURI();
		URL url= uri.toURL();
		image = new ImageView(url.toString());
		image.setFitHeight(IMAGE_HEIGHT);
		image.setFitWidth(IMAGE_WIDTH);
		myImagePane.getChildren().add(image);
		gameElement.uploadImage(url.toString());
		
	}
	
	private void updateAttributes(String category, String chosenAttribute) throws TransformerException {
		chosenAttributes.put(category, chosenAttribute);
		for(String cat: chosenAttributes.keySet()) {
			System.out.println(cat + "\n");
			System.out.println(chosenAttributes.get(cat) + "\n");
		}
		gameElement.updateAttributes(chosenAttributes);
		CustomElementSaver saver = new CustomElementSaver(gameElement, elementID, chosenAttributes);
	}
	
}
