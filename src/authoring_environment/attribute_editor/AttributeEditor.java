package authoring_environment.attribute_editor;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.TransformerException;

import authoring_environment.AttributeGetter;
import authoring_environment.DataAlert;
import authoring_environment.authored_elements.GameElement;
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
// TODO: get rid of strings in methods 
public class AttributeEditor implements AttributeGetter, DataAlert {

	private static final double IMAGE_WIDTH = 200;
	private static final double IMAGE_HEIGHT = 200;
	private static final String INPUT_ID = "Please enter a custom element ID: ";
	private static final String SUBMIT= "Submit";
	private static final int SMALL_FONT = 15;
	private static final int LARGE_FONT = 20;
	private static final String GAME_ENTITY = "GameEntity";
	private static final String CUSTOM_IMAGES_FOLDER = "data/authoredElementImages/";
	private static final String  SLASH = "/";
	private static final String DIMENSIONX = "X Dimension";
	private static final String DIMENSIONY = "Y Dimension";
	private static final String DEFAULT = "1";
	

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
	private String xDim;
	private String yDim;


	/**
	 * 
	 */
	public AttributeEditor() {
		setUpEditorWindow();
		xDim = DEFAULT;
		yDim = DEFAULT;
		chosenAttributes = new HashMap<>() ;
		gameElement= new GameElement();
		attributes = loadAttributes();
		AttributeComboBoxesPane boxesPane = new AttributeComboBoxesPane(attributes, this);
		attributeBoxes = boxesPane.getAttributeBoxes();
		organizeEditor();
		
	}
	
	public AttributeEditor(GameElement element) {
		gameElement = element;
		elementID = gameElement.getID();
		chosenAttributes = gameElement.getAttributes();
		xDim = gameElement.getDimensions().get(0);
		yDim = gameElement.getDimensions().get(1);
		String imagePath = gameElement.getImagePath();
		attributes = loadAttributes();
		AttributeComboBoxesPane boxesPane = new AttributeComboBoxesPane(attributes, chosenAttributes, this);
		attributeBoxes = boxesPane.getAttributeBoxes();
		setUpEditorWindow();
		organizeEditor();
		imageFile = new File("file:" + imagePath);
		imageURI = imageFile.toURI();
		image = new ImageView("file:" + imagePath);
		image.setFitHeight(IMAGE_HEIGHT);
		image.setFitWidth(IMAGE_WIDTH);
		myImagePane.getChildren().add(image);
		
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
        TextField idNameInput = new TextField(elementID);
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
	
	private void createDimensionsInputs() {
			Label x = new Label(DIMENSIONX);
	        x.setFont(new Font(SMALL_FONT));
	        myAttributePane.getChildren().add(x);
	        TextField xInput = new TextField(xDim);
	        myAttributePane.getChildren().add(xInput);
	        Label y = new Label(DIMENSIONY);
	        y.setFont(new Font(SMALL_FONT));
	        myAttributePane.getChildren().add(y);
	        TextField yInput = new TextField(yDim);
	        myAttributePane.getChildren().add(yInput);
	        createDimensionsButton(xInput, yInput, x, y);
	}
	
	private void createDimensionsButton(TextField xInput, TextField yInput, Label instructionX, Label instructionY) {
        Button submitButton = new Button(SUBMIT);
        submitButton.setOnAction(e -> {
            myAttributePane.getChildren().removeAll(xInput, yInput, instructionX, instructionY);
            xDim = xInput.getText();
            yDim = yInput.getText();
            Text name = new Text("Element ID: " + elementID);
            name.setFont(new Font(LARGE_FONT));
            myAttributePane.getChildren().add(name);
            gameElement.setID(elementID);
        });
        myAttributePane.getChildren().add(submitButton);
    }
	
	private void organizeEditor() {
		createDimensionsInputs();
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
		URL imageURL = imageURI.toURL();
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
		Path target = Paths.get(CUSTOM_IMAGES_FOLDER + chosenAttributes.get(GAME_ENTITY) + SLASH + elementID + imageFile.getAbsolutePath().substring(imageFile.getAbsolutePath().lastIndexOf(".")));
		try {
			Files.copy(source, target);
		} catch (IOException e) {
			saveAlert(e);
		}
		gameElement.updateDimensions(xDim, yDim);
		gameElement.uploadImage(target.toString());
		gameElement.updateAttributes(chosenAttributes);
		window.close();
	}
	
}
