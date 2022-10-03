package swordfinger_offer.search.旋转数组的最小数字;

import java.util.ArrayList;
public class Solution {
    // 梳理出所有情况就好了
    // 既然是排序数组的旋转，那肯定整体还是有序的，只是在一个局部位置逆序而已
    // 整体使用二分法判定旋转点所在区间，既然整体依然有序，就按照中间点与两端的相对大小判定区间，所有的大小情况：
    // 整体目的：降低旋转点可能存在的区间范围，最终目标是区间长度是1或者2；降低速度最好是折半，也可以逐个单位移动Left，right指针，只要最终可以让[left,right]区间长度为1或者2即可
    // 1. mid > left && mid > right  :右半区间（不包括中间节点）
    // 2. mid < right && mid < left  :左半区间（包括中间节点）
    // 3. mid == left && mid == right：无法判断是在右半区间还是左半区间，但是一定在(left,right)之间，所以left++或者right--来缩小区间
    // 4. mid > left && mid <= right，此时说明子区间是排序的了（说明区间的移动刚好移动到以旋转点为起点的一段子区间中），直接返回left节点即可
    // 注意，此时mid的值可以等于right的值，但是mid的值不可以等于left的值，因为旋转点一定是整个数组的最小值，所以只要不是恒等数组，就一定会有一个值小于后面的值；而且我的区间移动一定是按照旋转点移动的方向来找的，所以不会找到已排序好的另一部分，所以子区间的最小值一定是整个数组的最小值
    // 对于恒等数组，就让left,right数组慢慢缩进直到数组长度为1即可
    // left和right可以一起向中间移动吗？不可以一个极端的情况是恒等数组，如果是偶数个长度的话，那么最终移动结果是：left>right，此时主动返回不了结果，只能被动返回0
    public int minNumberInRotateArray(ArrayList <Integer> array) {
        if(array == null || array.size() == 0){
            return 0;
        }
        int left = 0;
        int right = array.size()-1;
        int mid = 0;
        while(left <= right){
            mid = left+(right-left)/2;
            if(left == right){
                return array.get(left);
            }
            if(mid == left){
                if(array.get(left) < array.get(right)){
                    return array.get(left);
                }else{
                    return array.get(right);
                }
            }
            if(array.get(mid) > array.get(right)){
                left = mid+1;
            }
            else if(array.get(mid) < array.get(left)){
                right = mid;
            }
            else if(array.get(mid) == array.get(left) && array.get(mid) == array.get(right)){
                left ++;
            }
            else if(array.get(mid) > array.get(left) && array.get(mid) <= array.get(right)){
                return array.get(left);
            }
        }
        return 0;
    }
}
