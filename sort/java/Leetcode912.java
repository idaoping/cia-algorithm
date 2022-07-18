import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/sort-an-array/
 * 912. 排序数组
 * 给你一个整数数组 nums，请你将该数组升序排列。
 * 示例 1：
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 *
 * 提示：
 * 1 <= nums.length <= 5 * 104
 * -5 * 104 <= nums[i] <= 5 * 104
 */
public class Leetcode912 {
    public int[] sortArray(int[] nums) {
        // bubbleSort(nums);
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    // 冒泡排序
    private void bubbleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        boolean swapped = false;
        for (int i = 0; i < nums.length - 1; i++) {

            for (int j = 0; j < nums.length - 1 - i; j++) {

                if (nums[j] > nums[j + 1]) {
                    int n = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = n;

                    swapped = true;
                }
            }

            if (swapped == false) {
                return;
            }
        }
    }

    // 归并排序
    private void mergeSort(int[] nums, int left, int right) {

        if (nums == null || nums.length == 0 || left >= right) {
            return;
        }

        int mid = left + ((right - left) >> 1);

        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        // int len = right - left + 1;
        // int[] temp = new int[right - left + 1];

        List<Integer> temp = new ArrayList<>();
        int idx1 = left;
        int idx2 = mid + 1;

        for (int i = left; i <= right; i++) {

            if (idx2 > right || (idx1 <= mid && nums[idx1] < nums[idx2])) {
                // temp[i] = nums[idx1++];
                temp.add(nums[idx1++]);
            } else {
                // temp[i] = nums[idx2++];
                temp.add(nums[idx2++]);
            }
        }

        for (int i = 0; i < temp.size(); i++) {
            nums[left + i] = temp.get(i);
        }
    }
}