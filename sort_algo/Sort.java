package sort_algo;

import datastruct.TreeNode;

import java.util.Random;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sort {
    Lock l = new ReentrantLock();
    // 冒泡排序：从后往前来把最小的往前移动，正序排序都是依次取出最小的放在最前面
    public void bubbleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        boolean isContinue = true;
        for (int i = 0; i < length && isContinue; i++) {
            isContinue = false;
            for (int j = length - 1; j > i; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                    isContinue = true;
                }
            }
        }
    }

    // 注意0位的边界条件即可
    public void insertSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            if (nums[i] > nums[i - 1]) {
                continue;
            }
            int j = i - 1;
            int value = nums[i];
            for (; j >= 0 && nums[j] >= value; j--) {
            }
            for (int k = i - 1; k > j; k--) {
                nums[k + 1] = nums[k];
            }
            nums[j + 1] = value;
        }
    }

    public void selectSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int minIndex = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            minIndex = i;
            for (int j = minIndex + 1; j < length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(nums, minIndex, i);
            }
        }
    }

    public void hellSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        int increment = nums.length;
        do {
            increment = increment / 3 + 1;
            for (int i = 0; i < increment; i++) {
                for (int j = i + increment; j < length; j += increment) {
                    if (nums[j] > nums[j - increment]) {
                        continue;
                    }
                    int l = j;
                    int value = nums[j];
                    for (; l >= 0 && nums[l] >= value; l -= increment) {
                    }
                    // key: 寻找最小位点时index可以小于0；所以后续数据位移时的索引不可以等于index，只可以大于index
                    for (int k = j - increment; k > l; k -= increment) {
                        nums[k + increment] = nums[k];
                    }
                    nums[l + increment] = value;
                }
            }
        } while (increment > 1);
    }

    public void quickSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        quickSortRecursion(nums, 0, nums.length - 1);
    }

    public void quickSortRecursion(int[] nums, int left, int right) {
        if (right <= left) {
            return;
        }
        int index = partition(nums, left, right);
        // 4. 左边的部分都小于，右边的部分都大于，那么左右使用index-1,index+1即可，不需要再考虑index索引处的值；相反考虑的话反而会导致再次乱序
        quickSortRecursion(nums, left, index - 1);
        quickSortRecursion(nums, index + 1, right);
    }

    // 1. 获取随机索引要考虑left的偏移量
    // 2. 最终一定是left == right，所以这里使用Left和right没有区别
    // 3. 在拿到随机索引之后，要将其与left交换，之后还是要对于nums[left]进行操作
    public int partition(int[] nums, int left, int right) {
        Random random = new Random();
        int randomIndex = random.nextInt(10 * (right - left + 1)) % (right - left) + left;
        swap(nums, left, randomIndex);
        int value = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= value) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] < value) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[right] = value;
        return right;
    }

    public void mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int[] res = new int[nums.length];
        mergeSortRecursion(nums, 0, nums.length - 1, res);
    }

    public void mergeSortRecursion(int[] nums, int left, int right, int[] res) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        // 必须使用(left, mid)与（mid+1, right），否则对于[1,2]就会导致永远结束不了
        mergeSortRecursion(nums, left, mid, res);
        mergeSortRecursion(nums, mid + 1, right, res);
        merge(nums, left, mid, right, res);
    }

    // 1. res数组向nums数组做迁移不可以在最外层去做，要在每次递归中去做，不然下一次递归感知不到上一次递归的结果
    public void merge(int[] nums, int left, int mid, int right, int[] res) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                res[k] = nums[i];
                i++;
            } else {
                res[k] = nums[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            res[k] = nums[i];
            k++;
            i++;
        }
        while (j <= right) {
            res[k] = nums[j];
            k++;
            j++;
        }
        for (int l = left; l <= right; l++) {
            nums[l] = res[l];
        }
    }

    // 1. 建堆，从length/2的地方开始建堆，因为叶子结点不需要shiftDown(),并对于上面的各节点只进行一次shiftDown（）即可，因为是length/2的位置，
    // 所以该中间节点层的节点下面只有一层 + 也就是只用shiftDown()，不会用到shiftUp()
    public void heapSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        for (int i = length / 2; i >= 0; i--) {
            shiftDown(nums, i, length);
        }
        while (length > 0) {
            // 先length--，在执行shiftDown()
            swap(nums, 0, length - 1);
            length--;
            shiftDown(nums, 0, length);
        }
    }

    // 是要判断2*1+1和2*i+2，但是如果2*i+2 > 2*1,并不是直接交换2*i+1和2*2+2，而是用2*i+2替换变量索引j的值即可
    // 一次shiftDown()只管一条（root->leaf）链路
    public void shiftDown(int[] nums, int index, int length) {
        int i = index;
        while (i < length && 2 * i + 1 < length) {
            int j = 2 * i + 1;
            if (j + 1 < length) {
                if (nums[j] < nums[j + 1]) {
                    j += 1;
                }
            }
            if (nums[i] < nums[j]) {
                swap(nums, i, j);
            }
            i = j;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    String Serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        StringBuffer stringBufferPre = new StringBuffer();
        StringBuffer stringBufferIn = new StringBuffer();
        preOrder(root, stringBufferPre);
        inOrder(root, stringBufferIn);
        String seria = stringBufferPre.toString() + "&" + stringBufferIn.toString();
        return seria;
    }

    public void preOrder(TreeNode node, StringBuffer stringBufferPre){
        if(node == null){
            return;
        }
        stringBufferPre.append(node.val);
        preOrder(node.left, stringBufferPre);
        preOrder(node.right, stringBufferPre);
    }

    public void inOrder(TreeNode node, StringBuffer stringBufferIn){
        if(node == null){
            return;
        }
        preOrder(node.left, stringBufferIn);
        stringBufferIn.append(node.val);
        preOrder(node.right, stringBufferIn);
    }

    TreeNode Deserialize(String str) {
        if(str == null || str.length() == 0){
            return null;
        }
        String [] strs = str.split("&");
        if(strs.length != 2){
            return null;
        }
        int length = strs[0].length();
        int [] pre = new int [length];
        int [] in = new int [length];
        for(int i = 0 ; i < strs[0].length() ; i ++){
            pre[i] = strs[0].charAt(i) - '0';
        }
        for(int i = 0 ; i < strs[1].length() ; i ++){
            in[i] = strs[1].charAt(i) - '0';
        }
        return DeserializeRescursion(pre, in, 0, length-1, 0, length-1);
    }

    public TreeNode DeserializeRescursion(int [] pre, int [] in, int preStart, int preEnd, int inStart, int inEnd){
        if(preStart < 0 || preEnd >= pre.length || inStart < 0 || inEnd >= in.length || preStart > preEnd || inStart > inEnd){
            return null;
        }
        TreeNode head = new TreeNode(pre[preStart]);
        if(preStart == preEnd || inStart == inEnd){
            return head;
        }
        int headVal = pre[preStart];
        int headIndexIn = inStart;
        while(headIndexIn < inEnd && in[headIndexIn] != headVal){
            headIndexIn ++;
        }
        int leftTreeLength = headIndexIn - inStart;
        if(headIndexIn > inStart){
            // 说明有左子树
            head.left = DeserializeRescursion(pre, in, preStart+1, preStart+leftTreeLength, inStart, headIndexIn-1);
        }
        if(headIndexIn < inEnd){
            // 说明有右子树
            head.right = DeserializeRescursion(pre, in, preStart+leftTreeLength+1, preEnd, headIndexIn+1, inEnd);
        }
        return head;
    }

    public ArrayList<Integer> FindGreatestSumOfSubArray (List<Integer> array) {
        // write code here
        if(array == null || array.size() == 0){
            return null;
        }
        // 数组区间采用左闭右闭区间
        int curSum = array.get(0);
        int maxSum = array.get(0);
        int start = 0;
        int end = 0;
        int curStart = 0;
        int curEnd = 1;
        while(curEnd < array.size()){
            if(array.get(curEnd) >= 0){
                if(curSum >= 0){
                    curSum += array.get(curEnd);
                }else{
                    curStart = curEnd;
                    curSum = array.get(curEnd);
                }
            }else{
                if(curSum >= 0){
                    if(curSum > maxSum || (curSum == maxSum && curEnd-curStart > end-start)){
                        maxSum = curSum;
                        start = curStart;
                        end = curEnd-1;
                    }
                    curSum += array.get(curEnd);
                }else{
                    if(curSum > maxSum){
                        maxSum = curSum;
                        start = curStart;
                        end = curEnd-1;
                    }
                    curStart = curEnd;
                    curSum = array.get(curEnd);
                }
            }
            if(curEnd == array.size()-1){
                if(curSum > maxSum || (curSum == maxSum && curEnd-curStart > end-start)){
                    maxSum = curSum;
                    start = curStart;
                    end = curEnd;
                }
            }
            curEnd ++;
        }
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0 ; i <end-start+1 ; i ++){
            res.add(array.get(start+1));
        }
        return res;
    }
    ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<Integer> GetLeastNumbers_Solution3(int [] input, int k) {
        GetLeastNumbers_SolutionRecursion3(input, k-1, 0, input.length-1);
        for(int i = 0 ; i < k ; i ++){
            list.add(input[i]);
        }
        return list;
    }

    // 要注意，这里的k已经不是外层k了，而是局部k
    public void GetLeastNumbers_SolutionRecursion3(int [] input, int k, int left, int right) {
        if(left >= right){
            return;
        }
        int index = partition3(input, left, right);
        if(index == k){
            return;
        }
        else if(index > k){
            GetLeastNumbers_SolutionRecursion(input, k-index-1, index+1, right);
        }
        else{
            GetLeastNumbers_SolutionRecursion(input, k, left, index-1);
        }
    }

    public int partition3(int [] input, int left, int right){
        Random random = new Random();
        int randomIndex = random.nextInt((right-left)*10) % (right-left+1) + left;
        swap1(input, left, randomIndex);
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
    public void swap1(int [] input, int left, int right){
        int temp = input[left];
        input[left] = input[right];
        input[right] = temp;
    }

    public ArrayList<Integer> FindGreatestSumOfSubArray1 (List<Integer> array) {
        if(array == null || array.size() == 0){
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        int length = array.size();
        int curSum = array.get(0);
        int maxSum = array.get(0);
        // 区间定义：左闭右闭
        int start = 0;
        int end = 0;
        int curStart = 0;
        // 使用curEnd做探路变量
        int curEnd = 1;
        while(curEnd < length){
            if(curSum > maxSum || (curSum == maxSum && (curEnd-curStart > end-start))){
                maxSum = curSum;
                start = curStart;
                end = curEnd;
            }
            if(curSum >= 0){
                curSum += array.get(curEnd);
            }else{
                curSum = array.get(curEnd);
                curStart = curEnd;
            }
            if(curSum > maxSum || (curSum == maxSum && (curEnd-curStart > end-start))){
                maxSum = curSum;
                start = curStart;
                end = curEnd;
            }
            curEnd ++;
        }
        for(int i = start ; i <= end ; i ++){
            list.add(array.get(i));
        }
        return list;
    }


    ArrayList<Integer> list4 = new ArrayList<>();
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        GetLeastNumbers_SolutionRecursion(input, k-1, 0, input.length-1);
        for(int i = 0 ; i < k ; i ++){
            list.add(input[i]);
        }
        return list;
    }

    // 要注意，这里的k已经不是外层k了，而是局部k
    public void GetLeastNumbers_SolutionRecursion(int [] input, int k, int left, int right) {
        if(left >= right){
            return;
        }
        // index是绝对索引值，但是k是相对目标索引值，用k和index相减计算相对长度，蠢不蠢？
        // 所以要么都用绝对索引值，要么都用相对索引值，但是要在原数组基础上排序比较，所以只能使用绝对索引，所以统一用绝对索引值即可
        int index = partition(input, left, right);
        if(index == k){
            return;
        }
        else if(index < k){
            GetLeastNumbers_SolutionRecursion(input, k, index+1, right);
        }
        else{
            GetLeastNumbers_SolutionRecursion(input, k, left, index-1);
        }
    }

    public int partition4(int [] input, int left, int right){
        Random random = new Random();
        int randomIndex = random.nextInt((right-left)*10) % (right-left+1) + left;
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
    public void swap4(int [] input, int left, int right){
        int temp = input[left];
        input[left] = input[right];
        input[right] = temp;
    }

    public ArrayList<Integer> GetLeastNumbers_Solution2(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if(input == null || input.length == 0){
            return list;
        }
        // GetLeastNumbers_SolutionRecursion(input, k, 0, input.length-1);
        GetLeastNumbers_SolutionNonRecursion2(input, k-1, 0, input.length-1);
        for(int i = 0 ; i < k ; i ++){
            list.add(input[i]);
        }
        return list;
    }
    // public void GetLeastNumbers_SolutionRecursion(int [] input, int k, int left, int right){
    //     if(left >= right){
    //         return;
    //     }
    //     int index = partition(input, left, right);
    //     // 定义：左闭右闭区间
    //     if(index == k){
    //         return;
    //     }
    //     // 都是用基于绝对索引,即将k的值一传到底，不使用相对索引偏移量
    //     if(index > k){
    //         GetLeastNumbers_SolutionRecursion(input, k, left, index-1);
    //     }
    //     else{
    //         GetLeastNumbers_SolutionRecursion(input, k, index+1, right);
    //     }
    // }
    public void GetLeastNumbers_SolutionNonRecursion2(int [] input, int k, int left, int right){
        if(left > right){
            return;
        }
        while(left <= right && left < input.length && right >= 0){
            int index = partition2(input, left, right);
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

    public int partition2(int [] input, int left, int right){
        Random random = new Random();
        int randomIndex = random.nextInt((right-left+1)*10)%(right-left+1) + left;
        swap2(input, left, randomIndex);
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

    public void swap2(int [] input, int left, int right){
        int temp = input[left];
        input[left] = input[right];
        input[right] = temp;
    }
}

