package swordfinger_offer.tree.按之字形顺序打印二叉树;

import datastruct.TreeNode;

import java.util.*;

public class Solution {
    // 关键点：
    // 1. 最底层还是使用层序遍历的方法，要记住输出的都是一样的数据，只是层序遍历不分隔每一层，而此题分隔每一层，所以还是使用队列，
    // 只是在入队列的时候维护下一层的节点个数就行了
    // 2.通过奇偶来判断是否要reverse
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> resList = new ArrayList<>();
        ArrayList<Integer> singleList = new ArrayList<>();
        if(pRoot == null){
            return resList;
        }
        int rowIndex = 0;
        // 为偶数表示从左到右，为基数表示从右到左
        int curLength = 1;
        int nextLength = 0;
        int curCount = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        TreeNode popNode = null;
        while(queue.size() > 0){
            if(curCount < curLength){
                popNode = queue.remove();
                singleList.add(popNode.val);
                if(popNode.left != null){
                    queue.add(popNode.left);
                    nextLength ++;
                }
                if(popNode.right != null){
                    queue.add(popNode.right);
                    nextLength ++;
                }
                curCount ++;
            }
            if(curCount == curLength){
                curLength = nextLength;
                curCount = 0;
                nextLength = 0;
                if(rowIndex %2 != 0){
                    reverse(singleList);
                }
                resList.add(new ArrayList<>(singleList));
                singleList.clear();
                rowIndex ++;
            }
        }
        return resList;
    }

    public void reverse(ArrayList<Integer> singleList){
        int length = singleList.size();
        int left = 0;
        int right = length-1;
        while(left < length/2 && left < right){
            int temp = singleList.get(left);
            singleList.set(left, singleList.get(right));
            singleList.set(right, temp);
            left ++;
            right --;
        }
    }
}
