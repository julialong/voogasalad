package game_player;

import authoring_environment.EditorWindow;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class is designed to allow the user to either open
 * the game authoring environment or the game chooser application
 * depending on their choice.
 *
 * @Author Dorian Barber
 */
public class OverViewDriver extends Application {

    /**
     * Starts the main application
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SuperSaladSquad");
        Scene scene = new Scene(getScene(primaryStage));
        scene.getStylesheets().add("../data/styling/overview.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * Creates the the user interface
     * @return the pane to be displayed toward the user
     *
     * TODO: Refactor
     */
    private Pane getScene(Stage stage){
        Pane pane = new Pane();
        VBox container = new VBox();
        HBox buttonContainer = new HBox();
        buttonContainer.getStyleClass().add("hbox");
        Button gae = new Button("Game Authoring Environment");
        gae.setOnAction(event -> {
            new EditorWindow(stage);
        });

        Button gp = new Button("Game Player");
        gp.setOnAction(event -> {
            new VoogaChooser(stage);
        });

        buttonContainer.getChildren().add(gae);
        buttonContainer.getChildren().add(gp);

        Text title = new Text("Welcome to Super Salad Squad");
        title.setFont(new Font(30));
        Text t = new Text("Choose an application to open");

        container.getChildren().add(title);
        container.getChildren().add(t);
        container.getChildren().add(buttonContainer);
        container.getStyleClass().add("vbox");
        pane.getChildren().add(container);
        return pane;
    }
    
	/**
	 * Runs the SSS application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
