package authoring_environment;

import java.util.ArrayList;
import java.util.List;

/**
 * The TreeNode class contains the information of the node and its children.
 *
 * @author Julia Long
 */
public class TreeNode {

    private String myInfo;
    private List<TreeNode> myChildren;

    public TreeNode(String info) {
        myInfo = info;
        myChildren = new ArrayList<>();
    }

    public TreeNode() {
        this("");
    }

    public void addChild(TreeNode child) {
        myChildren.add(child);
    }

    public List<TreeNode> getChildren() {
        return myChildren;
    }

    public void setInfo(String info) {
        myInfo = info;
    }

    public String getInfo() {
        return myInfo;
    }
}
