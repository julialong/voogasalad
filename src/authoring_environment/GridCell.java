package authoring_environment;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Michael Acker
 * Start Date: 4/5/2018
 *
 */

public class GridCell extends HBox {
	
	private ImageView myCellView;
	private String myPath;
	
	public GridCell() {
		super();
		myCellView = new ImageView();
		this.getChildren().add(myCellView);
		setupDragAndDrop();
	}
	
	public GridCell(double spacing) {
		super(spacing);
		myCellView = new ImageView();
		this.getChildren().add(myCellView);
		setupDragAndDrop();
	}
	
	public ImageView getView() {
		return myCellView;
	}
	
	public Image getImage() {
		return myCellView.getImage();
	}
	
	public void setImage(Image image) {
		myCellView.setImage(image);
	}
	
	public void setImage(Image image, String path) {
		myPath = path;
		myCellView.setImage(new Image("file:data/" + myPath));
	}
	
	private void setupDragAndDrop() {
		GridCell myGridCell = this;
		
		myGridCell.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        if (event.getGestureSource() != myGridCell &&
		                event.getDragboard().hasImage()) {
		            event.acceptTransferModes(TransferMode.COPY);
		        }
		        
		        event.consume();
		    }
		});
		myGridCell.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           myGridCell.setImage(db.getImage(), db.getString());
		           success = true;
		        }
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		myGridCell.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != myGridCell &&
		                 event.getDragboard().hasImage()) {
		        	 myGridCell.setStyle("-fx-background-color: #99ebff;");
		         }
		                
		         event.consume();
		    }
		});
		myGridCell.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		    	myGridCell.setStyle("-fx-background-color: transparent;");
		    	myGridCell.setStyle("-fx-border-color: black;");

		        event.consume();
		    }
		});
		
	}
}
