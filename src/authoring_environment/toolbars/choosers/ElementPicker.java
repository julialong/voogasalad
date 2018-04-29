package authoring_environment.toolbars.choosers;

import java.io.Console;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import authoring_environment.editor_windows.EditorWindow;
import authoring_environment.editor_windows.PickableElement;
import authoring_environment.toolbars.RightBar;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Michael Acker
 * Date Started: 4/2/18
 *
 */

public class ElementPicker {
	private static final int MAX_GRID_COLUMN_INDEX = 2;
	private static final int PADDING  = 19;
	private static final int MIN_HEIGHT = 300;
	private static final int MAX_WIDTH = 200;
	private static final int GAP = 20;

	private GridPane myElementGrid;
	private ScrollPane myScrollPane;
	private ArrayList<PickableElement> myElementImages;
	private String myTypeChoice;
	private RightBar myRightBar;
	
	public ElementPicker(RightBar bar) {
		
		myRightBar = bar;
		myElementGrid = new GridPane();
		myScrollPane = new ScrollPane(myElementGrid);
		myScrollPane.setMinHeight(MIN_HEIGHT);
		myScrollPane.setMaxWidth(MAX_WIDTH);
		myElementGrid.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
		myElementGrid.setHgap(GAP);
		myElementGrid.setVgap(GAP);
		myElementImages = new ArrayList<PickableElement>();
		loadImages(myTypeChoice);
		setElements();
		Clipboard clipboard = Clipboard.getSystemClipboard();
		clipboard.clear();
	}
	
	public ScrollPane getElementPane() {
		return myScrollPane;
	}
	
	private void loadImages(String type) {
		myElementImages.clear();
		if (myTypeChoice != null) {
			File folder = new File("./data/authoredElementImages/" + myTypeChoice + "/");
			String[] imageExtensions = new String[]{".jpg",".jpeg",".png",".gif"};
			File[] imageFiles = folder.listFiles((folder1, name) -> {
	            for (String extension : imageExtensions) {
	                if (name.endsWith(extension)) {
	                    return (true);
	                }
	            }
	            return (false);
	        });
			for(File file : imageFiles) {
				String fileName = file.getName();
				String ID = fileName.substring(0, fileName.lastIndexOf('.'));
				PickableElement element = new PickableElement(ID, this);
				myElementImages.add(element);
			}
		}
	}
	
	public void updateTypeChoice(String type) {
		myTypeChoice = type;
		loadImages(myTypeChoice);
		myElementGrid.getChildren().clear();
		setElements();
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
	
	public void lockElement(String ID) {
		for (PickableElement element : myElementImages) {
			if (element.isLocked() && element.getID() != ID) {
				element.unlock();
			}
		}
		Clipboard clipboard = Clipboard.getSystemClipboard();
		clipboard.clear();
		ClipboardContent content = new ClipboardContent();
	    content.putString(ID);
	    clipboard.setContent(content);
	}
	
	public void unlockElement() {
		Clipboard clipboard = Clipboard.getSystemClipboard();
		clipboard.clear();
	}

}
