package swordfinger_offer.tree.二叉搜索树的后序遍历序列;

public class Solution {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence == null || sequence.length == 0){
            return false;
        }
        return VerifySquenceOfBSTResursion(sequence, 0, sequence.length-1);
    }

    public boolean VerifySquenceOfBSTResursion(int [] sequence, int start, int end){
        if(start > end){
            return true;
        }
        if(start == end || start + 1 == end){
            return true;
        }
        int headVal = sequence[end];
        int rightTreeFirstindex = start;
        while(rightTreeFirstindex < end && sequence[rightTreeFirstindex] <= headVal){
            rightTreeFirstindex ++;
        }
        // 如果sequence[leftTreeLastIndex] == headVal说明没有右子树
        // 如果rightTreeFirstindex == 0说明没有右子树
        int curIndex = rightTreeFirstindex;
        while(curIndex < end){
            if(sequence[curIndex] < headVal){
                return false;
            }
            curIndex ++;
        }
        return VerifySquenceOfBSTResursion(sequence, start, rightTreeFirstindex-1) && VerifySquenceOfBSTResursion(sequence, rightTreeFirstindex, end-1);
    }
}