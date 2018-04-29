package authoring_environment.attribute_editor;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BasicAttributeEditor implements AttributeGetter {
	
	private static final String BASIC = "Basic";
	private VBox myBasicAttributePane;
	private VBox myImagePane;
	private HBox myTitlePane;
	
	public BasicAttributeEditor(Stage window) {
		setupEditorWindow(window);
	}
	
	private void setupEditorWindow(Stage window) {
		BorderPane myRoot= new BorderPane();
		//myRoot.getStyleClass().add("attribute-editor");
		myBasicAttributePane = new VBox();
		myImagePane= new VBox();
		myTitlePane= new HBox();
		Scene editor= new Scene(myRoot);
		//editor.getStylesheets().add("GAE.css");
		//myBasicAttributePane.getStyleClass().add("attribute-pane");
		//myImagePane.getStyleClass().add("image-pane");
		//myTitlePane.getStyleClass().add("attribute-title-pane");
		myRoot.setLeft(myBasicAttributePane);
		myRoot.setRight(myImagePane);
		myRoot.setTop(myTitlePane);
		window.setScene(editor);
		window.setTitle("Attribute Editor");
		window.show();
	}
	
	private void setupBasicInputs(Pane myBasicAttributePane) {
		Map<String, List<String>> basicAttributesMap = loadAttributes(BASIC);
		Set<String> basicAttributes = basicAttributesMap.keySet();
		for (String attribute : basicAttributes) {
			Group groupToAdd = createInputBox(attribute);
			myBasicAttributePane.getChildren().addAll(groupToAdd);
		}
		
	}
	

}
