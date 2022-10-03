package leetcode.stock_profit.买卖股票的最佳时机2;

public class Solution {
    // 可以随意买卖的话，那么只要价格在上涨（明天价格比今天贵）就一直先买再卖（卖了的当天要再买以备明天再卖出）就可以；
    // 但是只要价格在下降（明天的价格比今天的便宜）就不可以再买了，当然如果今天的价格比昨天便宜，那么今天依然要卖，但是卖了之后今天就不可以再买了
    // 可以当天买入当天卖出，就意味着可以当天卖出再当天买入，所以只要一味的判断今天和明天的价格就可以判断买入和卖出的逻辑

    public int maxProfitOne(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        boolean isSold = false;
        int maxProfit = 0;
        for(int i = 0 ; i < prices.length ; i ++){
            if(isSold){
                maxProfit += prices[i];
                isSold = false;
            }
            if(i+1 < prices.length && prices[i] < prices[i+1]){
                maxProfit -= prices[i];
                isSold = true;
            }
        }
        return maxProfit;
    }

    // 这个题如果按照上面的思路：即对于每一天先买入再卖出，细化到连续几天价格上升期间中的每一天的具体利润得失来看，
    // 确实是不可以用动态规划来做，因为动态规划讲究状态转移，这里的状态要么是买入，要么是卖出，要么是不操作，每一天对于股票的操作也是这样的要求；
    // 但是题目中每天对于操作却是随意的，可以先买入后卖出，也可以先卖出后买入，这样一来状态就是不确定的，所以不可以用动态规划来做
    // 但是其实每一天先买入后卖出其实是不值钱的，所以对于动态规划这种拉长时间区间的做法来说，就不影响了，可以把先买入后卖出的状态视为不进行操作，因为总利润没有变化，因为下一个状态是不固定的，而方法一的下一个操作日期一定是明天，所以要把买入和卖出拆开
    public int maxProfitTwo(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int [][] profit = new int [prices.length][2];
        // 0表示持有，1表示不持有
        profit[0][0] = -prices[0];
        profit[0][1] = 0;
        for(int i = 1 ; i < prices.length ; i ++){
            profit[i][0] = Math.max(profit[i-1][0], profit[i-1][1] - prices[i]);
            profit[i][1] = Math.max(profit[i-1][1], profit[i-1][0] + prices[i]);
        }
        return Math.max(profit[prices.length-1][1], profit[prices.length-1][0]);
    }

}
