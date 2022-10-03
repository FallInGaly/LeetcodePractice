package swordfinger_offer.DP.连续子数组的最大和二;

import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param array int整型一维数组
     * @return int整型一维数组
     */
    public ArrayList<Integer> FindGreatestSumOfSubArray (ArrayList<Integer> array) {
        if(array == null || array.size() == 0){
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        int length = array.size();
        int curSum = array.get(0);
        int maxSum = array.get(0);
        // 区间定义：左闭右闭
        int start = 0;
        int end = 0;
        int curStart = 0;
        // 使用curEnd做探路变量
        int curEnd = 1;
        while(curEnd < length){
            // 这里的end只能是curEnd-1，因为这里逻辑是在当前索引被加和之前，判断前一次的curSum是否需要更新为maxSum，所以涉及的curEnd是前一个索引，而不是当前索引。
            if(curSum > maxSum || (curSum == maxSum && (curEnd-curStart > end-start))){
                maxSum = curSum;
                start = curStart;
                end = curEnd-1;
            }
            if(curSum >= 0){
                curSum += array.get(curEnd);
            }else{
                curSum = array.get(curEnd);
                curStart = curEnd;
            }
            // 最后这一次的判断不可省略，因为之前是判断前一索引与maxSum的关系，之后 更新了curSum，这次更新必须在循环退出时被记录，所以不可省略，以及此时的end才是curEnd，因为针对的是当前索引的结果
            if(curSum > maxSum || (curSum == maxSum && (curEnd-curStart > end-start))){
                maxSum = curSum;
                start = curStart;
                end = curEnd;
            }
            curEnd ++;
        }
        for(int i = start ; i <= end ; i ++){
            list.add(array.get(i));
        }
        return list;
    }
}
