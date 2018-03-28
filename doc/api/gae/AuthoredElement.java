/**
 * The AuthoredElement interface is implemented by every element that
 * can be inserted into the GameCreator environment
 *
 * @author julialong, judithsanchez
 */
public interface AuthoredElement {


    /**
     * Updates the current state of the element by adding or removing an Attribute
     * @param attribute is the attribute to modify
     * @param status is true if the attribute should be added, false otherwise
     */
    public abstract void updateAttributes(Attribute attribute, boolean status);

    /**
     * Given the string of a file, uploads the image to be used for this
     * particular element
     * @param fileName is the name of the image file
     */
    public abstract void uploadImage(String fileName);

    /**
     * Adds the current element to the GameCreator grid
     */
    public abstract void addToGrid();

    /**
     * Modifies the current element's position on the GameCreator grid
     * @param xPosition is the new x position
     * @param yPosition is the new y position
     */
    public abstract void move(double xPosition, double yPosition);
}