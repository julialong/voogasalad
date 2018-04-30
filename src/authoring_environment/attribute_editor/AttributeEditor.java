package authoring_environment.attribute_editor;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.TransformerException;

import authoring_environment.CustomElementSaver;
import authoring_environment.DataAlert;
import authoring_environment.TreeNode;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This class is the abstract class that all of the specific editors -- for block, for enemy and for player, 
 * inherit from 
 * 
 * @author Judi Sanchez Date started: April 3 2018
 *
 */
// TODO: get rid of strings in methods
public abstract class AttributeEditor implements DataAlert {

	private static final double IMAGE_WIDTH = 200;
	private static final double IMAGE_HEIGHT = 200;
	public static final String ENTER_ID = "Enter ID";

	protected VBox myAttributePane;
	protected VBox myImagePane;
	protected HBox myTitlePane;

	private TextField idField;
	protected List<Attribute> attributeList;

	private ImageView image;
	private Stage window;
	private File imageFile;
	private URI imageURI;

	/**
	 * 
	 */
	public AttributeEditor() {
		attributeList = new ArrayList<Attribute>();
		setUpEditorWindow();
		organizeEditor();
		myAttributePane.getChildren().add(new CloseAttributeEditorButton(this));

	}

	/*
	 * public AttributeEditor(GameElement element) { setUpEditorWindow();
	 * organizeEditor(); imageFile = new File("file:" + imagePath); imageURI =
	 * imageFile.toURI(); image = new ImageView("file:" + imagePath);
	 * image.setFitHeight(IMAGE_HEIGHT); image.setFitWidth(IMAGE_WIDTH);
	 * myImagePane.getChildren().add(image);
	 * 
	 * }
	 */

	/**
	 * Creates the BorderPane, Scene, and Stage and the different Boxes that go in
	 * the BorderPane Adds the AddImageButton to myImagePane
	 * TODO: rewrite this method
	 */
	private void setUpEditorWindow() {
		BorderPane myRoot = new BorderPane();
		myRoot.getStyleClass().add("attribute-editor");
		myAttributePane = new VBox();
		myImagePane = new VBox();
		myImagePane.getChildren().add(new AddImageButton(this));
		myTitlePane = new HBox();
		Label idInstruction = new Label(ENTER_ID);
		idField = new TextField();
		myTitlePane.getChildren().addAll(idInstruction, idField);
		Scene editor = new Scene(myRoot);
		editor.getStylesheets().add("GAE.css");
		myRoot.setLeft(myAttributePane);
		myRoot.setRight(myImagePane);
		myRoot.setTop(myTitlePane);
		window = new Stage();
		window.setScene(editor);
		window.setTitle("Attribute Editor");
		window.show();
	}

	/**
	 * This method opens the window that allows the user to upload an image file to
	 * be used as the image for a custom element
	 * 
	 * @throws MalformedURLException
	 */
	public void openFileChooser() throws MalformedURLException {
		myImagePane.getChildren().remove(image);
		Stage fileWindow = new Stage();
		FileChooser filechooser = new FileChooser();
		filechooser.getExtensionFilters()
				.add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
		imageFile = filechooser.showOpenDialog(fileWindow);
		imageURI = imageFile.toURI();
		URL imageURL = imageURI.toURL();
		image = new ImageView(imageURL.toString());
		image.setFitHeight(IMAGE_HEIGHT);
		image.setFitWidth(IMAGE_WIDTH);
		myImagePane.getChildren().add(image);

	}

	protected String getElementID() {
		return idField.getText();
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
		TreeNode tree = new TreeNode("Attributes");
		for (Attribute attribute : attributeList) {
			TreeNode attributeBranchNode = new TreeNode("name");
			tree.addChild(attributeBranchNode);
			Map<String, List<String>> attributeContentMap = attribute.getAttributeContent();
			Set<String> options = attributeContentMap.keySet();
			for (String option : options) {
				TreeNode optionNode = new TreeNode(option);
				attributeBranchNode.addChild(optionNode);
				List<String> optionParameters = attributeContentMap.get(option);
				for (String parameter : optionParameters) {
					TreeNode parameterNode = new TreeNode(parameter);
					optionNode.addChild(parameterNode);
				}
			}

		}
		try {
			CustomElementSaver saver = new CustomElementSaver(getElementID(), tree);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
