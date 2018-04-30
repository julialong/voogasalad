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
	private static final String CUSTOM_IMAGES_FOLDER = "data/authoredElementImages/";
	private static final String  SLASH = "/";
	private static final String DEFAULT = "1";
	
	protected VBox myAttributePane;
	protected VBox myImagePane;
	protected HBox myTitlePane;
	

	private GameElement gameElement;
	private String elementID; 
	private Map<String, List<String>> attributes;
	private Map<String, String> chosenAttributes; 
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
		//gameElement= new GameElement();
		organizeEditor();
		
	}
	
	public AttributeEditor(GameElement element) {
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
		
	}

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
	 * This method triggers the gameElement updateAttributes method which saves attributes for an element
	 * to an XML file
	 */
	/*public void saveChanges() {
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
	}*/
	
	abstract void saveData();
	
}
