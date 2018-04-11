package authoring_environment.editor_windows;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class PickableElement extends ImageView {
	private static final String ELEMENT_DATA_PATH = "./data/authoredElementData/";
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
		myImage = new Image("file:/" + path);
		myType = type;
		this.setImage(myImage);
		System.out.println(myImage.getHeight());
		System.out.println(this.getImage().getHeight());
		this.setFitHeight(40);
		this.setFitWidth(40);
		setupDragAndDrop();
	}
	
	public String getType() {
		return myType;
	}
	
	private void setupDragAndDrop() {
		this.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
		        Dragboard db = startDragAndDrop(TransferMode.COPY);
		        ClipboardContent content = new ClipboardContent();
		        content.putString(myID);
		        db.setContent(content);
		        event.consume();
		    }
		});
	}
	
	private Document parseElementXML(String ID) {
		try {
		File file = new File(ELEMENT_DATA_PATH + ID + ".xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		db = dbf.newDocumentBuilder();
		Document elementDoc = db.parse(file);
		return elementDoc;
		} catch (Exception e) {
		    e.printStackTrace();
		    return null;
	    }
	}
}
