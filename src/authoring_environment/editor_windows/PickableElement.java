package authoring_environment.editor_windows;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class PickableElement extends ImageView{
	private Image myImage;

	public PickableElement(Image image) {
		super(image);
		this.setFitHeight(40);
		this.setFitWidth(40);
		myImage = this.getImage();
		setupDragAndDrop();
	}
	
	private void setupDragAndDrop() {
		this.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
		        Dragboard db = startDragAndDrop(TransferMode.COPY);
		        ClipboardContent content = new ClipboardContent();
		        content.putImage(myImage);
		        db.setContent(content);
		        event.consume();
		    }
		});
		
	}
}
