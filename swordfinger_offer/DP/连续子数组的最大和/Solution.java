package swordfinger_offer.DP.连续子数组的最大和;

import java.util.*;
public class Solution {
    // 要考虑curSum 等于负值的情况

    // 如果当前是正数，那么不论前面是正数还是负数都不可能是max

    // 1. 如果加了负数小于0，那么直接舍弃寻找第一个正数再开始
    // 2. 如果负数是最后一个，直接舍弃
    // 3. 如果负数右边的是正数且绝对值大于负数 加入
    // 4. 使用外层的一个常量做好记录就行，遇到连着两个负数就把加和负数之前的值记录下来即可

    // 一定要考虑负值的情况！！
    // 也就是要分情况：
    // 当前元素是正数
    //     curSum是正数
    //     curSum是负数
    // 当前元素是负数
    //     curSum是正数
    //     curSum是负数
    // 这样分情况来讨论

    // 最值问题，涉及多步操作的，考虑贪心算法，即每一步操作都考虑局部最优解；整体考虑全局最优解

    // 其实没有必要分那么多情况，每次加和操作都更新下maxSum，然后对于curSum为正的情况统一加和，对于curSum为负的情况统一重新赋值curSum即可，如果把curSum加成负数了就直接交给下次加和来控制即可
    public int FindGreatestSumOfSubArray(int [] array) {
        if(array == null || array.length == 0){
            return 0;
        }
        int index = 1;
        int curSum = array[0];
        int maxSum = array[0];
        int length = array.length;
        while(index < length){
            maxSum = Math.max(maxSum, curSum);
            if(curSum >= 0){
                curSum += array[index];
            }else{
                curSum = array[index];
            }
            // 最后这一次判断绝不可以省略，因为在前一次判断中curSum进行了变更，最终的结果要保存下这次变更
            if(index == length-1){
                maxSum = Math.max(maxSum, curSum);
            }
            index ++;
        }
        return maxSum;
    }
}
