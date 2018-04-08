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

import authoring_environment.toolbars.buttons.AddImageButton;
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
public class AttributeEditor {

	private static final String ATTRIBUTE_RESOURCES = "resources/attributes";
	private static final double IMAGE_WIDTH = 200;
	private static final double IMAGE_HEIGHT = 200;
	private GameEntity gameElement;
	private HashMap<String, String> attributes;
	private List<ComboBox<String>> attributeBoxes;
	private BorderPane myRoot;
	private VBox myAttributePane;
	private VBox myImagePane;
	private HBox myTitlePane;
	private FileChooser filechooser;
	private ImageView image;

	public AttributeEditor(GameEntity element) {
		gameElement= element;
		setUpEditorWindow();
		attributes = loadAttributes();
		makeComboBoxList(attributes);
		organizeEditor();
		
	}

	private HashMap<String, String> loadAttributes() {
		HashMap<String, String> attributes = new HashMap<String, String>();
		ResourceBundle resources = ResourceBundle.getBundle(ATTRIBUTE_RESOURCES);
		Enumeration<String> attributeKeys = resources.getKeys();
		while (attributeKeys.hasMoreElements()) {
			String key = attributeKeys.nextElement();
			String type = resources.getString(key);
			attributes.put(key, type);
		}
		return attributes;
	}

	private void makeComboBoxList(HashMap<String, String> attributes) {
		attributeBoxes = new ArrayList<ComboBox<String>>();
		Set<String> categories = new HashSet<String>(attributes.values());
		for (String category : categories) {
			List<String> categoryAttributes = new ArrayList<String>();
			categoryAttributes.add(category);
			for (String key : attributes.keySet()) {
				if (attributes.get(key).equals(category)) {
					categoryAttributes.add(key);
				}
			}
			ComboBox<String> attributeBox = new ComboBox<String>();
			attributeBox.getItems().addAll(categoryAttributes);
			attributeBox.getSelectionModel().select(category);
			attributeBox.getStyleClass().add("combobox");
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
	
}
