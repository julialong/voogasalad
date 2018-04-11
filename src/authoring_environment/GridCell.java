package authoring_environment;

import org.w3c.dom.Document;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * 
 * @author Michael Acker
 * Start Date: 4/5/2018
 *
 */

public class GridCell extends HBox {
	
	private ImageView myCellView;
	private String myID;
	private String myType;
	private boolean selected;
	private ScrollingGrid myGrid;
	private int mySize;
	private Document myDataDoc;
	
	public GridCell(ScrollingGrid grid, int size) {
		super();
		myCellView = new ImageView();
		mySize = size;
		selected = false;
		myGrid = grid;
		myType = null;
		this.getChildren().add(myCellView);
		this.setMinHeight(mySize);
		this.setMaxWidth(mySize);
		this.setMaxHeight(mySize);
		this.setMinWidth(mySize);
		myCellView.setFitWidth(mySize);
		myCellView.setFitHeight(mySize);
		this.setStyle("-fx-border-color: black;");
		setupEvents();
	}
	
	public GridCell(double spacing) {
		super(spacing);
		myCellView = new ImageView();
		this.getChildren().add(myCellView);
		setupEvents();
		selected = false;
	}
	
	public ImageView getView() {
		return myCellView;
	}
	
	public Image getImage() {
		return myCellView.getImage();
	}
	
	public String getID() {
		return myID;
	}
	
	public int getSize() {
		return mySize;
	}
	
	public void setSize(int size) {
		mySize = size;
		this.setMinHeight(mySize);
		this.setMaxWidth(mySize);
		this.setMaxHeight(mySize);
		this.setMinWidth(mySize);
		myCellView.setFitWidth(mySize);
		myCellView.setFitHeight(mySize);
	}
	
	public void setImage(String ID) {
		myID = ID;
		myDataDoc = myGrid.parseElementXML(ID);
		String path = myDataDoc.getDocumentElement().getAttribute("ImageFile");
		myType = myDataDoc.getDocumentElement().getAttribute("GameEntity");
		myCellView.setImage(new Image("file:" + path));
	}
	
	public String getType() {
		return myType;
	}
	
	private void select() {
		selected = true;
		this.setEffect(new InnerShadow(mySize/2 , Color.web("#99ebff") ));
	}
	
	private void deselect() {
		selected = false;
		this.setEffect(null);
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void reset() {
		this.getChildren().clear();
		myCellView = new ImageView();
		this.getChildren().add(myCellView);
		myCellView.setFitHeight(mySize);
		myCellView.setFitWidth(mySize);
		myID = null;
		myType = null;
		deselect();
	}
	
	private void setupEvents() {
		GridCell myGridCell = this;
		
		myGridCell.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        if(selected) {
		        	deselect();
		        } else {
		        	select();
		        }
		    }
		});
		
		myGridCell.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        if (event.getGestureSource() != myGridCell &&
		                event.getDragboard().hasString()) {
		            event.acceptTransferModes(TransferMode.COPY);
		        }
		        
		        event.consume();
		    }
		});
		myGridCell.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasString()) {
		           myGrid.setCellImage(myGridCell, db.getString());
		           success = true;
		        }
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		myGridCell.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		         if (event.getGestureSource() != myGridCell &&
		                 event.getDragboard().hasString()) {
		        	 myGridCell.setStyle("-fx-background-color: #99ebff;");
		         }
		                
		         event.consume();
		    }
		});
		myGridCell.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    	myGridCell.setStyle("-fx-background-color: transparent;");
		    	myGridCell.setStyle("-fx-border-color: black;");

		        event.consume();
		    }
		});
		
	}
}
