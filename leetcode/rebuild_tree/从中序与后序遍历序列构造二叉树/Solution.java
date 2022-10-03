package leetcode.rebuild_tree.从中序与后序遍历序列构造二叉树;

import datastruct.TreeNode;

public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null || inorder.length != postorder.length || inorder.length == 0){
            return null;
        }
        int length = inorder.length-1;
        return buildTreeRecursion1(inorder, postorder, 0, length, 0, length);
    }

    public TreeNode buildTreeRecursion1(int[] inorder, int[] postorder, int inStart, int inEnd,int postStart, int postEnd){
        if(inStart < 0 || postStart < 0 || inStart > inEnd || postStart > postEnd || inEnd >= inorder.length || postEnd >= postorder.length){
            return null;
        }
        TreeNode node = new TreeNode(postorder[postEnd]);
        int nodeVal = postorder[postEnd];
        int leftLastIndex = inStart;
        while(leftLastIndex <= inorder.length && inorder[leftLastIndex] != nodeVal){
            leftLastIndex ++;
        }
        int leftTreeLength = leftLastIndex - inStart;
        if(leftLastIndex > inStart){
            // 重建左子树
            node.left = buildTreeRecursion1(inorder, postorder, inStart, leftLastIndex-1, postStart, leftTreeLength+postStart-1);
        }
        if(leftLastIndex < inEnd){
            // 重建右子树
            node.right = buildTreeRecursion1(inorder, postorder, leftLastIndex+1, inEnd, leftTreeLength+postStart, postEnd-1);
        }
        return node;
    }
}
