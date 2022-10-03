package swordfinger_offer.tree.从上往下打印二叉树;

import datastruct.TreeNode;

import java.util.*;

public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode popNode = null;
        while(queue.size() > 0){
            popNode = queue.remove();
            list.add(popNode.val);
            if(popNode.left != null){
                queue.add(popNode.left);
            }
            if(popNode.right != null){
                queue.add(popNode.right);
            }
        }
        return list;
    }
}
