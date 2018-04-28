package authoring_environment.editor_windows;

import authoring_environment.DocumentGetter;
import authoring_environment.toolbars.choosers.ElementPicker;

import org.w3c.dom.Document;

import javafx.scene.control.Tooltip;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;

/**
 * 
 * @author Michael Acker
 * Date Started: 4/2/2018
 *
 */
public class PickableElement extends ImageView implements DocumentGetter {
	private static final String ELEMENT_DATA_PATH = "./data/authoredElementData/";
	private static final int REQUESTED_WIDTH = 40;
	private static final int REQUESTED_HEIGHT = 40;

	private Image myImage;
	private String myType;
	private String myID;
	private Document myDataDoc;
	private ElementPicker myPicker;
	private boolean locked;

	public PickableElement(String ID, ElementPicker picker) {
		super();
		myID = ID;
		myPicker = picker;
		locked = false;
		Tooltip tip = new Tooltip(myID);
		Tooltip.install(this, tip);
		myDataDoc = parseElementXML(myID);
		String path = myDataDoc.getDocumentElement().getAttribute("ImageFile");
		String type = myDataDoc.getDocumentElement().getAttribute("GameEntity");
		myImage = new Image("file:" + path, REQUESTED_WIDTH, REQUESTED_HEIGHT, true, true);
		myType = type;
		this.setImage(myImage);
		this.setFitHeight(REQUESTED_HEIGHT);
		this.setFitWidth(REQUESTED_WIDTH);
		setupDragAndDrop();
		setupDoubleClick();
	}
	
	public String getType() {
		return myType;
	}
	
	private void setupDragAndDrop() {
		this.setOnDragDetected(e -> {
		        Dragboard db = startDragAndDrop(TransferMode.COPY);
		        ClipboardContent content = new ClipboardContent();
		        content.putString(myID);
		        content.putImage(myImage);
		        db.setContent(content);
		        e.consume();
		});
	}
	
	private void setupDoubleClick() {
		this.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				if (locked) {
					unlock();
					myPicker.unlockElement();
				} else {
					lock();
					myPicker.lockElement(myID);
				}
				
			}
		});
	}
	
	private void lock() {
		locked = true;
		this.setEffect(new InnerShadow(20 , Color.web("#99ebff") ));
	}
	
	public void unlock() {
		locked = false;
		this.setEffect(null);
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public String getID() {
		return myID;
	}

	/**
	 * TODO: why can't we have all of the parsing done by
	 * TODO: these methods, and return a constructed object?
	 * Gets the Document associated with a given ID
	 * @param ID is the ID of the object to get
	 * @return the XML Document associated with the ID
	 */
	private Document parseElementXML(String ID) {
        return getDocument(ID, ELEMENT_DATA_PATH);
    }
}
