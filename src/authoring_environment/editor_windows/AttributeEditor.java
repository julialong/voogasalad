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
import java.util.Map;
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
// TODO: refactor this class into smaller classes to reduce number of dependencies
public class AttributeEditor {

	private static final String ATTRIBUTE_RESOURCES = "resources/attributes";
	private static final double IMAGE_WIDTH = 200;
	private static final double IMAGE_HEIGHT = 200;
	private static final String INPUT_ID = "Please enter a custom element ID: ";
	private static final String SUBMIT= "Submit";
	private static final int SMALL_FONT = 15;
	private static final int LARGE_FONT = 20;
	private static final String GAME_ENTITY = "GameEntity";

	private GameElement gameElement;
	private String elementID; 
	private Map<String, List<String>> attributes;
	private List<ComboBox<String>> attributeBoxes;
	private Map<String, String> chosenAttributes; 
	private VBox myAttributePane;
	private VBox myImagePane;
	private HBox myTitlePane;
	private ImageView image;
	private Stage window;
	private File imageFile;
	private URI imageURI;
	private URL imageURL;



	public AttributeEditor(GameElement element) {
		chosenAttributes = new HashMap<>() ;
		gameElement= element;
		setUpEditorWindow();
		attributes = loadAttributes();
		AttributeComboBoxesPane boxesPane = new AttributeComboBoxesPane(attributes, this);
		attributeBoxes = boxesPane.getAttributeBoxes();
		organizeEditor();
		
	}

	private Map<String, List<String>> loadAttributes() {
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
	
	private void setUpEditorWindow() {
		BorderPane myRoot= new BorderPane();
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
        instruction.setFont(new Font(SMALL_FONT));
        myTitlePane.getChildren().add(instruction);
        TextField idNameInput = new TextField();
        myTitlePane.getChildren().add(idNameInput);
        createIDButton(idNameInput, instruction);
	}
	
	private void createIDButton(TextField idNameInput, Label instruction) {
        Button submitButton = new Button(SUBMIT);
        submitButton.setOnAction(e -> {
            myTitlePane.getChildren().removeAll(idNameInput,submitButton,instruction);
            elementID = idNameInput.getText();
            Text name = new Text("Element ID: " + elementID);
            name.setFont(new Font(LARGE_FONT));
            myTitlePane.getChildren().add(name);
            gameElement.setID(elementID);
        });
        myTitlePane.getChildren().add(submitButton);
    }
	
	private void organizeEditor() {
		setUpInputBox();
		for(ComboBox<String> attributeBox: attributeBoxes) {
			myAttributePane.getChildren().add(attributeBox);
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
		FileChooser filechooser = new FileChooser();
		filechooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
		imageFile= filechooser.showOpenDialog(fileWindow);
		imageURI = imageFile.toURI();
		imageURL = imageURI.toURL();
		image = new ImageView(imageURL.toString());
		image.setFitHeight(IMAGE_HEIGHT);
		image.setFitWidth(IMAGE_WIDTH);
		myImagePane.getChildren().add(image);
		
	}
	
	/**
	 * 
	 * @param category
	 * @param chosenAttribute
	 * @throws TransformerException
	 */
	public void updateAttribute(String category, String chosenAttribute) throws TransformerException {
		chosenAttributes.put(category, chosenAttribute);
	}
	
	/**
	 * This method triggers the gameElement updateAttributes method which saves attributes for an element
	 * to an XML file
	 */
	public void saveChanges() {
		Path source = Paths.get(imageURI);
		Path target = Paths.get("data/authoredElementImages/" + elementID + imageFile.getAbsolutePath().substring(imageFile.getAbsolutePath().lastIndexOf(".")));
		try {
			Files.copy(source, target);
		} catch (IOException e) {
			// TODO: Handle this error
			e.printStackTrace();
		}
		gameElement.uploadImage(target.toString());
		gameElement.updateAttributes(chosenAttributes);
		window.close();
	}
	
}
