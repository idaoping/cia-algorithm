import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.cn/problems/merge-intervals/
 * 56. 合并区间
 * 难度：中等
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]
 * 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例2：
 * <p>
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 提示：
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class Leetcode56 {
    public int[][] merge(int[][] intervals) {
        // 解题思路：先将区间按升序排序，再进行合并区间

        Arrays.sort(intervals, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }

                return o1[1] - o2[1];
            }
        });
        // lambda 写法
        // Arrays.sort(intervals, (a, b) -> {
        // if (a[0] == b[0]) {
        // return a[1] - b[1];
        // }
        // return a[0] - b[0];
        // });

        // 合并
        // eg. [1, 5] [2, 6], [3, 4]
        // [1....5]
        // .[2....6]
        // ..[34]
        // [1.....6]
        // 判断是否连续区间：新区间的起始值是否 > 旧区间的结束值

        List<int[]> ans = new ArrayList<>();
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            int n = ans.size();

            if (n == 0 || ans.get(n - 1)[1] < start) { // 没有重叠，新开区间
                ans.add(new int[] { start, end });
            } else { // 有重叠，连续区间
                ans.get(n - 1)[1] = Math.max(ans.get(n - 1)[1], end); // 更新结束值：取大值
            }
        }

        return ans.toArray(new int[ans.size()][2]);
    }

    public int[][] merge2(int[][] intervals) {
        // 差分思想解题
        // 把每个区间 [l,r] 看作一次 +1 的覆盖，进一步转化为 l 处 +1，r+1 处 -1 两个事件
        // 由区间生成 2n 个事件 -> 排序 -> 遍历，用一个计数变量记录覆盖次数，0变1、1变0 时就找到了合并后的区间
        // eg. [1, 5] [2, 6], [3, 4]
        // 1..2..3..4..5..6..7
        // +1 -1
        // ...+1............-1
        // ......+1....-1

        // 生成 2n 个事件
        // e[0] - 位置
        // e[1] - 状态：+1/-1
        List<int[]> eventList = new ArrayList<>();
        for (int[] interval : intervals) {
            eventList.add(new int[] { interval[0], 1 });
            eventList.add(new int[] { interval[1] + 1, -1 });
        }

        int[][] events = eventList.toArray(new int[][] {});
        Arrays.sort(events, (a, b) -> {
            // 位置相同时，保证 +1 在 -1 之前
            // 举例：[0, 0], [1, 4]
            // 0 1 2 3 4 5
            // +1-1
            // ..+1.....-1
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }

            return a[0] - b[0];
        });

        int status = 0;
        int start = 0;
        List<int[]> ans = new ArrayList<>();
        for (int[] e : events) {
            if (status == 0) { // 状态加之前 0，加之后非 0
                start = e[0]; // 产生一个新区间
            }

            status += e[1];

            if (status == 0) { // 非零变0，区间的结束点
                ans.add(new int[] { start, e[0] - 1 });
            }
        }
        return ans.toArray(new int[][] {});
    }
}