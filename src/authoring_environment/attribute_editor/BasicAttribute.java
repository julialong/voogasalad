package authoring_environment.attribute_editor;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BasicAttribute extends Attribute implements AttributeGetter {
	
	private static final String BASIC = "Basic";
	private VBox myBasicAttributePane;
	private VBox myImagePane;
	private HBox myTitlePane;
	
	public BasicAttribute(Stage window) {
		setupEditorWindow(window);
	}


	/**
	 * Create all textfield inputs with a label that will specify the purpose of the textfield.
	 * i.e. label = X Dimension => textfield = (some integer which will represent the x dimension)
	 *
	 * @param myBasicAttributePane
	 */
	@Override
	public void setupInputs(Pane myBasicAttributePane, String attributeType) {
		List<String> basicAttributesOptions = super.loadAttributes(attributeType);
		VBox container = new VBox();
		for(String option : basicAttributesOptions){
			HBox inputLine = new HBox();
			Label instruction = new Label(option);
			TextField input = new TextField();
			inputLine.getChildren().addAll(instruction, input);
			container.getChildren().add(inputLine);
		}
		myBasicAttributePane.getChildren().add(container);
	}

	private void setupEditorWindow(Stage window) {
		BorderPane myRoot= new BorderPane();
		myBasicAttributePane = new VBox();
		myImagePane= new VBox();
		myTitlePane= new HBox();
		Scene editor= new Scene(myRoot);
		myRoot.setLeft(myBasicAttributePane);
		myRoot.setRight(myImagePane);
		myRoot.setTop(myTitlePane);
		window.setScene(editor);
		window.setTitle("Attribute Editor");
		window.show();
	}
}
