package swordfinger_offer.DP.最长不含重复字符的子字符串;

import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param s string字符串
     * @return int整型
     */
    // 不包含重复字符 -> 使用Set或者Map
    public int lengthOfLongestSubstring (String s) {
        // write code here
        if(s == null || s.length() == 0){
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 1;
        int left = 0;
        int right = 1;
        // 定义：左闭右闭区间
        map.put(s.charAt(0), 0);
        while(right < s.length()){
            if(map.containsKey(s.charAt(right))){
                maxLength = Math.max(maxLength, right-left);
                int dupIndex = map.get(s.charAt(right));
                for(int i = left ; i <= dupIndex ; i ++){
                    map.remove(s.charAt(i));
                }
                left = dupIndex+1;
            }
            map.put(s.charAt(right), right);
            if(right == s.length()-1){
                maxLength = Math.max(maxLength, right-left+1);
            }
            right ++;
        }
        return maxLength;
    }
}