package swordfinger_offer.search.字符串的排列;

import java.util.*;
public class Solution {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        if(str == null || str.length() == 0){
            return list;
        }
        boolean [] isUsed = new boolean[str.length()];
        StringBuffer stringBuffer = new StringBuffer();
        PermutationRecursion(str, isUsed, set, stringBuffer);
        for(String s : set){
            list.add(s);
        }
        return list;
    }

    public void PermutationRecursion(String str, boolean [] isUsed, Set<String> set, StringBuffer stringBuffer){
        if(stringBuffer.length() == str.length()){
            set.add(stringBuffer.toString());
            return;
        }
        for(int i = 0 ; i < str.length() ; i ++){
            if(!isUsed[i]){
                isUsed[i] = true;
                stringBuffer.append(str.charAt(i));
                PermutationRecursion(str, isUsed, set, stringBuffer);
                stringBuffer.delete(stringBuffer.length()-1, stringBuffer.length());
                isUsed[i] = false;
            }
        }
    }
}