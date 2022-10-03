package swordfinger_offer.sort.数组中重复的数字;

import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param numbers int整型一维数组
     * @return int整型
     */
    public int duplicate1 (int[] numbers) {
        // write code here
        if(numbers == null || numbers.length <= 1){
            return -1;
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < numbers.length ; i ++){
            if(set.contains(numbers[i])){
                return numbers[i];
            }else{
                set.add(numbers[i]);
            }
        }
        return -1;
    }

    // 解法二：由于题目特殊的数值范围（0 - N-1），所以可以考虑把数值与数组下标严格对应起来，
    // 并且在遍历的过程中依次判断当前数值与当前数值为下标的数组元素的值是否相等，
    // 如果相等则说明有重复元素（相等的值之前已经有被复位的）；如果不相等则swap，将当前的数值与对应的下标完成复位，以便后续再遇到重复的值

    // 因为数组下标和元素数值都在[0 - N-1]，即有着完全相同的取值范围，所以不会数组越界
    public int duplicate(int[] numbers){
        if(numbers == null || numbers.length <= 1){
            return -1;
        }
        for(int i = 0 ; i < numbers.length ; i ++){
            if(i == numbers[i]){
                continue;
            }
            if(numbers[i] == numbers[numbers[i]]){
                return numbers[i];
            }else{
                swap(numbers, numbers[i], i);
            }
        }
        return -1;
    }

    public void swap(int[] numbers, int left, int right){
        int temp = numbers[left];
        numbers[left] = numbers[right];
        numbers[right] = temp;
    }
}