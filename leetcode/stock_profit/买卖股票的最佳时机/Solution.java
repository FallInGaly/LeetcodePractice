package leetcode.stock_profit.买卖股票的最佳时机;

public class Solution {
    // 方法一：动态规划 但是时间复杂度比较大
    public int maxProfitONe(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int [][] profit = new int [prices.length][2];
        // 0表示持有，1表示不持有
        profit[0][0] = -prices[0];
        profit[0][1] = 0;
        for(int i = 1 ; i < prices.length ; i ++){
            profit[i][0] = -prices[i];
            int maxProfit = 0;
            for(int j = i-1 ; j >= 0 ; j --){
                maxProfit = Math.max(profit[i-1][1], Math.max(maxProfit, prices[i] + profit[j][0]));
            }
            profit[i][1] = maxProfit;
        }
        return profit[prices.length-1][1];
    }

    // 方法二：前后缀最大
    // 使用前缀最大以及后缀最大，时间复杂度不大，空闲复杂度不小
    public int maxProfitTwo(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int [] preMin = new int [prices.length];
        int [] postMax = new int [prices.length];
        preMin[0] = prices[0];
        postMax[prices.length-1] = prices[prices.length-1];
        for(int i = 1 ; i < prices.length ; i ++){
            preMin[i] = Math.min(preMin[i-1], prices[i]);
        }
        for(int i = prices.length-2 ; i >= 0 ; i --){
            postMax[i] = Math.max(postMax[i+1], prices[i]);
        }
        int maxProfit = 0;
        for(int i = 0 ; i < prices.length ; i ++){
            maxProfit = Math.max(maxProfit, postMax[i] - preMin[i]);
        }
        return maxProfit;
    }
    // 不使用动态规划，不使用前后缀，而是单纯的从题意入手：
    // 对于每一位的值，将其与该值之前的min相减判断差值并拿到最大值，而该值是不是新的min只对于后续的结果有影响；
    // 即每次遍历，既要得到该值与之前的min的差值（并与之前比较得到最大差值）；也要结合自身得到min，为后续的最大差值比较做准备
    public int maxProfitThree(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int minPrice = prices[0];
        int maxProfit = 0;
        for(int i = 1 ; i < prices.length ; i ++){
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            if (prices[i] < minPrice){
                minPrice = prices[i];
            }
        }
        return maxProfit;
    }
}
