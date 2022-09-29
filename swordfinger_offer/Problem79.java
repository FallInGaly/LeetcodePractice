package swordfinger_offer;

import java.util.*;
public class Problem79 {
    // 1. 由于一个格子只能访问一次，所以要使用一个boolean数组防止再一次递归中对于相同的格子重复访问
    // 2. 由于递归的逻辑的限制，导致一次递归只能以一个起点开始递归寻找路径，不可能一次递归遍历完所有格子作为起点的路径，所以必须在最外层使用双重循环遍历所有的格子，并依次作为起点开始递归
    public boolean exist(char[][] board, String word) {
        if(board == null || word == null || board.length == 0 || board[0].length == 0){
            return false;
        }
        int rowLength = board.length;
        int columnLength = board[0].length;
        ArrayList<Character> list = new ArrayList<>();
        boolean [][] isUsed = new boolean [rowLength][columnLength];
        for(int i = 0 ; i < rowLength ; i ++){
            for(int j = 0 ; j < columnLength ; j ++){
                if(board[i][j] == word.charAt(0) && existRecursion(board, word, i, j, 0, isUsed, list)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean existRecursion(char[][] board, String word, int row, int column, int strIndex, boolean [][] isUsed, ArrayList<Character> list){
        // 有问题，因为我只考虑了最字符串的第一个以及后一个元素以及字符串长度是否与数组元素匹配，但是没有考虑到中间的部分
        // 即有可能会有长度满足，第一个以及最后一个元素满足但是中间的元素不匹配的情况，所以还要考虑整个字符串是不是匹配
        if(strIndex == word.length()-1){
            list.add(board[row][column]);
            for(int i = 0 ; i < list.size() ; i ++){
                if(list.get(i) != word.charAt(i)){
                    return false;
                }
            }
            return true;
        }
        boolean isReach = false;
        // row+1, row-1, column+1, column-1
        if(row+1 < board.length && !isUsed[row+1][column]){
            isUsed[row][column] = true;
            list.add(board[row][column]);
            isReach = isReach || existRecursion(board, word, row+1, column, strIndex+1, isUsed, list);
            isUsed[row][column] = false;
            list.remove(list.size()-1);
        }
        if(row-1 >= 0 && !isUsed[row-1][column]){
            isUsed[row][column] = true;
            list.add(board[row][column]);
            isReach = isReach || existRecursion(board, word, row-1, column, strIndex+1, isUsed, list);
            isUsed[row][column] = false;
            list.remove(list.size()-1);
        }
        if(column+1 < board[0].length && !isUsed[row][column+1]){
            isUsed[row][column] = true;
            list.add(board[row][column]);
            isReach = isReach || existRecursion(board, word, row, column+1, strIndex+1, isUsed, list);
            isUsed[row][column] = false;
            list.remove(list.size()-1);
        }
        if(column-1 >= 0 && !isUsed[row][column-1]){
            isUsed[row][column] = true;
            list.add(board[row][column]);
            isReach = isReach || existRecursion(board, word, row, column-1, strIndex+1, isUsed, list);
            isUsed[row][column] = false;
            list.remove(list.size()-1);
        }
        return isReach;
    }
}
