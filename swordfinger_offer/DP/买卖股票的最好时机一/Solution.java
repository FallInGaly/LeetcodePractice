package swordfinger_offer.DP.买卖股票的最好时机一;

import java.util.*;


public class Solution {
    /**
     *
     * @param prices int整型一维数组
     * @return int整型
     */
    // 其实也算是贪心法，就是始终渐进式的维护局部的最优解（前N项minPrice以及maxProfit），而不是直接就奔着全局的最值去了，那肯定复杂度就高了
    // 因为每一个位置的最大利润只能出自于当前的价格以及前n-1项的min值，所以我也不用去管后面的值是不是更小，只要更新好当前位置前面的min值即可
    public int maxProfitTan (int[] prices) {
        // write code here
        if(prices == null || prices.length == 0){
            return 0;
        }
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i = 0 ; i < prices.length ; i ++){
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i]-minPrice);
        }
        return maxProfit;
    }

    // 状态：每一天的收益
    // 状态转移：只和前一天的收益以及今天的（行为）收益有关，行为也只和前一天有关，因为再之前的前一天也会记录最值
    public int maxProfit (int[] prices) {
        // write code here
        if(prices == null || prices.length == 0){
            return 0;
        }
        int [][] profit = new int [prices.length][2];
        // 0表示持有 1表示不持有
        profit[0][0] = -prices[0];
        profit[0][1] = 0;
        for(int i = 1 ; i < prices.length ; i ++){
            // 因为只能交易一次，所以profit[i][0]只能取值为-prices[i]，而不能取值为profit[i-1][1]-prices[i]，简单来说就是不能
            // 把之前赚的钱投入到这次中，简单来说就是买了不能再买，只要再买，那前一次赚的钱清空即可，而后一次是要拿上一次买完股票的前和当前股价做对比来考虑利润的，所以就在全局控制住了不可以重复购买就是profit[i-1][1]-prices[i]
            // 但是如果不限制只买一次就是
            profit[i][0] = Math.max(profit[i-1][0], -prices[i]);
            // profit[i][0] = Math.max(profit[i-1][0], profit[i-1][1]-prices[i]);
            profit[i][1] = Math.max(profit[i-1][1], profit[i-1][0]+prices[i]);
        }
        return Math.max(profit[prices.length-1][0], profit[prices.length-1][1]);
    }
}
