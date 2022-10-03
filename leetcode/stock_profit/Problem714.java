package leetcode.stock_profit;

public class Problem714 {
    // 这个题目，题目中没有说可以在同一天既买入又卖出就说明一天只能操作一次，所以可以使用动态规划
    // 股票买卖问题，使用两个状态值（0表示持有，1表示不持有）完全可以满足要求，没有必要再新增一个2：不操作，没有任何意义
    public int maxProfit(int[] prices, int fee) {
        if(prices == null || prices.length == 0 || fee < 0){
            return 0;
        }
        int [][] profit = new int [prices.length][2];
        // 0表示持有，1表示不持有
        profit[0][0] = -prices[0];
        profit[0][1] = 0;
        for(int i = 1; i < prices.length ; i ++){
            profit[i][0] = Math.max(profit[i-1][1]-prices[i], profit[i-1][0]);
            profit[i][1] = Math.max(profit[i-1][0]+prices[i]-fee, profit[i-1][1]);
        }
        return Math.max(profit[prices.length-1][0], profit[prices.length-1][1]);
    }
}
