package swordfinger_offer.DP.跳台阶扩展问题;

public class Solution {
    // 这个题目，其实就是 stairs[i] = stairs[0] + stairs[1] + ... + stairs[i-1]
    // 就是对于前i项和相加（不加当前i项，因为值还不知道，就是为了计算这个值得），但其实这个规则对于每一项都是一样的，即
    // stairs[i-1] = stairs[i] = stairs[0] + stairs[1] + ... + stairs[i-2],所以stairs[i] = stairs[i-1] + stairs[i-1] = 2*stairs[i-1]
    public int jumpFloorII(int target) {
        if(target < 1){
            return 1;
        }
        int [] stairs = new int [target+1];
        stairs[0] = 1;
        stairs[1] = 1;
        for(int i = 2 ; i < stairs.length ; i ++){
            stairs[i] = 2 * stairs[i-1];
        }
        return stairs[target];
    }
}