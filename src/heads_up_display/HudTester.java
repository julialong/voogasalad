package heads_up_display;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class HudTester extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Tester for HUD");
        Scene scene = createMainScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene createMainScene() {
        Group root = new Group();
        Scene scene = new Scene(root, 700, 700);
        scene.setFill(Paint.valueOf("RED"));
        HeadsUpDisplay hud = new Hud(900, 900);
        int id1 = hud.addComponent("Test 1", 300, 300);
        hud.addComponent("Test 2");
        root.getChildren().add(hud.getHUD());

        Button but = new Button("Here is a button");
        but.setOnAction(event ->{
            hud.updateComponent(id1, "Yayyyy it worked");
        });
        root.getChildren().add(but);

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
