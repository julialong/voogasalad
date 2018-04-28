package authoring_environment.grid;

import engine.entity.GameEntity;
import javafx.scene.PointLight;
import org.w3c.dom.Document;

import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.awt.*;

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
	private int myXDim;
	private int myYDim;
	private Document myDataDoc;
	private Point myPosition;
	private GameEntity myObject;
	private static final int DEFAULT_DIM = 1;
	
	public GridCell(ScrollingGrid grid, int size, int x, int y) {
		super();
		myCellView = new ImageView();
		mySize = size;
		myXDim = DEFAULT_DIM;
		myYDim = DEFAULT_DIM;
		selected = false;
		myGrid = grid;
		myType = null;
		myPosition = new Point(x,y);
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

	public void setObject(GameEntity object) {
		myObject = object;
	}

	public GameEntity getObject() {
		return myObject;
	}
	
	public int getSize() {
		return mySize;
	}

	public Point getPosition() {
		return myPosition;
	}
	
	public void setSize(int size) {
		mySize = size;
		this.setMinHeight(mySize*myYDim);
		this.setMaxWidth(mySize*myXDim);
		this.setMaxHeight(mySize*myYDim);
		this.setMinWidth(mySize*myXDim);
		myCellView.setFitWidth(mySize*myXDim);
		myCellView.setFitHeight(mySize*myYDim);
	}
	
	public void setImage(String ID) {
		myID = ID;
		myDataDoc = myGrid.parseElementXML(ID);
		String path = myDataDoc.getDocumentElement().getAttribute("ImageFile");
		myType = myDataDoc.getDocumentElement().getAttribute("GameEntity");
		myCellView.setImage(new Image("file:" + path));
		//TODO: Get Dimensions from file
		this.setSize(mySize);
		if(selected) {deselect();}
	}
	
	public String getType() {
		return myType;
	}
	
	public int getXDim() {
		return myXDim;
	}
	
	public int getYDim() {
		return myYDim;
	}
	
	private void select() {
		selected = true;
		this.setEffect(new InnerShadow(mySize/2 , Color.web("#99ebff") ));
	}
	
	void deselect() {
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
		myXDim = DEFAULT_DIM;
		myYDim = DEFAULT_DIM;
		setSize(mySize);
		myID = null;
		myType = null;
		myObject = null;
		deselect();
	}

	private void setupEvents() {
		this.setOnMouseClicked(e -> handleClicked());
		this.setOnDragOver(this::handleDragOver);
		this.setOnDragDropped(this::handleDragDropped);
		this.setOnDragEntered(this::handleDragEntered);
		this.setOnDragExited(this::handleDragExited);
	}

	private void handleClicked() {
		final Clipboard clipboard = Clipboard.getSystemClipboard();
	    if (clipboard.hasString()) {
	    	myGrid.setCellElement(this, clipboard.getString());
	    } else {
	    	if(selected) {
				deselect();
			} else {
				select();
			}
	    }
	}

	private void handleDragOver(DragEvent event) {
		if (event.getGestureSource() != this &&
				event.getDragboard().hasString()) {
			event.acceptTransferModes(TransferMode.COPY);
		}
		event.consume();
	}

	private void handleDragEntered(DragEvent event) {
		if (event.getGestureSource() != this &&
				event.getDragboard().hasString()) {
			this.setStyle("-fx-background-color: #99ebff;");
		}
		event.consume();
	}

	private void handleDragExited(DragEvent event) {
		this.setStyle("-fx-background-color: transparent;");
		this.setStyle("-fx-border-color: black;");
		event.consume();
	}

	private void handleDragDropped(DragEvent event) {
		Dragboard db = event.getDragboard();
		boolean success = false;
		if (db.hasString()) {
			myGrid.setCellElement(this, db.getString());
			success = true;
			deselect();
		}
		event.setDropCompleted(success);
		event.consume();
	}
}
