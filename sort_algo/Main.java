package sort_algo;

import datastruct.BST;
import datastruct.TreeNode;
import swordfinger_offer.Problem79;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        Sort sort = new Sort();
        char [][] nums1 = new char [][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        int [] nums = new int[] {1,2,3,4,5};
        sort.heapSort(nums);
        printArray(nums);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node10 = new TreeNode(10);
        node1.SetLeft(node2);
        node1.SetRight(node3);
        node2.SetLeft(node4);
        node2.SetRight(node5);
        node3.SetLeft(node6);
        node3.SetRight(node7);
        node4.SetLeft(node8);
        node6.SetLeft(node10);
        ArrayList list = bst.inOrder(node1);
        printList(list);
    }

    public static void printArray(int [] nums){
        for(int i = 0 ; i < nums.length ; i ++){
            System.out.print( nums[i] + " ");
        }
    }

    public static void printList(ArrayList<Integer> nums){
        for(int i = 0 ; i < nums.size() ; i ++){
            System.out.print(nums.get(i) + " ");
        }
    }
}
