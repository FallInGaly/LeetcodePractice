package swordfinger_offer.DP.把数字翻译成字符串;

import java.util.*;


public class Solution {
    /**
     * 解码
     * @param nums string字符串 数字串
     * @return int整型
     */

    // 应该有这样的直觉：对于一个结构，如果他是字符串，序列，或者数组，以及二维数组这种明确的同层级的一个一个元素的结构
    // 那么对于他们的统计，最值问题都可以拆分为基于多元素的多阶段决策模型，而多阶段模型也可以进而定义为多个状态以及状态之间的转移
    // 所以字符串，序列，数组等结构天然就是动态规划的题目，适合于定义状态

    // 数组反译成字母，就说明序列每一位都要由前一位多加一个字母变成现在的样子 -> 前面的基础上加一当前位数字；前面的基础上给一位数字再加上当前一位数字

    // 按照设问定义状态：每一个字符串的位置有多少种可能的译码结果
    // 状态转移：，所以i的状态只可能由i-1以及i-2两种状态转移得到
    public int solve (String nums) {
        // write code here
        // 0怎么处理?

        if(nums == null || nums.length() == 0){
            return 0;
        }
        int length = nums.length();
        int [] res = new int [length+1];
        res[0] = judge(nums, 0, 0) ? 1: 0;
        res[1] = res[0];
        for(int i = 2 ; i <= length ; i ++){
            res[i] = 0;
            if(nums.charAt(i-1) != '0'){
                res[i] += res[i-1];
            }
            if(judge(nums, i-2, i-1)){
                res[i] += res[i-2];
            }
        }
        return res[length];
    }

    public boolean judge(String nums, int first, int last){
        if(first == last){
            return nums.charAt(first) != '0';
        }
        if(nums.charAt(first) < '1' || nums.charAt(first) > '2'){
            return false;
        }
        if(nums.charAt(first) == '2' && nums.charAt(last) > '6'){
            return false;
        }
        return true;
    }
}
