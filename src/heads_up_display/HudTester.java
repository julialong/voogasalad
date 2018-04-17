package heads_up_display;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
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
        HeadsUpDisplay hud = new Hud(700, 700);
        hud.addComponent("Test 1", 300, 300);
        hud.addComponent("Test 2");
        root.getChildren().add(hud.getHUD());
        root.getChildren().add(new Text("HI"));
        return scene;
    }

    public static void main(String[] args){
        launch(args);
    }
}
