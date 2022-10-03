package swordfinger_offer.DP.礼物的最大价值;

import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param grid int整型二维数组
     * @return int整型
     */
    // 状态：在每一个二维的位置可以拿到多少价值的礼物？
    // 状态转移：要么从(i-1,j)，要么从(i,j-1)的状态转移过来，而且只能移动一格（从之前的位置拿了多少钱，然后加上这个位置的礼物价值，一共多少钱就是当前这个位置的价值，即当前的状态）
    public int maxValue (int[][] grid) {
        // write code here
        if(grid == null || grid.length  == 0 || grid[0].length == 0){
            return 0;
        }
        int rowLength = grid.length;
        int columnLength = grid[0].length;
        int [][] value = new int [rowLength][columnLength];
        // 因为避免 0 -> -1 导致数组越界，所以必须i,j都从1开始，但是这样一来就必须有左侧与上侧位置的状态值，所以需要提前初始化，
        // 因为按照二重循环的状态赋值顺序都是先对角线上的某个点 -> 从这个点开始按照一条水平线 + 一条垂直线，来依次赋值，即是按照上侧以及左侧向内部一层层赋值的，所以只要初始化了左侧以及上侧不存在拿不到上一个状态的值的问题
        value[0][0] = grid[0][0];
        for(int i = 1 ; i < rowLength ; i++){
            value[i][0] = value[i-1][0] + grid[i][0];
        }
        for(int j = 1 ; j < columnLength ; j++){
            value[0][j] = value[0][j-1] + grid[0][j];
        }
        for(int i = 1 ; i < rowLength ; i ++){
            for(int j = 1 ; j < columnLength ; j ++){
                value[i][j] = Math.max(value[i-1][j], value[i][j-1]) + grid[i][j];
            }
        }
        return value[rowLength-1][columnLength-1];
    }
}