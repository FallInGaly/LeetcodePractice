package datastruct;

public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
    public void SetLeft(TreeNode left) {
        this.left = left;
    }
    public void SetRight(TreeNode right) {
        this.right = right;
    }


}