package swordfinger_offer.backtrace.矩阵中的路径;

import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param matrix char字符型二维数组
     * @param word string字符串
     * @return bool布尔型
     */
    public boolean hasPath (char[][] matrix, String word) {
        // write code here
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int rowLength = matrix.length;
        int columnLength = matrix[0].length;
        boolean [][] isUsed = new boolean [rowLength][columnLength];
        for(int i = 0 ; i < rowLength ; i ++){
            for(int j = 0 ; j < columnLength ; j ++){
                if(matrix[i][j] == word.charAt(0) && hasPathRecursion(matrix, word, i, j, 0, isUsed)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasPathRecursion(char[][] matrix, String word, int row, int column, int index, boolean [][] isUsed){
        if(index == word.length()-1 && matrix[row][column] == word.charAt(index)){
            return true;
        }
        if(matrix[row][column] != word.charAt(index)){
            return false;
        }
        isUsed[row][column] = true;
        // row+1, row-1, column+1, column-1
        boolean isReach = false;
        if(row+1 < matrix.length && !isUsed[row+1][column]){
            isReach = isReach || hasPathRecursion(matrix, word, row+1, column, index+1, isUsed);
        }
        if(row-1 >= 0 && !isUsed[row-1][column]){
            isReach = isReach || hasPathRecursion(matrix, word, row-1, column, index+1, isUsed);
        }
        if(column+1 < matrix[0].length && !isUsed[row][column+1]){
            isReach = isReach || hasPathRecursion(matrix, word, row, column+1, index+1, isUsed);
        }
        if(column-1 >= 0 && !isUsed[row][column-1]){
            isReach = isReach || hasPathRecursion(matrix, word, row, column-1, index+1, isUsed);
        }
        isUsed[row][column] = false;
        return isReach;
    }
}
