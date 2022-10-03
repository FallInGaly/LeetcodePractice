package swordfinger_offer.sort.最小的K个数;

import java.util.*;

public class Solution {
    // 递归一遍，循环一遍，堆做一遍
    public ArrayList<Integer> GetLeastNumbers_SolutionMaxHeap(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if(input == null || input.length == 0){
            return list;
        }
        // 这样看来java中的优先队列对于capacity的限制是严格的，在创建的时候定义的capacity，在使用中length就不可以大于capacity，否则会报错
        // 所以根据我的需求，要么就是不确定capacity这个参数，要么就是使用k+1，防止越界

        // 做法一，使用大顶堆
        // 使用大顶堆的话，就意味着要利用堆来淘汰大的值，要先组成一个k个元素的堆，之后add一个remove一个，始终保证堆得length为k，最终留下的最小的k个（即把最大的值放在堆顶，删除之后再加入新值）
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k+1, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return o2-o1;
            }
        });
        for(int i = 0 ; i < input.length ; i ++){
            maxHeap.add(input[i]);
            if(maxHeap.size() > k){
                maxHeap.remove();
            }
        }
        for(Integer val : maxHeap){
            list.add(val);
        }
        return list;
    }

    // 做法二，使用小顶堆
    // 使用小顶堆的话，就意味着要利用堆来筛选出小的值，把所有的值全部入堆，然后在得到结果的时候，进行k次堆顶的取出就好了，即取到堆计算出的k个最小值作为结果
    public ArrayList<Integer> GetLeastNumbers_SolutionMinHeap(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if(input == null || input.length == 0){
            return list;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return o1-o2;
            }
        });
        for(int i = 0 ; i < input.length ; i ++){
            minHeap.add(input[i]);
        }
        for(int i = 0 ; i < k ; i ++){
            list.add(minHeap.remove());
        }
        return list;
    }
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if(input == null || input.length == 0){
            return list;
        }
        GetLeastNumbers_SolutionRecursion(input, k, 0, input.length-1);
        GetLeastNumbers_SolutionNonRecursion(input, k-1, 0, input.length-1);
        for(int i = 0 ; i < k ; i ++){
            list.add(input[i]);
        }
        return list;
    }
    public void GetLeastNumbers_SolutionRecursion(int [] input, int k, int left, int right){
        if(left >= right){
            return;
        }
        int index = partition(input, left, right);
        // 定义：左闭右闭区间
        if(index == k){
            return;
        }
        // 都是用基于绝对索引,即将k的值一传到底，不使用相对索引偏移量
        if(index > k){
            GetLeastNumbers_SolutionRecursion(input, k, left, index-1);
        }
        else{
            GetLeastNumbers_SolutionRecursion(input, k, index+1, right);
        }
    }

    // 只走左子树或右子树一条支路，所以可以使用非递归；但是遍历要走两条支路，所以不可以直接非递归
    public void GetLeastNumbers_SolutionNonRecursion(int [] input, int k, int left, int right){
        if(left > right){
            return;
        }
        while(left <= right){
            int index = partition(input, left, right);
            // 定义：左闭右闭区间
            if(index == k){
                return;
            }
            // 都是用基于绝对索引,即将k的值一传到底，不使用相对索引偏移量
            if(index > k){
                right =  index-1;
            }
            else{
                left = index+1;
            }
        }
    }

    // 注意，这里的random.nextInt(xx)，xx必须是大于0的正数，也就是使用random.nextInt(right-left)不行，因为可能为0，
    // 为0就会抛出异常：java.lang.IllegalArgumentException: bound must be positive 必须使用random.nextInt(right-left+1)
    public int partition(int [] input, int left, int right){
        Random random = new Random();
        int randomIndex = random.nextInt((right-left+1)*10)%(right-left+1) + left;
        swap(input, left, randomIndex);
        int value = input[left];
        while(left < right){
            while(left < right && input[right] >= value){
                right --;
            }
            input[left] = input[right];
            while(left < right && input[left] < value){
                left ++;
            }
            input[right] = input[left];
        }
        input[left] = value;
        return left;
    }

    public void swap(int [] input, int left, int right){
        int temp = input[left];
        input[left] = input[right];
        input[right] = temp;
    }
}