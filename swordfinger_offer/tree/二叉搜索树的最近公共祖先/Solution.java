package swordfinger_offer.tree.二叉搜索树的最近公共祖先;

import datastruct.TreeNode;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param root TreeNode类
     * @param p int整型
     * @param q int整型
     * @return int整型
     */

    // 最近公共祖先：
    // 1. 要么就是两个节点分别在祖先的左右子树中
    // 2. 要么就是一个节点在另一个节点的任意子树中

    // 而搜索树由于其自身的特点，对于第一种情况不需要去依次遍历父节点下的所有节点是否包含给定节点，天然的顺序性使得只要判断父节点和两个节点的大小关系就可以确定该情况；而非搜索树的话就需要遍历父节点下的左右子树来判断是否左右子树自个含有给定的一个节点从而确定该情况
    public int lowestCommonAncestor (TreeNode root, int p, int q) {
        // write code here
        if(root == null){
            return 0;
        }
        if(root.val == p || root.val == q || (root.val > p && root.val < q) || (root.val > q && root.val < p)){
            return root.val;
        }
        if(root.val > p && root.val > q){
            return lowestCommonAncestor(root.left, p, q);
        }
        if(root.val < p && root.val < q){
            return lowestCommonAncestor(root.right, p, q);
        }
        return 0;
    }
}