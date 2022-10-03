package swordfinger_offer.tree.二叉树中和为某一值的路径一;

import datastruct.TreeNode;

public class Solution {
    /**
     *
     * @param root TreeNode类
     * @param sum int整型
     * @return bool布尔型
     */
    public boolean hasPathSum (TreeNode root, int sum) {
        // write code here
        if(root == null){
            return false;
        }
        return hasPathSumRecursion(root, 0, sum);
    }

    public boolean hasPathSumRecursion(TreeNode node, int count, int sum){
        boolean isReach = false;
        if(node.left == null && node.right == null){
            if(count + node.val == sum){
                return true;
            }else{
                return false;
            }
        }
        if(node.left != null){
            isReach = isReach || hasPathSumRecursion(node.left, count+node.val, sum);
        }
        if(node.right != null){
            isReach = isReach || hasPathSumRecursion(node.right, count+node.val, sum);
        }
        return isReach;
    }
}
