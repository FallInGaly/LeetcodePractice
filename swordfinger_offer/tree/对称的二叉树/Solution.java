package swordfinger_offer.tree.对称的二叉树;

import datastruct.TreeNode;
import java.util.*;
public class Solution {
    // 递归解法
    // boolean isSymmetrical(TreeNode pRoot) {
    //     if(pRoot == null){
    //         return true;
    //     }
    //     return isSymmetricalRecursion(pRoot.left, pRoot.right);
    // }

    // public boolean isSymmetricalRecursion(TreeNode left, TreeNode right){
    //     if((left == null && right != null) || (left != null && right == null)){
    //         return false;
    //     }
    //     if(left == null && right == null){
    //         return true;
    //     }
    //     if(left.val != right.val){
    //         return false;
    //     }
    //     return isSymmetricalRecursion(left.left, right.right) && isSymmetricalRecursion(left.right, right.left);
    // }

    // 非递归解法
    // 核心思路：
    // 前序 + 中序 可以唯一确定一棵二叉树
    // 中序 + 后序 可以唯一确定一棵二叉树
    // 前序 + 后序 不可以唯一确定一棵二叉树
    boolean isSymmetrical(TreeNode pRoot) {
        if(pRoot == null){
            return true;
        }
        ArrayList<Integer> listInPositive = new ArrayList<>();
        ArrayList<Integer> listInNegative = new ArrayList<>();
        inTraverse(pRoot, listInPositive, true);
        inTraverse(pRoot, listInNegative, false);
        ArrayList<Integer> listPrePositive = new ArrayList<>();
        ArrayList<Integer> listPreNegative = new ArrayList<>();
        preTraverse(pRoot, listPrePositive, true);
        preTraverse(pRoot, listPreNegative, false);
        if(listInPositive.size() != listInNegative.size() || listPrePositive.size() != listPreNegative.size()){
            return false;
        }
        for(int i = 0 ; i < listInPositive.size() ; i ++){
            if(listInPositive.get(i) != listInNegative.get(i)){
                return false;
            }
            if(listPrePositive.get(i) != listPreNegative.get(i)){
                return false;
            }
        }
        return true;
    }

    public void inTraverse(TreeNode node, ArrayList<Integer> listPositive, boolean isPositive){
        Stack<TreeNode> stackPositive = new Stack<>();
        TreeNode curNode = node;
        TreeNode popNode;
        while(stackPositive.size() > 0 || curNode != null){
            if(curNode != null){
                stackPositive.push(curNode);
                curNode = isPositive ? curNode.left : curNode.right;
            }else{
                popNode = stackPositive.pop();
                listPositive.add(popNode.val);
                TreeNode judgeNode = isPositive ? popNode.right : popNode.left;
                if(judgeNode != null){
                    curNode = judgeNode;
                }else{
                    curNode = null;
                }
            }
        }
    }

    public void preTraverse(TreeNode node, ArrayList<Integer> listPositive, boolean isPositive){
        Stack<TreeNode> stackPositive = new Stack<>();
        stackPositive.push(node);
        TreeNode popNode;
        while(stackPositive.size() > 0){
            popNode = stackPositive.pop();
            listPositive.add(popNode.val);
            if(isPositive){
                if(popNode.right != null){
                    stackPositive.push(popNode.right);
                }
                if(popNode.left != null){
                    stackPositive.push(popNode.left);
                }
            }else{
                if(popNode.left != null){
                    stackPositive.push(popNode.left);
                }
                if(popNode.right != null){
                    stackPositive.push(popNode.right);
                }
            }

        }
    }
}
