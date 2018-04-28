package heads_up_display;

/**
 * Components in the heads up display
 *
 * @Author Dorian Barber
 */
public interface Component {

    /**
     * Returns the value that this component holds
     */
    public String getComponent();

    /**
     * Sets the value held within this component
     */
    public void setComponent(String value);
}
