import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/
 * 215. 数组中的第K个最大元素
 * 难度：中等
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *
 * 提示：
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 */
public class Leetcode215 {
    public int findKthLargest(int[] nums, int k) {
        // 内置排序方法排序，然后 取 n - k 下标的值
        // 时间复杂度: O(NlogN)

        // 写法一：降序排序，取 k-1 下标的值
        // Integer[] arr = new Integer[nums.length];
        // for (int i = 0; i < nums.length; i++) {
        // arr[i] = nums[i];
        // }
        // // Arrays.sort(arr, Comparator.comparingInt(n -> -n));
        // Arrays.sort(arr, (n1, n2) -> n2 - n1);

        // return arr[k - 1];

        // 写法二：找第 k 大，即查找排序（升序，以 0 起始）后的第 n-k 小的值
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargest2(int[] nums, int k) {
        // 优先队列（堆排序）：时间复杂度：O(N)（建堆） + O(klogN)（取 K 次最值）

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1); // 大根堆
        // 建立堆：O(N)
        for (int n : nums) {
            pq.add(n);
        }
        while (k > 1) { // 弹出 k-1 次最值：O((k-1)logN) => O(klogN)
            pq.poll();
            k--;
        }

        return pq.peek(); // 取第 k 最值
    }

    public int findKthLargest3(int[] nums, int k) {
        // 快速排序：可优化至 O(N)
        // 找第 k 大，即查找排序（升序，以 0 起始）后的第 n-k 小的值
        int n = nums.length;
        return quickSort(nums, 0, n - 1, n - k);
    }

    // 快速排序：查找第 k 小的值
    // 思路
    // 1.将序列分成了左、右子数组且分别为：[较小的 p 个数][较大的 n - p 个数]
    // 2.第 k 小的数在哪？当 k < p 时，说明左子数组包含了第 k 小的值，则在左子数组里，否则，在右子数组里。
    // 3.所以每次只需递归左、或右子数组
    // 4.时间复杂度：n + n/2 + n/4 + ...+ 1 < 2n => O(n)
    private int quickSort(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }

        int pIdx = partition(nums, left, right);

        // 细节：此处为 <= 符合，因为右边界的取值包含 pIdx
        if (k <= pIdx) {
            return quickSort(nums, left, pIdx, k);
        }

        return quickSort(nums, pIdx + 1, right, k);
    }

    private int partition(int[] nums, int left, int right) {
        // right - left + 1 即有 right - left + 1 个元素
        int pIdx = left + (int) Math.floor(Math.random() * (right - left + 1));
        int pivot = nums[pIdx];

        while (left <= right) {
            // 右移至 nums[left] >= pivot;
            while (nums[left] < pivot) {
                left++;
            }

            // 左移至 nums[right] <= pivot;
            while (nums[right] > pivot) {
                right--;
            }

            if (left <= right) {
                int n = nums[left];
                nums[left] = nums[right];
                nums[right] = n;

                left++;
                right--;
            }
        }

        return right;
    }

}
