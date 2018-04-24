package heads_up_display;

import javafx.scene.text.Text;

public class HudComponent extends Text implements Component{
    private double mouseX;
    private double mouseY;

    /**
     * Constructs a HudComponent
     * @param value
     */
    public HudComponent(String value){
        super(value);
        setDraggable();
        getStyleClass().add("text");
    }

    /**
     * Makes this component draggable
     */
    private void setDraggable() {
        setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });
        setOnMouseDragged(event ->{
            double deltaX = event.getSceneX() - mouseX;
            double deltaY = event.getSceneY() - mouseY;
            setLayoutX(getLayoutX() + deltaX);
            setLayoutY(getLayoutY() + deltaY);
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
            event.consume();
        });
    }

    /**
     * Returns the value that this component holds
     */
    @Override
    public String getComponent() {
        return getText();
    }

    /**
     * Sets the value held within this component
     *
     * @param value
     */
    @Override
    public void setComponent(String value) {
        setText(value);
    }
}

