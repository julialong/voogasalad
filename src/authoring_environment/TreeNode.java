package authoring_environment;

import java.util.ArrayList;
import java.util.List;

/**
 * The TreeNode class contains the information of the node and its children.
 *
 * @author Julia Long
 * Date started: April 29 18
 */
public class TreeNode {

    private String myInfo;
    private List<TreeNode> myChildren;

    /**
     * Creates a new TreeNode
     * @param info is the information of the node
     */
    public TreeNode(String info) {
        myInfo = info;
        myChildren = new ArrayList<>();
    }

    /**
     * Creates a new empty TreeNode
     */
    public TreeNode() {
        this(null);
    }

    /**
     * Adds a child to the TreeNode
     * @param child is the child to add
     */
    public void addChild(TreeNode child) {
        myChildren.add(child);
    }

    /**
     * Gets the list of children of the current node
     * @return the current node's children
     */
    public List<TreeNode> getChildren() {
        return myChildren;
    }

    /**
     * Sets the info of the node
     * @param info is the info of the node
     */
    public void setInfo(String info) {
        myInfo = info;
    }

    /**
     * Gets the info of the node
     * @return the info of the string
     */
    public String getInfo() {
        return myInfo;
    }
}
