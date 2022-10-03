package swordfinger_offer.tree.在二叉树中找到两个节点的最近公共祖先;

import datastruct.TreeNode;

public class Solution {
    /**
     *
     * @param root TreeNode类
     * @param o1 int整型
     * @param o2 int整型
     * @return int整型
     */
    int ancestor = 0;
    public int lowestCommonAncestor (TreeNode root, int o1, int o2) {
        // write code here
        if(root == null){
            return 0;
        }
        lowestCommonAncestorRecursion(root, o1, o2);
        return ancestor;
    }
    // 两点：
    // 1. 关于提前返回，不应该提前返回，如果提前返回的话，那么任何节点只要满足其中一个节点，都会直接返回，根本走不到后面给ancestor的步骤
    // 2. 关于返回值，应该返回head left right的并集（或）的逻辑关系，因为match给定节点与否是针对于当前整棵子树而言的
    public boolean lowestCommonAncestorRecursion(TreeNode node, int o1, int o2){
        if(node == null){
            return false;
        }
        boolean headMtach = node.val == o1 || node.val == o2;
        boolean leftMatch = lowestCommonAncestorRecursion(node.left, o1, o2);
        boolean rightMtach = lowestCommonAncestorRecursion(node.right, o1, o2);
        // 主逻辑在这里，所以除了null的情况，不可以提前返回；必须要走完这里的逻辑将ancestor赋值之后再返回
        // 虽然这个方法（递归）的目的是判断某个节点为根的树有没有match到节点，但是还有更重要的目的是如果两个节点均Match到了给ancestor赋值，
        // 所以绝对不可以提前返回，必须进行根据条件给ancestor赋值完毕之后再根据整棵子树的情况返回boolean（有没有match到的结果）
        if((headMtach && leftMatch) || (headMtach && rightMtach) || (leftMatch && rightMtach)){
            ancestor = node.val;
        }
        return headMtach || leftMatch || rightMtach;
    }
}
