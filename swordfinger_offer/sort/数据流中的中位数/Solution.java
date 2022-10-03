package swordfinger_offer.sort.数据流中的中位数;

import java.util.*;

public class Solution {
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    int size;

    public Solution(){
        maxHeap = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return o2-o1;
            }
        });
        minHeap = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return o1-o2;
            }
        });
        size = 0;
    }
    // Insert的时候就要保证新进来的数值如果要放在maxHeap的话，那他的值一定不能比minHeap的最小值要大
    // 要注意的是：Get中位数不等于取出（或者说删除）中位数，所以只等于peek，而不等于pop
    public void Insert(Integer num) {
        size ++;
        if(minHeap.size() > 0){
            int minVal = minHeap.remove();
            if(num > minVal){
                minHeap.add(num);
                maxHeap.add(minVal);
            }else{
                maxHeap.add(num);
                minHeap.add(minVal);
            }
        }else{
            maxHeap.add(num);
        }
        if(maxHeap.size() - minHeap.size() > 1){
            minHeap.add(maxHeap.remove());
        }
    }

    public Double GetMedian() {
        Double res = 1.0;
        if(size % 2 != 0){
            Integer val = maxHeap.remove();
            res = res * val;
            maxHeap.add(val);
        }else{
            Integer maxVal = maxHeap.remove();
            Integer minVal = minHeap.remove();
            res = (maxVal*res + minVal*res) / 2;
            maxHeap.add(maxVal);
            minHeap.add(minVal);
        }
        return res;
    }
}
