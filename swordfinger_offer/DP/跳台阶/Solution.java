package swordfinger_offer.DP.跳台阶;

public class Solution {
    // 状态定义：按照题目设问来定义（例如本题中的某一级台阶有几种跳法）
    public int jumpFloor(int target) {
        if(target < 1){
            return 1;
        }
        int [] stairs = new int [target+1];
        stairs[0] = 1;
        stairs[1] = 1;
        for(int i = 2 ; i < stairs.length ; i ++){
            stairs[i] = stairs[i-1] + stairs[i-2];
        }
        return stairs[target];
    }
}
