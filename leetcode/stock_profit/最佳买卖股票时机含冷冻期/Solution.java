package leetcode.stock_profit.最佳买卖股票时机含冷冻期;

public class Solution {
    // 搞清楚状态是什么？
    // 搞清楚状态是如何转移的,是由哪些之前的状态转移过来的？是怎么之前的状态转移过来的？
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        // 0表示持有，1表示不持有且不在冷冻期，2表示不持有且在冷冻期
        int [][] profit = new int [prices.length][3];
        profit[0][0] = -prices[0];
        profit[0][1] = 0;
        profit[0][2] = 0;
        for(int i = 1 ; i < prices.length ; i ++){
            profit[i][0] = Math.max(profit[i-1][0], profit[i-1][1] - prices[i]);
            profit[i][1] = Math.max(profit[i-1][1], profit[i-1][2]);
            profit[i][2] = profit[i-1][0] + prices[i];
        }
        return Math.max(profit[prices.length-1][0], Math.max(profit[prices.length-1][1], profit[prices.length-1][2]));
    }
}
