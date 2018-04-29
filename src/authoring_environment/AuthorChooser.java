package authoring_environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author Judi Sanchez 
 * Date started: April 27 2018
 *
 */
public class AuthorChooser {
	
	private static final String GAMEDATA = "data/gameData/";
	
	private Stage myStage;
	private Scene myScene;
	private Pane myRoot;
	private ListView<String> myAuthorList;
	private String myAuthor;
	
	
	/**
	 * This constructor
	 */
	public AuthorChooser(Stage stage) {
		openChooserWindow(stage);
		
	}
	
	/**
	 * This method opens the window that allows the user to select their profile 
	 */
	private void openChooserWindow(Stage stage) {
		myRoot = new BorderPane();
		myScene = new Scene(myRoot);
		myStage = stage;
		myStage.setScene(myScene);
		myStage.show();
		
	}
	
	
	private void setAuthor(String author) {
		myAuthor = author;
	}
	
	private void openEditor() {
		
	}
	
	private void createNewAuthor() {
		String author = ""; // TEMPORARY 
		makeNewDirectory(author);
		
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
		ObservableList<String> listOfAuthors = FXCollections.observableArrayList((makeFolderList(folder)));
		ListView<String> listviewOfAuthors = new ListView<String>(listOfAuthors);
		myAuthorList = listviewOfAuthors;
		
	}
	
	private List<String> makeFolderList(File parentFolder){
		List<String> folderList = new ArrayList<String>();
		File[] folders = parentFolder.listFiles();
		for(File folder : folders) {
			folderList.add(folder.toString());
		}
		return folderList;
		
	}
	

}


