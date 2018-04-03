package authoring_environment;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Authored Element Creator allows for the user to create a new AuthoredElement.  
 * @author Judi Sanchez
 * Date started: April 2 2018
 *
 */
public class AuthoredElementCreator {
	
	private FileChooser filechooser;
	private File file;
	
	/**
	 * This constructor is called when the AddElementButton is pushed on the right-side toolbar
	 * It creates a FileChooser for the user to select an image file from their computer files
	 * @throws MalformedURLException
	 */
	public AuthoredElementCreator() throws MalformedURLException {
		file = openFileChooser();
		URI uri= file.toURI();
		URL url= uri.toURL();
		//Image test = new Image(file.getPath());
		Image image = new Image(url.toString());
		
	}
	
	private File openFileChooser() {
		Stage fileWindow= new Stage();
		filechooser = new FileChooser();
		File chosenFile= filechooser.showOpenDialog(fileWindow);
		return chosenFile;
		
	}
	

}
