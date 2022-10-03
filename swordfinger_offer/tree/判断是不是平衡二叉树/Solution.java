package swordfinger_offer.tree.判断是不是平衡二叉树;

import datastruct.TreeNode;

public class Solution {
    boolean isBalance = true;
    public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null){
            return true;
        }
        IsBalanced_SolutionRecursion(root);
        return isBalance;
    }
    public int IsBalanced_SolutionRecursion(TreeNode node){
        if(!isBalance){
            return 0;
        }
        if(node == null){
            return 0;
        }
        int leftHeight = IsBalanced_SolutionRecursion(node.left);
        int rightHeight = IsBalanced_SolutionRecursion(node.right);
        if(Math.abs(leftHeight - rightHeight) > 1){
            isBalance = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
