package swordfinger_offer.search.二维数组中的查找;

import java.util.*;
public class Solution {
    // 基于二分查找，缩短时间复杂度到O(M*logN)
    public boolean Find1(int target, ArrayList<ArrayList<Integer>> array) {
        if(array == null || array.size() == 0 || array.get(0).size() == 0){
            return false;
        }
        int index = -1;
        for(int i = 0 ; i < array.size() ; i ++){
            index = search(array.get(i), target);
            if(index != -1){
                return true;
            }
        }
        return false;
    }

    public int search(ArrayList<Integer> array, int k){
        int left = 0;
        int right = array.size()-1;
        while(left <= right){
            int mid = left + (right-left)/2;
            if(array.get(mid) == k){
                return mid;
            }else if(array.get(mid) > k){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return -1;
    }

    // 巧妙利用行列排序的规则，在这样严格行列排序的场景下，二维数组中row减小的方向就是数值变小的方向；column增大的方向就是数值变大的方向
    // 所以有了对应 row-- 和 column++ 的逻辑；整体的逻辑与一维的二分查找几乎一模一样，非常巧妙

    // 1. 查找相等的点肯定是以点的维度去移动查找的，所以研究对象肯定是某个点
    // 2. 基于一维的点的移动就是增大与减小；但是基于二维的点却可以上下左右移动一个单位
    // 3.当然也可以在二维也单纯的要么row增大与减小，要么column增大与减小那样好像一维一样的移动，但是这样就会导致时间复杂度必然是相乘的关系，
    // 不可能是相加的，所以一定不是好像一维一样的移动，一定是在二维平面那样灵活的移动才有可能在O(M+N)下查找到
    // 4. 由于行列递增，
    //     a.在一列中的某个数字，其上的数字都比它小
    //     b.在一行中的某个数字，其右的数字都比它大
    public boolean Find(int target, ArrayList<ArrayList<Integer>> array) {
        if(array == null || array.size() == 0 || array.get(0).size() == 0){
            return false;
        }
        int row = array.size()-1;
        int column = 0;
        while(row >= 0 && column < array.get(0).size()){
            if(array.get(row).get(column) == target){
                return true;
            }else if(array.get(row).get(column) > target){
                row --;
            }else{
                column ++;
            }
        }
        return false;
    }
}