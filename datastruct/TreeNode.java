package datastruct;

public class TreeNode {
    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;

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