package swordfinger_offer.queue_stack.翻转单词序列;

public class Solution {
    public String ReverseSentence(String str) {
        if(str == null || str.length() == 0){
            return "";
        }
        String reverseStr = reverse(str);
        String [] strs = reverseStr.split(" ");
        StringBuffer stringBuffer = new StringBuffer();
        for(int i  = 0 ; i < strs.length ; i ++){
            String curStr = reverse(strs[i]);
            stringBuffer.append(curStr);
            stringBuffer.append(" ");
        }
        return stringBuffer.toString().trim();
    }

    public String reverse(String str){
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer();
        int right = length-1;
        while(right >= 0){
            stringBuffer.append(str.charAt(right));
            right --;
        }
        return stringBuffer.toString();
    }
}