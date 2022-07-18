/**
 * https://leetcode.cn/problems/relative-sort-array/
 * 1122. 数组的相对排序
 * 给你两个数组，arr1 和 arr2，arr2 中的元素各不相同，arr2 中的每个元素都出现在 arr1 中。
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 * <p>
 * 提示：
 * 1 <= arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 */
public class Leetcode1122 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        // 采用计数排序算法。理由：
        // 1. 数值范围不大：[0...1000]
        // 2. 需要由相对顺序（按 arr2）

        int[] count = new int[1001]; // 数组大小由题意 0 <= arr1[i], arr2[i] <= 1000 得知

        for (int i = 0; i < arr1.length; i++) {
            count[arr1[i]]++; // 统计每个出现数的次数
        }

        int i = 0;
        // 先按照 arr2 的数的次序取值
        for (int n : arr2) {
            while (count[n] > 0) {
                count[n]--;
                arr1[i++] = n;
            }
        }

        // 把剩余的数放到末尾
        for (int n = 0; n < count.length; n++) {
            while (count[n] > 0) {
                count[n]--;
                arr1[i++] = n;
            }
        }

        return arr1;
    }
}
