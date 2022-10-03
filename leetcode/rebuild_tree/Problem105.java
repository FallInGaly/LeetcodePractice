package leetcode.rebuild_tree;

 // Definition for a binary tree node.
public class Problem105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length != inorder.length || preorder.length == 0){
            return null;
        }
        int length = preorder.length-1;
        return buildTreeRecursion(preorder, inorder, 0, length, 0 , length);
    }
    // 注意：在中序遍历中，父节点的左边第一个值不一定是前序中左子树序列中的最后一个值（是指左子树中距离父节点最近的节点，该节点还有自己的的左子树的）
    // 所以找到中序遍历中父节点左边第一个值在前序遍历中的位置是完全没有意义的，还是直接数中序遍历中从第一个元素走到头结点的步数并去前序遍历中找到父节点的左子树的最后一个节点更靠谱一点

    public TreeNode buildTreeRecursion(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd){
        if(preStart < 0 || preEnd >= preorder.length || inStart < 0 || inEnd >= inorder.length || preStart > preEnd || inStart > inEnd){
            return null;
        }
        TreeNode node = new TreeNode(preorder[preStart]);
        if(preStart == preEnd || inStart == inEnd){
            return node;
        }
        int nodeVal = preorder[preStart];
        int headIndexIn = inStart;
        while(headIndexIn < inEnd && inorder[headIndexIn] != nodeVal){
            headIndexIn ++;
        }
        int leftTreeCount = headIndexIn - inStart;
        if(headIndexIn > inStart){
            node.left = buildTreeRecursion(preorder, inorder, preStart+1, preStart+leftTreeCount, inStart, headIndexIn-1);
        }
        if(headIndexIn < inEnd){
            node.right = buildTreeRecursion(preorder, inorder, preStart+leftTreeCount+1, preEnd, headIndexIn+1, inEnd);
        }
        return node;
    }
}
