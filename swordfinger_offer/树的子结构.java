package swordfinger_offer;

import datastruct.TreeNode;

public class 树的子结构 {
    // 通过前中后序遍历来比较子字符串肯定是不可以的,只能通过递归来做
    // 最关键的点：子结构的话，可以某个节点的原树节点不为null，而截取部分的对应节点为null
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if(root2 == null || root1 == null){
            return false;
        }
        return HasSubtreeRecursion(root1, root2);
    }

    public boolean HasSubtreeRecursion(TreeNode root1,TreeNode root2){
        if(root1 == null){
            return false;
        }
        if(root1.val == root2.val){
            boolean isSub = compareTree(root1, root2);
            if(isSub){
                return true;
            }
        }
        return HasSubtreeRecursion(root1.left, root2) || HasSubtreeRecursion(root1.right, root2);
    }

    public boolean compareTree(TreeNode root1,TreeNode root2){
        if(root1 == null && root2 != null){
            return false;
        }
        if((root1 != null && root2 == null) || (root1 == null && root2 == null)){
            return true;
        }
        if(root1 != null && root2 != null && root1.val != root2.val){
            return false;
        }
        if((root1.left == null && root2.left != null) || (root1.right == null && root2.right != null)){
            return false;
        }
        if ((root1.left != null && root2.left != null) && (root1.left.val != root2.left.val)){
            return false;
        }
        if((root1.right != null && root2.right != null) && (root1.right.val != root2.right.val)){
            return false;
        }
        return compareTree(root1.left, root2.left) && compareTree(root1.right, root2.right);
    }
}
