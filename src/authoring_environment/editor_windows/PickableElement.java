package authoring_environment.editor_windows;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class PickableElement extends ImageView {
	private static final String ELEMENT_DATA_PATH = "./data/authoredElementData/";
	private static final int REQUESTED_WIDTH = 40;
	private static final int REQUESTED_HEIGHT = 40;

	private Image myImage;
	private String myType;
	private String myID;
	private Document myDataDoc;

	public PickableElement(String ID) {
		super();
		myID = ID;
		myDataDoc = parseElementXML(myID);
		String path = myDataDoc.getDocumentElement().getAttribute("ImageFile");
		String type = myDataDoc.getDocumentElement().getAttribute("GameEntity");
		myImage = new Image("file:" + path, REQUESTED_WIDTH, REQUESTED_HEIGHT, true, true);
		myType = type;
		this.setImage(myImage);
		this.setFitHeight(REQUESTED_HEIGHT);
		this.setFitWidth(REQUESTED_WIDTH);
		setupDragAndDrop();
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
	
	private Document parseElementXML(String ID) {
		// TODO: get rid of this duplicated code
		try {
		File file = new File(ELEMENT_DATA_PATH + ID + ".xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		db = dbf.newDocumentBuilder();
		Document elementDoc = db.parse(file);
		return elementDoc;
		} catch (Exception e) {
		    e.printStackTrace();
		    // TODO: handle exceotion
		    return null;
	    }
	}
}
