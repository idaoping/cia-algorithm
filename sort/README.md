## 排序

> https://www.runoob.com/w3cnote/ten-sorting-algorithm.html

### 冒泡排序（Bubble Sort）

> 逆序交换
> * 遍历数据
> * 比较相邻的元素，如果逆序（大或小），则交互
> * O(logN^2)

```typescript
// 写法1：往左冒泡
// 取 n - 1 ~ i 的元素进行从右往左两两比较
// [0...i...n];
//      ^   ^
function bubbleSort1(nums) {
    if (!nums?.length) {
        return;
    }

    let swapped = false; // 记录是否有交换

    for (let i = 0; i < nums.length; i++) {
        // 取 n - 1 ~ i 的元素进行从【右往左】两两比较
        // 目的：把数据往左冒泡
        // 关键点：j-- => 下标减少 => 往左冒泡
        for (let j = nums.length - 1; j > i; j--) {
            if (nums[j] < nums[j - 1]) {
                const n = nums[j - 1];
                nums[j - 1] = nums[j];
                nums[j] = n;

                swapped = true;
            }
        }
        console.log(nums);
        if (!swapped) { // 如果未发生交互，说明已排好序
            break;
        }
    }
}

// 写法2：数据往右冒泡
// 取 0 ~ n - i 的元素进行从左往右两两比较
// [0...i...n];
//  ^   ^
function bubbleSort2(nums) {
    if (!nums?.length) {
        return;
    }

    let swapped = false; // 是否有交换

    // 取 i < nums.length - 1
    // 理解1：两两比较，即最后一对为倒数第二（len - 2）与最后一个数的比较（len - 1)
    // 理解2：有 len - 1 对数据比较
    for (let i = 0; i < nums.length - 1; i++) {

        // 取 i < nums.length - 1：保证 j + 1 合法不越界
        for (let j = 0; j < nums.length - 1 - i; j++) {
            if (nums[j] > nums[j + 1]) {
                const n = nums[j + 1];
                nums[j + 1] = nums[j];
                nums[j] = n;

                swapped = true;
            }
        }
        if (!swapped) { // 如果未发生交互，说明已排好序
            break;
        }
    }
}

```

### 插入排序（Insertion Sort）

> 选择哪个位置来放值
> * 遍历**未排序**数据
> * 在**已排序**的数据中找到**合适的位置**插入（对于数组，移动元素）
> * 找位置关键：从已排序的数据中**从右往左**查找插入的位置
> * O(logN^2)

```typescript
function insertionSort(nums: number[]) {
    if (!nums?.length) {
        return;
    }

    for (let i = 1; i < nums.length; i++) {
        let curr = nums[i];
        let pos = i - 1; // 插入的位置

        // 从已排序的数据中从右往左查找插入的位置
        for (; pos >= 0; pos--) {
            // 注意：不能写成 num[i] >= nums[pos]
            // 原因：num[i] 的值会变化：nums[pos + 1] = nums[pos]，影响了 nums[i] 的值
            if (curr >= nums[pos]) {
                break;
            }
            nums[pos + 1] = nums[pos];
        }

        // while 语句写法
        // while (pos >= 0 && nums[pos] > curr) {
        //     nums[pos + 1] = nums[pos];
        //     pos--;
        // }

        // 理解：pos + 1
        // 1. 上面的循环中 pos-- 多减了 1
        // 2. 宏观上理解：pos 的初始值为 i - 1，如果位置未发生变化，则 pos = i = pos + 1;
        nums[pos + 1] = curr;
    }
}
```

### 选择排序（Selection Sort）

> 选择哪个数来放位置
> * 每次从未排序的的数据中找最小/大值
> * 放到已排序的数据的末尾
> * O(logN^2)

#### typescript

```typescript
function selectionSort(nums: number[]) {
    if (!nums?.length) {
        return;
    }
    for (let i = 0; i < nums.length - 1; i++) {
        let minIdx = i;
        for (let j = i + 1; j < nums.length; j++) {
            if (nums[minIdx] < nums[j]) {
                minIdx = j;
            }
        }
        const n = nums[i];
        nums[i] = nums[minIdx];
        nums[minIdx] = n;
    }
}
```

#### Java

```java
public class Sort {
    public void selectionSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            int minIdx = i;
            for (int j = j + 1; j < nums.length; j++) {
                if (nums[minIdx] < nums[j]) {
                    minIdx = j;
                }
            }

            int n = nums[i];
            nums[i] = nums[minIdx];
            nums[minIdx] = n;
        }
    }
}
```

### 堆排序（Heap Sort）

> https://shimo.im/docs/LneufQhYZB831RUl/read

> 对选择排序的优化： O(NlogN)

#### 写法一

> * 利用二叉堆（Binary Heap, 优先队列的一种实现）高效（O(LogN)）的选出最小/大值
> * 建立一个包含 N 个元素的二叉堆
> * 重复 N 次从二叉堆中取出最小/最大值，即可得到有序序列

```typescript
// 伪代码
function HeapSort(nums) {
    const binaryHeap
    bh = new BinaryHeap();
    // 构建二叉堆
    for (let i = 0; i < nums.length; i++) {
        bh.insert(nums[i]);
    }
    // 循环取二叉堆最值
    for (let i = 0; i < nums.length; i++) {
        nums[i] = bh.pop();
    }
}

```

#### 写法二

> 利用二叉堆的堆化操作
> 1. 对数组建立二叉堆（n 个数）：升序建立大根堆，降序小根堆
> 2. 取第一个元素和最后一个元素交换
> 3. 对 0 ~ n-1 元素的数组进行堆化操作（n - 1个数）
> 4. 重复 2 ~ 3 直到没有元素

```typescript
function heapSort(nums: number[]) {
    if (!nums?.length) {
        return;
    }

    // 建立二叉堆；若进行升序，建立大根堆，若降序排序，建立小根堆
    buildHeap(nums);

    for (let i = nums.length - 1; i >= 0; i--) {
        // 取堆的第一个元素（最值）与最后一个元素交换，使数组最后一个元素成为最值
        [nums[i], nums[0]] = [nums[0], nums[i]];

        // 对 0 ~ i 的数据进行堆化操作，即重新调整堆的位置，时根元素为最值
        heapify(nums, 0, i);
    }
}

function buildHeap(nums: number[]) {
    const len = nums.length;
    // 从下往上堆化操作
    // 取最后一个非叶子节点 Math.floor(len / 2) - 1 开始向上堆化
    // 该方式的优势为：最大时间复杂度 O(N)
    for (let i = Math.floor(len / 2) - 1; i >= 0; i--) {
        heapify(nums, i, len);
    }
}

// 堆化操作，即重新调整堆
// 此例为大根堆
function heapify(nums: number[], i: number, len: number) {
    if(!len) {
        return;
    }
    // [...i...(2 * i + 1),(2 * i + 2)...]
    //     i       left        right
    let maxKey = i;
    let left = 2 * i + 1;   // 左节点 key
    let right = 2 * i + 2;  // 有节点 key

    if (left < len && nums[left] > nums[maxKey]) {
        maxKey = left;
    }
    if (right < len && nums[right] > nums[maxKey]) {
        maxKey = right;
    }

    if (maxKey !== i) {
        [nums[i], nums[maxKey]] = [nums[maxKey], nums[i]];

        heapify(nums, maxKey, len);
    }
}

```

### 归并排序（Merge Sort）

> 基于分治的算法：O(NlogN)
> * 原问题：把数组排序
> * 子题：把数组前一半、后一半分别排序
> * 合并左右两半**已排序**的数组

> 参考：https://shimo.im/docs/aHIgEa1aL2oJKxXb/read

#### typescript

```typescript
function mergeSort(nums: number[], l: number, r: number) {
    // 理解 l >= r;
    // l > r 不合法，终止条件
    // l === r，可以理解为此时 nums 已经拆解为只有一个元素的子数组，即已排好序
    if (!nums?.length || l >= r) {  // 递归终止条件
        return;
    }
    // 归并思路
    // 第一步：将数组二分排序
    const mid = l + ((r - l) >>> 1);
    mergeSort(nums, l, mid);
    mergeSort(nums, mid + 1, r);
    // 第二步：将排序的有序数组合并
    merge(nums, l, mid, r);
}

// 合并两个有序数组
function merge(nums: number[], l: number, mid: number, r: number) {
    const temp = []; // 临时数组，存放合并后的数组
    let idx1 = l; // 有序数组1 开始下标
    let idx2 = mid + 1; // 有序数组2 开始下标

    // 细节：i 的起始值为 l
    for (let i = l; i <= r; i++) {
        // idx2 > r 表示 数组2 的元素已经用完
        if (idx1 <= mid && nums[idx1] <= nums[idx2] || idx2 > r) {
            temp.push(nums[idx1++]);
        } else {
            temp.push(nums[idx2++]);
        }
    }
    // 将排序数组拷贝回原数组
    for (let i = 0; i < temp.length; i++) {
        // 细节：nums 的下标为：l + i
        nums[l + i] = temp[i];
    }
}
```

### 快速排序（Quick Sort）

> 基于分治的算法，最好情况：O(NlogN^2)，最坏情况：O(N^2)
> * 从数组中（随机或中间）选择一个基准元素 pivot
> * 将小于 pivot 的元素放在左边，大于 pivot 的元素放在右边
> * 对左、右两边的子数组重复 1、2 步骤
> * 随机选取 pivot，期望的时间复杂度为 O(NlogN^2)，最坏情况 O(N^2)

> 参考
> * https://shimo.im/docs/ACAlmaE7awcywe0P/read
> * https://www.bilibili.com/video/BV1q64y1S7Ax

> 归并排序 VS 快速排序
> * 归并排序：先排序左右子数组，然后合并两个有序数组
> * 快速排序：先分配好左右子数组，然后对左右子数组分别排序（重复快速排序）
> * 代码具体表现为：归并是先递归，再处理本层逻辑，快排是先处理本层逻辑，再递归
> * 归并在合并处理时，需要开辟额外空间，再拷贝回原数组

#### 写法一（推荐）

> * 划分左、右子数组时，左或者右子数组中**包含** pivot 值
> * 进行 partition（左、右分割）时，**不直接**返回 pivot 的下标

```typescript
function quickSort(nums: number[], l: number, r: number) {
    // 理解 l >= r;
    // l > r 不合法，终止条件
    // l === r，可以理解为此时 nums 已经拆解为只有一个元素的子数组，即已排好序
    if (!nums?.length || l >= r) {
        return;
    }

    const pivotIdx = partition(nums, l, r);
    quickSort(nums, l, pivotIdx); // 左子数组包含 pivot 值
    quickSort(nums, pivotIdx + 1, r);
}

function partition(nums: number[], l: number, r: number) {
    // 随机选一个基准值
    const idx = l + Math.floor(Math.random() * (r - l + 1));
    const pivot = nums[idx];

    // eg. 基准值 9
    // [5, 3, 7, 9, 2, 1, 4, 8]
    //  l                    r
    while (l <= r) {
        // 从左往右遍历，当 nums[l] >= pivot 时，停止
        while (nums[l] < pivot) {
            l++;
        }
        // 从右往左遍历，当 nums[r] <= pivot 时，停止
        while (nums[r] > pivot) {
            r--;
        }

        if (l <= r) {
            // 此时
            // num[l] >= pivot，需要放在右边
            // num[r] <= pivot，需要放在左边
            // 所以，交换 l、r 位置的值
            const n = nums[l];
            nums[l] = nums[r];
            nums[r] = n;

            // 注意，l、r 需要分别右移和左移
            // 理解：交换位置后，已经确定了大小，不需要在下一轮的遍历中做比较
            l++;
            r--;
        }
    }

    // 当写成左子数组包含 pivot 值，return r
    // quickSort(nums, l, pivotIdx); // 左子数组包含 pivot 值
    // quickSort(nums, pivotIdx + 1, r);
    return r;

    // 当写成右子数组包含 pivot 值，return l
    // quickSort(nums, l, pivotIdx - 1);
    // quickSort(nums, pivotIdx, r); // 右子数组包含 pivot 值
    // return l;
}
```

#### 写法二

> * 划分左、右子数组时，左或者右子数组中**不包含** pivot 值
> * 进行 partition（左、右分割）时，**直接**返回 pivot 的下标
> * 基准值选最后一元素或第一个个元素

```typescript
function quickSort(nums, l, r) {
    // 理解 l >= r;
    // l > r 不合法，终止条件
    // l === r，可以理解为此时 nums 已经拆解为只有一个元素的子数组，即已排好序
    if (!nums?.length || l >= r) {
        return;
    }

    const pivotIdx = partition(nums, l, r);
    // 左右子数组都不包含 pivot 值
    quickSort(nums, l, pivotIdx - 1);
    quickSort(nums, pivotIdx + 1, r);
}

function partition(nums, l, r) {
    // 选择最后一个元素为基准值
    let pIdx = r;
    const pivot = nums[pIdx];
    r -= 1;
    // eg. 基准值 9
    // [5, 3, 7, 9, 2, 1, 4, 8]
    //  l                 r  p
    while (l < r) {

        // 从左往右遍历，当 nums[l] >= pivot 时，停止
        while (l < r && nums[l] < pivot) {
            l++;
        }
        // 从右往左遍历，当 nums[r] <= pivot 时，停止
        while (l < r && nums[r] > pivot) {
            r--
        }

        if (l < r) {
            // 此时
            // num[l] >= pivot，需要放在右边
            // num[r] <= pivot，需要放在左边
            // 所以，交换 l、r 位置的值
            const n = nums[l];
            nums[l] = nums[r];
            nums[r] = n;

            // 注意，l、r 需要分别右移和左移
            // 理解：交换位置后，已经确定了大小，不需要在下一轮的遍历中做比较
            l++;
            r--;
        }
    }
    // 此时 l === r;
    // 如果 nums[r] > pivot，则交换，保证 pivot 左右满足[...) < pivot < (...]
    // 否则 [...) < nums[r] < (... pivot]
    if (nums[r] > pivot) {
        nums[pIdx] = nums[r];
        nums[r] = pivot;
    }

    return r; // 返回左右分界的下标
}
```