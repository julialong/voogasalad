package authoring_environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import authoring_environment.editor_windows.EditorWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author Judi Sanchez 
 * Date started: April 27 2018
 *
 */
public class AuthorChooser {
	
	private static final String GAMEDATA = "data" + File.separator + "gameData" + File.separator;
	private static final String SLASH = File.separator;
	private static final int ONE = 1;
	private static final String NEW_AUTHOR = "Create a new authoring profile";
	private static final String NEW_AUTHOR_INST  = "Enter name of new profile: ";
	private static final String SUBMIT = "ENTER";
	private static final String START = "START";
	private static final String TITLE = "CHOOSE YOUR PROFILE";
	
	private Stage myStage;
	private Scene myScene;
	private BorderPane myRoot;
	private VBox leftPane;
	private VBox rightPane;
	private ListView<String> myAuthorList;
	private String myAuthor;
	private ObservableList<String> listOfAuthors;
	
	
	/**
	 * This constructor
	 */
	public AuthorChooser(Stage stage) {
		openChooserWindow(stage);
		addAuthorList();
		addSubmitButton();
		
	}
	
	/**
	 * This method opens the window that allows the user to select their profile 
	 */
	private void openChooserWindow(Stage stage) {
		myRoot = new BorderPane();
		myScene = new Scene(myRoot);
		myScene.getStylesheets().add("GAE.css");
		myRoot.getStyleClass().add("author-chooser");
		myStage = stage;
		myStage.setScene(myScene);
		myStage.show();
		VBox topPane = new VBox();
		// TODO: CSS Styling
		topPane.getChildren().add(new Text(TITLE));
		leftPane = new VBox();
		rightPane = new VBox();
		leftPane.setSpacing(50);
		myRoot.setRight(rightPane);;
		myRoot.setLeft(leftPane);
		myRoot.setTop(topPane);
		
	}
	
	
	private void setAuthor(String author) {
		if(author.equals(NEW_AUTHOR)) {
			if(rightPane.getChildren().isEmpty()) {
				createNewAuthor();
			}
		}
		else {
			myAuthor = author;
		}
	}
	
	private void openEditor() {
		myStage.close();
		Stage newStage = new Stage();
		EditorWindow editor = new EditorWindow(newStage);
		editor.setAuthor(myAuthor);
		
	}
	
	private void createNewAuthor() {
		Label instruction = new Label(NEW_AUTHOR_INST);
		TextField box = new TextField();
		Button enter = new Button(SUBMIT);
		rightPane.getChildren().add(instruction);
		rightPane.getChildren().add(box);
		rightPane.getChildren().add(enter);
		enter.setOnMouseClicked(e-> {
			listOfAuthors.add(box.getText());
			makeNewDirectory(box.getText());
			rightPane.getChildren().remove(instruction);
			rightPane.getChildren().remove(box);
			rightPane.getChildren().remove(enter);
			
		});
		
	}
	
	/**
	 * When the user chooses to create a new authoring environment profile, this method is called to create a new folder
	 * for them under gameData
	 * @param authorName is the name of the new profile that the user is creating 
	 */
	private void makeNewDirectory(String authorName) {
		File directory = new File(GAMEDATA + authorName);
		directory.mkdir();
	}
	
	private void addAuthorList(){
		File folder = new File(GAMEDATA);
		listOfAuthors = FXCollections.observableArrayList((makeFolderList(folder)));
		listOfAuthors.add(NEW_AUTHOR);
		ListView<String> listviewOfAuthors = new ListView<String>(listOfAuthors);
		myAuthorList = listviewOfAuthors;
		myAuthorList.setOnMouseClicked(e-> {
			setAuthor(myAuthorList.getSelectionModel().getSelectedItem());
		});
		leftPane.getChildren().add(myAuthorList);
		
	}
	
	private List<String> makeFolderList(File parentFolder){
		List<String> folderList = new ArrayList<String>();
		File[] folders = parentFolder.listFiles();
		for(File folder : folders) {
			folderList.add(folder.toString().substring(folder.toString().lastIndexOf(SLASH) + ONE));
		}
		return folderList;
		
	}
	
	private void addSubmitButton() {
		Button start = new Button(START);
		start.setOnMouseClicked(e-> {
		if(! (myAuthor == null)) {
			openEditor();
		}});
		leftPane.getChildren().add(start);
	}
	

}


