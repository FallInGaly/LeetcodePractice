package swordfinger_offer.tree.把二叉树打印成多行;

import datastruct.TreeNode;

import java.util.*;

public class Solution {
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> resList = new ArrayList<>();
        ArrayList<Integer> singleList = new ArrayList<>();
        if(pRoot == null){
            return resList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        int curCount = 0;
        int curLength = 1;
        int nextLength = 0;
        TreeNode popNode = null;
        while(queue.size() > 0){
            popNode = queue.remove();
            curCount ++;
            singleList.add(popNode.val);
            if(popNode.left != null){
                queue.add(popNode.left);
                nextLength ++;
            }
            if(popNode.right != null){
                queue.add(popNode.right);
                nextLength ++;
            }
            if(curCount == curLength){
                resList.add(new ArrayList<>(singleList));
                singleList.clear();
                curCount = 0;
                curLength = nextLength;
                nextLength = 0;
            }
        }
        return resList;
    }

}
