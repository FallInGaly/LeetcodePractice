package swordfinger_offer.backtrace.机器人的运动范围;

public class Solution {
    volatile int count = 0;
    public int movingCount(int threshold, int rows, int cols) {
        if(!isCanReach(threshold, 0, 0)){
            return 0;
        }
        boolean [][] isUsed = new boolean[rows][cols];
        movingCountRecursion(threshold, rows-1, cols-1, 0, 0, isUsed);
        return count;
    }

    // 这个题目和那个矩阵搜索的区别是这个题目要找的不是路径，而是点；所以在遍历过一个点之后，就要给
    // boolean二维数组赋值true，并且count ++ , 因为一个点对应一个count ++，所以一个点在整个遍历过程只能被遍历一次，而不是一次递归中遍历一次，
    // 即并且之后全局都不可以再遍历到这个点了，而不是本次递归不可以再遍历到这个点了

    // 正因为研究对象是点而不是路径，所以不应该从递归的维度去考虑，而应该从整体的维度去考虑
    public void movingCountRecursion(int threshold, int rows, int cols, int row, int col, boolean [][] isUsed){
        if(row < 0 || row > rows || col < 0 || col > cols){
            return;
        }
        count ++;
        isUsed[row][col] = true;
        if(row+1 <= rows && isCanReach(threshold, row+1, col) && !isUsed[row+1][col]){
            movingCountRecursion(threshold, rows, cols, row+1, col, isUsed);
        }
        if(row-1 >= 0 && isCanReach(threshold, row-1, col) && !isUsed[row-1][col]){
            movingCountRecursion(threshold, rows, cols, row-1, col, isUsed);
        }
        if(col+1 <= cols && isCanReach(threshold, row, col+1) && !isUsed[row][col+1]){
            movingCountRecursion(threshold, rows, cols, row, col+1, isUsed);
        }
        if(col-1 >= 0 && isCanReach(threshold, row, col-1) && !isUsed[row][col-1]){
            movingCountRecursion(threshold, rows, cols, row, col-1, isUsed);
        }
    }

    public boolean isCanReach(int threshold, int row, int col){
        int count = 0;
        while(row > 0){
            count  += row % 10;
            row = row / 10;
        }
        while(col > 0){
            count += col % 10;
            col = col / 10;
        }
        return count <= threshold;
    }
}
