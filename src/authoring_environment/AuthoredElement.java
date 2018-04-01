package authoring_environment;

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
    public void updateAttributes(Attribute attribute, boolean status);

    /**
     * Given the string of a file, uploads the image to be used for this
     * particular element
     * @param fileName is the name of the image file
     */
    public void uploadImage(String fileName);

    /**
     *  Adds the current element to the GameCreator grid
     * @param xPosition is the initial x position of the element on the grid
     * @param yPosition is the initial y position of the element on the grid
     */
    public void addToGrid(double xPosition, double yPosition);

    /**
     * Modifies the current element's position on the GameCreator grid
     * @param xPosition is the new x position
     * @param yPosition is the new y position
     */
    public void move(double xPosition, double yPosition);
}