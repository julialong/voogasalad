package authoring_environment.editor_windows;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.xml.transform.TransformerException;

import authoring_environment.authored_elements.GameElement;
import authoring_environment.toolbars.buttons.AddImageButton;
import authoring_environment.toolbars.buttons.CloseAttributeEditorButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


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
	private static final String INPUT_ID = "Please enter a custom element ID: ";
	private static final String SUBMIT= "Submit";
	private GameElement gameElement;
	private HashMap<String, List<String>> attributes;
	private List<ComboBox<String>> attributeBoxes;
	private HashMap<String, String> chosenAttributes; 
	private BorderPane myRoot;
	private VBox myAttributePane;
	private VBox myImagePane;
	private HBox myTitlePane;
	private FileChooser filechooser;
	private ImageView image;
	private Stage window;
	private String elementID; 

	public AttributeEditor(GameElement element) {
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
				updateAttribute(category, attributeBox.getValue());
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
		
		window= new Stage();
		window.setScene(editor);
		window.setTitle("Attribute Editor");
		window.show();
	}
	
	private void setUpInputBox() {
		Label instruction = new Label(INPUT_ID);
        instruction.setFont(new Font(15));
        myTitlePane.getChildren().add(instruction);
        TextField idNameInput = new TextField();
        myTitlePane.getChildren().add(idNameInput);
        createIDButton(idNameInput, instruction);
	}
	
	private void createIDButton(TextField idNameInput, Label instruction) {
        Button submitButton = new Button(SUBMIT);
        submitButton.setOnAction(e -> {
            myTitlePane.getChildren().removeAll(idNameInput,submitButton,instruction);
            String id = idNameInput.getText();
            Text name = new Text("Element ID: " + id);
            name.setFont(new Font(20));
            myTitlePane.getChildren().add(name);
            gameElement.setID(id);
        });
        myTitlePane.getChildren().add(submitButton);
    }
	
	private void organizeEditor() {
		setUpInputBox();
		for(int i=0; i<attributeBoxes.size(); i++) {
			myAttributePane.getChildren().add(attributeBoxes.get(i));
			
		}
		
		myAttributePane.getChildren().add(new CloseAttributeEditorButton(this));
		myImagePane.getChildren().add(new AddImageButton(this));
	}
	
	/**
	 * This method opens the window that allows the user to upload an image file to be 
	 * used as the image for a custom element 
	 * @throws MalformedURLException
	 */
	public void openFileChooser() throws MalformedURLException {
		myImagePane.getChildren().remove(image);
		Stage fileWindow= new Stage();
		filechooser = new FileChooser();
		filechooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
		File file= filechooser.showOpenDialog(fileWindow);
		URI uri= file.toURI();
		URL url= uri.toURL();
		Path source = Paths.get(uri);
		Path target = Paths.get("data/customElements/" + file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("/") + 1));
		try {
			Files.copy(source, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
		image = new ImageView(url.toString());
		image.setFitHeight(IMAGE_HEIGHT);
		image.setFitWidth(IMAGE_WIDTH);
		myImagePane.getChildren().add(image);
		gameElement.uploadImage(target.toString());
		
	}
	
	private void updateAttribute(String category, String chosenAttribute) throws TransformerException {
		chosenAttributes.put(category, chosenAttribute);
	}
	
	/**
	 * This method triggers the gameElement updateAttributes method which saves attributes for an element
	 * to an XML file
	 */
	public void saveChanges() {
		gameElement.updateAttributes(chosenAttributes);
		window.close();
	}
	
}
