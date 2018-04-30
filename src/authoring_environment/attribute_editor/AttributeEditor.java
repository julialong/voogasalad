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
import authoring_environment.DataAlert;
import authoring_environment.authored_elements.GameElement;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
public abstract class AttributeEditor implements DataAlert {

	private static final double IMAGE_WIDTH = 200;
	private static final double IMAGE_HEIGHT = 200;
	public static final String ENTER_ID= "Enter ID";

	protected VBox myAttributePane;
	protected VBox myImagePane;
	protected HBox myTitlePane;
	

	private GameElement gameElement;
	TextField idField;

	private ImageView image;
	private Stage window;
	private File imageFile;
	private URI imageURI;



	/**
	 * 
	 */
	public AttributeEditor() {
		setUpEditorWindow();
		//gameElement= new GameElement();
		organizeEditor();
		
	}
	
	/*public AttributeEditor(GameElement element) {
		gameElement = element;
		elementID = gameElement.getID();
		chosenAttributes = gameElement.getAttributes();
		xDim = gameElement.getDimensions().get(0);
		yDim = gameElement.getDimensions().get(1);
		String imagePath = gameElement.getImagePath();
		setUpEditorWindow();
		organizeEditor();
		imageFile = new File("file:" + imagePath);
		imageURI = imageFile.toURI();
		image = new ImageView("file:" + imagePath);
		image.setFitHeight(IMAGE_HEIGHT);
		image.setFitWidth(IMAGE_WIDTH);
		myImagePane.getChildren().add(image);
		
	}*/

	/**
	 * Creates the BorderPane, Scene, and Stage and the different Boxes that go in the BorderPane
	 * Adds the AddImageButton to myImagePane
	 */
	private void setUpEditorWindow() {
		BorderPane myRoot= new BorderPane();
		myRoot.getStyleClass().add("attribute-editor");
		myAttributePane = new VBox();
		myImagePane= new VBox();
		myImagePane.getChildren().add(new AddImageButton(this));
		myTitlePane= new HBox();
		Label idInstruction = new Label(ENTER_ID);
		idField = new TextField();
		myTitlePane.getChildren().addAll(idInstruction, idField);
		Scene editor= new Scene(myRoot);
		editor.getStylesheets().add("GAE.css");
		myRoot.setLeft(myAttributePane);
		myRoot.setRight(myImagePane);
		myRoot.setTop(myTitlePane);
		window= new Stage();
		window.setScene(editor);
		window.setTitle("Attribute Editor");
		window.show();
	}
	
	/**
	 * Adds the necessary attributes to the editor window
	 * If it is a block or enemy, add Basic Behavior Movement and Interaction
	 * If it is a player, add Basic and Movement 
	 */
	abstract void organizeEditor();
	
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
	 * This method calls the CustomElementSaver which creates an XML file out of the data
	 */
	abstract void saveData();
	
	protected String getElementID() {
		return idField.getText();
	}
	
}
