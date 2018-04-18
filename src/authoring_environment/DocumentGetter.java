package authoring_environment;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * The DocumentGetter interface allows for the retrieval of an XML document
 * from any class. This code was appearing in several classes, and was refactored
 * to prevent code duplication.
 *
 * @author Michael Acker, Judith Sanchez, Julia Long
 */
public interface DocumentGetter {

    String XML = ".xml";

    /**
     * Gets the necessary document
     * @param ID is the ID of the object to retrieve
     * @param elementDataPath is the filepath to the folder
     * @return the retrieved Document
     */
    default Document getDocument(String ID, String elementDataPath){
        try {
            File file = new File(elementDataPath + ID + XML);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;
            db = dbf.newDocumentBuilder();
            return db.parse(file);
        } catch (Exception e) {
            //throw new InvalidElementException();
            return null;
        }
    }

    /**
     * Thrown when the file requested in invalid.
     */
    class InvalidElementException extends Exception {

    }
}
