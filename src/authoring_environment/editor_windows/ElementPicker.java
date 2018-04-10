package authoring_environment.editor_windows;

import java.io.Console;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Michael Acker
 * Date Started: 4/2/18
 *
 */

public class ElementPicker {
	private static final int ELEMENT_SIZE = 40;
	private static final int MAX_GRID_COLUMN_INDEX = 2;
	
	private GridPane myElementGrid;
	private ScrollPane myScrollPane;
	private ArrayList<ImageView> myElementImages;
	private String myTypeChoice;
	
	public ElementPicker() {
		
		myElementGrid = new GridPane();
		myScrollPane = new ScrollPane(myElementGrid);
		myScrollPane.setMinHeight(300);
		myScrollPane.setMaxWidth(200);
		myElementGrid.setPadding(new Insets(19,19,19,19));
		myElementGrid.setHgap(20);
		myElementGrid.setVgap(20);
		myElementImages = new ArrayList<ImageView>();
		updateTypeChoice();
		loadImages(myTypeChoice);
		setElements();
		
	}
	
	public ScrollPane getElementPane() {
		return myScrollPane;
	}
	
	private void loadImages(String type) {
		File folder = new File("./data/" + type);
		String[] imageExtensions = new String[]{".jpg", ".jpeg", ".png", ".gif"};
		File[] imageFiles = folder.listFiles(new FilenameFilter() {
			public boolean accept(File folder, String name) {
				for (String extension : imageExtensions) {
					if (name.endsWith(extension)) {
						return (true);
					}
				}
				return (false);
			}
		});
		for(File file : imageFiles) {
			Image image = new Image("file:data/" + myTypeChoice + "/" + file.getName(), 40, 40, true, true);
			PickableElement element = new PickableElement(image, myTypeChoice, file.getName());
			myElementImages.add(element);
		}
		
	}
	
	private void updateTypeChoice() {
		//TODO: Load type choice from combo box
		myTypeChoice = "ExampleElementPictures";
	}
	
	private void setElements() {
		int rowIndex = 0;
		int columnIndex = 0;
		for (ImageView image : myElementImages) {
			myElementGrid.add(image, columnIndex, rowIndex);
			columnIndex++;
			if (columnIndex > MAX_GRID_COLUMN_INDEX) {
				columnIndex = 0;
				rowIndex++;
			}
		}
		
	}

}
