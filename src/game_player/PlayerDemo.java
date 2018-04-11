package game_player;

import engine.entity.Player;
import javafx.scene.Node;
import javafx.scene.image.ImageView;


public class PlayerDemo extends Player {
    private ImageView icon = new ImageView("../data/ExampleElementPictures/Oak_Sapling.png");

    public PlayerDemo(){
        super();
        this.setFrictionConstant(20);
    }

    public Node getIcon(){
        icon.setX(100);
        icon.setY(100);
        return icon;
    }
}
