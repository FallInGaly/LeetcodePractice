package sort_algo;

import java.util.Random;

public class Sort {
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
}

