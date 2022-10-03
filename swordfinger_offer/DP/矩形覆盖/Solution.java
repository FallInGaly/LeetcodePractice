package swordfinger_offer.DP.矩形覆盖;

public class Solution {
    // 状态：当前的2*n的大矩形有几种摆放方法？（即怎么摆出来的？）说白了就是现在这个图形的样子有可能由几个样子增加了砖变成这个样子的？
    // 由于2*n的形状的特殊性
    // 状态转移：要么由前一个状态一次性增加了一块砖竖着摆进来的
    //           要么由前一个状态一次性增加了两块砖一起横着摆进来的
    public int rectCover(int target) {
        if(target < 1){
            return 0;
        }
        int [] bricks = new int [target+1];
        bricks[0] = 1;
        bricks[1] = 1;
        for(int i = 2 ; i < bricks.length ; i ++){
            bricks[i] = bricks[i-1] + bricks[i-2];
        }
        return bricks[target];
    }
}