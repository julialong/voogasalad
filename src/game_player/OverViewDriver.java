package game_player;

import authoring_environment.EditorWindow;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OverViewDriver extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SuperSaladSquad");
        Scene scene = new Scene(getScene(primaryStage));
        scene.getStylesheets().add("../data/styling/overview.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private Pane getScene(Stage stage){
        Pane pane = new Pane();
        HBox container = new HBox();
        Button gae = new Button("Game Authoring Environment");
        gae.setOnAction(event -> {
            new EditorWindow(stage);
        });

        Button gp = new Button("Game Player");
        gp.setOnAction(event -> {
            new VoogaChooser(stage);
        });

        container.getChildren().add(gae);
        container.getChildren().add(gp);
        pane.getChildren().add(container);
        return pane;
    }
}
