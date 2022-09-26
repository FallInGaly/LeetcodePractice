package sort_algo;

public class Main {
    public static void main(String[] args) {
        Sort sort = new Sort();
        int [] nums = new int[]{2,3,4,7,1,0,9,1,15,4,3};
        sort.heapSort(nums);
        printArray(nums);
    }

    public static void printArray(int [] nums){
        for(int i = 0 ; i < nums.length ; i ++){
            System.out.print( nums[i] + " ");
        }
    }
}
