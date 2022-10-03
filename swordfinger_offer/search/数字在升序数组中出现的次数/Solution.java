package swordfinger_offer.search.数字在升序数组中出现的次数;

import java.util.*;
public class Solution {
    // O(logN) 要么是基于树的递归，要么是二分，要么是排序
    // 所以此题其实就只是一个二分搜索而已
    public int GetNumberOfK(ArrayList<Integer> array, int k) {
        if(array == null || array.size() == 0){
            return 0;
        }
        int index = search(array, k);
        if(index == -1){
            return 0;
        }
        int count = 0;
        int leftIndex = index;
        int rightIndex = index+1;
        while(leftIndex >= 0){
            if(array.get(leftIndex) == k){
                count ++;
            }
            leftIndex --;
        }
        while(rightIndex < array.size()){
            if(array.get(rightIndex) == k){
                count ++;
            }
            rightIndex ++;
        }
        return count;
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
}
