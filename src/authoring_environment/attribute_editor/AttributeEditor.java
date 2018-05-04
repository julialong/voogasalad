package authoring_environment.attribute_editor;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.TransformerException;

import authoring_environment.CustomElementSaver;
import authoring_environment.DataAlert;
import authoring_environment.TreeNode;
import javafx.scene.Scene;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/** TODO: Remove strings from methods
 * This class is the abstract class for all of the specific editors -- for
 * block, for enemy and for player It has methods for opening boxes and text
 * inputs for user manipulation, and for saving the contents of the inputs to
 * XML file
 * 
 * @author Judi Sanchez Date started: April 3 2018
 *
 */
public abstract class AttributeEditor implements DataAlert {

	private static final double IMAGE_WIDTH = 200;
	private static final double IMAGE_HEIGHT = 200;
	private static final String ID = "ID";
	private static final String CUSTOM_IMAGES_FOLDER = "data/authoredElementImages/";
	private static final String SLASH = File.separator;
	private static final String IMAGE_FILE = "ImageFile";
	private static final String BASIC = "Basic";
	private static final String STYLESHEET = "GAE.css";
	private static final String EDITOR_STYLESHEET = "attribute-editor";
	private static final String ATTRIBUTES = "Attributes";
	private static final String TITLE = "Attribute Editor";
	


	protected VBox myAttributePane;
	protected VBox myImagePane;
	protected HBox myTitlePane;
	protected List<Attribute> attributeList;
	protected String myType;

	private Stage window;
	private String elementID;
	private File imageFile;
	private URI imageURI;
	private URL imageURL;
	private ImageView image;

	/**
	 * This is the constructor for opening the attribute editor for a brand new game
	 * entity
	 */
	public AttributeEditor() {
		attributeList = new ArrayList<Attribute>();
		setUpEditorWindow();
		organizeEditor();
		myAttributePane.getChildren().add(new CloseAttributeEditorButton(this));

	}

	/**
	 * Creates the BorderPane, Scene, and Stage and the different Boxes that go in
	 * the BorderPane Adds the AddImageButton to myImagePane 
	 * method
	 */
	private void setUpEditorWindow() {
		BorderPane myRoot = new BorderPane();
		myRoot.getStyleClass().add(EDITOR_STYLESHEET);
		myAttributePane = new VBox();
		myImagePane = new VBox();
		myImagePane.getChildren().add(new AddImageButton(this));
		myTitlePane = new HBox();
		Scene editor = new Scene(myRoot);
		editor.getStylesheets().add(STYLESHEET);
		myRoot.setLeft(myAttributePane);
		myRoot.setRight(myImagePane);
		myRoot.setTop(myTitlePane);
		window = new Stage();
		window.setScene(editor);
		window.setTitle(TITLE);
		window.show();
	}

	/**
	 * Adds the necessary attributes to the editor window If it is a block or enemy,
	 * add Basic Behavior Movement and Interaction If it is a player, add Basic and
	 * Movement
	 */
	abstract void organizeEditor();

	/**
	 * This method calls the CustomElementSaver which creates an XML file out of the
	 * data. It creates a Tree with the Maps for each attribute, and it passes the
	 * elementID and the tree to the CustomElementSaver.
	 */
	protected void saveData() {
		TreeNode tree = new TreeNode(ATTRIBUTES);
		for (Attribute attribute : attributeList) {
			TreeNode attributeBranchNode = new TreeNode(attribute.returnName());
			tree.addChild(attributeBranchNode);
			Map<String, List<String>> attributeContentMap = attribute.getAttributeContent();
			Set<String> options = attributeContentMap.keySet();
			for (String option : options) {
				TreeNode optionNode = new TreeNode(option);
				attributeBranchNode.addChild(optionNode);
				List<String> optionParameters = attributeContentMap.get(option);
				for (String parameter : optionParameters) {
					if (option.equals(ID)) {
						elementID = parameter;
					}
					TreeNode parameterNode = new TreeNode(parameter);
					optionNode.addChild(parameterNode);
				}
			}

		}
		addImageToTree(tree);
		addTypeToTree(tree);
		try {
			CustomElementSaver saver = new CustomElementSaver(elementID, tree);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		window.close();
	}

	/**
	 * This method opens the window that allows the user to upload an image file to
	 * be used as the image for a custom element
	 * 
	 * @throws MalformedURLException
	 * TODO : Remove magic strings 
	 */
	public void openFileChooser() throws MalformedURLException {
		myImagePane.getChildren().remove(image);
		Stage fileWindow = new Stage();
		FileChooser filechooser = new FileChooser();
		filechooser.getExtensionFilters()
				.add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
		imageFile = filechooser.showOpenDialog(fileWindow);
		imageURI = imageFile.toURI();
		imageURL = imageURI.toURL();
		image = new ImageView(imageURL.toString());
		image.setFitHeight(IMAGE_HEIGHT);
		image.setFitWidth(IMAGE_WIDTH);
		myImagePane.getChildren().add(image);

	}

	/**
	 * This method is used to copy the file path from the image that is uploaded
	 * with the FileChooser to a new file path that has the same name as the ID that
	 * was given by the user to the new game entity
	 * 
	 * @return the file path for the copied image
	 */
	private String copyImage() {
		Path source = Paths.get(imageURI);
		Path target = Paths.get(CUSTOM_IMAGES_FOLDER + myType + SLASH + elementID
				+ imageFile.getAbsolutePath().substring(imageFile.getAbsolutePath().lastIndexOf(".")));
		try {
			Files.copy(source, target);
		} catch (IOException e) {
			saveAlert(e);
		}
		return target.toString();

	}

	/**
	 * This method is used to add the image to the tree of attributes for a game
	 * entity
	 * 
	 * @param tree
	 *            is the tree to which we are adding the file path for the image
	 */
	private void addImageToTree(TreeNode tree) {
		String filePath = copyImage();
		List<TreeNode> attributes = tree.getChildren();
		for (TreeNode attribute : attributes) {
			if (attribute.getInfo().equals(BASIC)) {
				TreeNode imagePathNode = new TreeNode(IMAGE_FILE);
				attribute.addChild(imagePathNode);
				imagePathNode.addChild(new TreeNode(filePath));
			}
		}

	}
	
	private void addTypeToTree(TreeNode tree) {
		TreeNode entityType= new TreeNode("GameEntity");
		tree.addChild(entityType);
		entityType.addChild(new TreeNode(myType));
	}

}
