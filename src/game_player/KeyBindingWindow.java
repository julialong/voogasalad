package game_player;

import java.util.Enumeration;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KeyBindingWindow {
	private Stage myStage = new Stage();
    private BorderPane myView =  new BorderPane();
//    private JSONtoObject reader = new GameFileReader();
//    private ListView<String> keyList = new ListView<>();
    private VoogaGameView myGameView;

    public final ResourceBundle RESOURCEKEYS = ResourceBundle.getBundle("engine.controls.resources/Controls");

    
    public KeyBindingWindow(VoogaGameView gv){
        myGameView = gv;
    	myView.setMinWidth(550);
        setUpStage();
    }

    /**
     * Sets up the stage for the
     */
    private void setUpStage(){
        myStage.setTitle("Change Key Bindings");
        //myStage.setMaximized(true);
        myStage.setMinWidth(600);
        Scene scene = new Scene(this.displayChoices(),450,450);
        scene.getStylesheets().add("../data/styling/styleSheet.css");
        myStage.setScene(scene);
        myStage.show();
    }


    /**
     * This will represent the extensive list of developed games that the
     * user can choose. Choosing a game will prompt the sendToGame method
     */
    public BorderPane displayChoices() {
    	Enumeration<String> enumeration = RESOURCEKEYS.getKeys();
    	VBox container = new VBox();
    	while (enumeration.hasMoreElements()) {
    		HBox tempContainer = new HBox();
        	String propKey = enumeration.nextElement();
        	String val = RESOURCEKEYS.getString(propKey);
        	tempContainer.getChildren().add(new Label(propKey));
        	TextField textField = new TextField();
        	textField.setPromptText(val);
        	Button change = new Button("Change");
        	change.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                	//TODO: add error check to make sure there is actually a keyCode for what is passed in
                	if(textField.getText() != null && textField.getText() != "") {
                		myGameView.changeBinding(propKey,KeyCode.valueOf(textField.getText().toString().toUpperCase()));
                		//displayChoices();
                	}
                }
            });
        	tempContainer.getChildren().addAll(textField, change);
        	container.getChildren().add(tempContainer);
        }
        myView.setCenter(container);
        return myView;
    }
}

