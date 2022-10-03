package sort_algo;

import datastruct.BST;
import datastruct.TreeNode;

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
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(7);
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node7 = new TreeNode(3);
//        TreeNode node8 = new TreeNode(8);
//        TreeNode node10 = new TreeNode(10);
        node1.SetLeft(node2);
        node1.SetRight(node3);
        node3.SetLeft(node4);
        node3.SetRight(node5);
//        node3.SetLeft(node6);
//        node3.SetRight(node7);
//        node4.SetLeft(node8);
//        node6.SetLeft(node10);
//        String str = sort.Serialize(node1);
//        System.out.println(str);
        Integer [] numsNew = {1,-2,3,10,-4,7,2,-5};
        List<Integer> list = Arrays.asList(numsNew);
        int [] num = {4,5,1,6,2,7,3,8};
        Integer [] numsss = new Integer [num.length];
        for(int i = 0 ; i < numsss.length ; i ++){
            numsss[i] = num[i];
        }
        List<Integer> list1 = Arrays.asList(numsss);
//        Integer [] newArray = (Integer [])list1.toArray();
//        sort.GetLeastNumbers_Solution(num, 4);
//        printList(list);
        Integer [] numss = {1,-2,3,10,-4,7,2,-5};
        List<Integer> list2 = Arrays.asList(numss);
//        ArrayList<Integer> list3 = (ArrayList<Integer>)list2;
//        sort.FindGreatestSumOfSubArray1(list1);
        int [] nn = {4,5,1,6,2,7,3,8};
        ArrayList<Integer> listNew = sort.GetLeastNumbers_Solution2(nn, 7);
        printList(listNew);
        //
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(100, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        //
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(100, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return o1-o2;
            }
        });

    }

    public static void printArray(int [] nums){
        for(int i = 0 ; i < nums.length ; i ++){
            System.out.print( nums[i] + " ");
        }
        System.out.println();
    }

    public static void printList(ArrayList<Integer> nums){
        for(int i = 0 ; i < nums.size() ; i ++){
            System.out.print(nums.get(i) + " ");
        }
        System.out.println();
    }
}
