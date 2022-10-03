package swordfinger_offer.tree.二叉树中和为某一值的路径二;

import datastruct.TreeNode;

import java.util.ArrayList;

public class Solution {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int expectNumber) {
        ArrayList<ArrayList<Integer>> resList = new ArrayList<>();
        ArrayList<Integer> singleList = new ArrayList<>();
        if(root == null){
            return resList;
        }
        FindPathRecursion(root, 0, expectNumber, resList, singleList);
        return resList;
    }

    // 对于一个数据结构加了元素在递归返回之后就一定要移除这个元素
    // 这种寻找路径的问题，
    // 1. 对于叶子结点的判断必须放在递归返回的时候去判断（就是必须在叶子结点本身去判断是否叶子结点），而不可以在倒数第二层
    // 去判断叶子结点（即在当前层判断下一层是否叶子结点，因为这样就会导致某些问题）
    // 2. 对于叶子结点的判断必须通过node.left == null && node.right ==null来判断，也就是必须在叶子结点的上层就判断左右子节点是不是null
    // 而不可以在叶子结点判断 == null，因为这样就就会导致叶子结点被取到两次（叶子结点的左右都是Null，会走两次递归返回的路径）
    public void FindPathRecursion(TreeNode node, int count,  int expectNumber, ArrayList<ArrayList<Integer>> resList, ArrayList<Integer> singleList){
        if(node.left == null && node.right == null){
            if(count + node.val == expectNumber){
                singleList.add(node.val);
                resList.add(new ArrayList<>(singleList));
                singleList.remove(singleList.size()-1);
            }
            return;
        }
        singleList.add(node.val);
        if(node.left != null){
            FindPathRecursion(node.left, count+node.val, expectNumber, resList, singleList);
        }
        if(node.right != null){
            FindPathRecursion(node.right, count+node.val, expectNumber, resList, singleList);
        }
        singleList.remove(singleList.size()-1);
    }
}
