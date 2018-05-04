package game_player;

import authoring_environment.AuthorChooser;
import authoring_environment.editor_windows.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This class is designed to allow the user to either open
 * the game authoring environment or the game chooser application
 * depending on their choice.
 *
 * @Author Dorian Barber
 */
public class OverViewDriver extends Application {

    private ResourceBundle rb;

    /**
     * Starts the main application
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        rb = ResourceBundle.getBundle("game_player.resources.overview");
        primaryStage.setTitle(getResourceValue("stageTitle"));
        Scene scene = new Scene(getScene(primaryStage));
        scene.getStylesheets().add(getResourceValue("sceneStyle"));
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("./game.player.styling/game_icon.png"));
        primaryStage.show();
    }


    /**
     * Runs the SSS application
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Creates the the user interface by adding all components and
     * styling to it. The return value is the pane which represents the
     * scene to be displayed.
     */
    private Pane getScene(Stage stage){
        Pane pane = new Pane();
        VBox container = new VBox();
        HBox buttonContainer = new HBox();
        buttonContainer.getStyleClass().add(getResourceValue("hboxStyle"));
        setButtonActions(buttonContainer, stage);
        createText(container);
        container.getChildren().add(buttonContainer);
        container.getStyleClass().add(getResourceValue("vboxStyle"));
        pane.getChildren().add(container);
        return pane;
    }


    /**
     * Returns the information withheld in the overview.properties file
     * The parameter is a string which represents the key for the properties file.
     * If either the key is invalid or the value in the properties file is formatted
     * incorrectly an alert is shown and the program closes after.
     */
	private String getResourceValue(String key){
	    try{
	        return rb.getString(key);
        }
        catch (NullPointerException|MissingResourceException|ClassCastException e){
            Alert propertiesError = new Alert(Alert.AlertType.ERROR);
            propertiesError.setHeaderText(null);
            propertiesError.setContentText("Either the properties file or the code is corrupt. \n Please review the overview.properties file.");
            propertiesError.showAndWait();
            try {
                Platform.exit();
            } catch (Exception e1) { }
            return "";
        }
    }


    /**
     * Creates and adds the buttons to the HBox.
     * This method also sets the action for each of the buttons.
     */
    private void setButtonActions(HBox buttonContainer, Stage stage){
        Button gae = new Button(getResourceValue("gaeText"));
        gae.setOnAction(event -> {
            new AuthorChooser(new Stage());
            stage.close();
        });
        Button gp = new Button(getResourceValue("gpText"));
        gp.setOnAction(event -> {
            new VoogaChooser(new Stage());
            stage.close();
        });
        buttonContainer.getChildren().add(gae);
        buttonContainer.getChildren().add(gp);
    }


    /**
     * Creates the title texts for this class.
     * The text is then added to the VBox
     */
    private void createText(VBox container){
        Text title = new Text(getResourceValue("screenTitle"));
        title.getStyleClass().add(getResourceValue("titleStyle"));
        Text t = new Text(getResourceValue("subText"));
        container.getChildren().add(title);
        container.getChildren().add(t);
    }
}
