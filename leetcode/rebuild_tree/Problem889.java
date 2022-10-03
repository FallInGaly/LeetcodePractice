package leetcode.rebuild_tree;

public class Problem889 {
    // 按照前序与后序的话，如果父节点只有左右一棵子树那么无法判断到底是左子树还是右子树，以下这种情况默认为为左子树
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        if(preorder == null || postorder == null || preorder.length != postorder.length || preorder.length == 0){
            return null;
        }
        int length = preorder.length-1;
        return constructFromPrePostRecursion(preorder, postorder, 0, length, 0, length);
    }
    public TreeNode constructFromPrePostRecursion(int[] preorder, int[] postorder, int preStart, int preEnd, int postStart, int postEnd){
        if(preStart > preEnd || postStart > postEnd || preStart < 0 || postStart < 0 || preEnd >= preorder.length || postEnd >= postorder.length){
            return null;
        }
        TreeNode node = new TreeNode(preorder[preStart]);
        if(preStart == preEnd || postStart == postEnd){
            // 说明同时没有左右子树
            return node;
        }
        int leftTreeNodeVal = preorder[preStart+1];
        int leftTreeLastIndexPost = postStart;
        while(leftTreeLastIndexPost < postEnd && postorder[leftTreeLastIndexPost] != leftTreeNodeVal){
            leftTreeLastIndexPost ++;
        }
        int leftTreeLength = leftTreeLastIndexPost - postStart + 1;
        if (leftTreeLastIndexPost < postEnd-1){
            // 说明同时有左右子树
            node.left = constructFromPrePostRecursion(preorder, postorder, preStart+1, preStart+leftTreeLength, postStart, postStart+leftTreeLength-1);
            node.right = constructFromPrePostRecursion(preorder, postorder, preStart+leftTreeLength+1, preEnd, postStart+leftTreeLength, postEnd-1);
        }else{
            node.left = constructFromPrePostRecursion(preorder, postorder, preStart+1, preEnd, postStart, postEnd-1);
        }
        return node;
    }
}
