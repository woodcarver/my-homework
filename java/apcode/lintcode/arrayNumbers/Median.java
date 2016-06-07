package lintcode.arrayNumbers;

public class Median {
    // [8,8,8,8] 会stackoverflow，why？因为没有办法分两半
    // [1,8,5,8,3,9,9] 会stackoverflow，why？1,3,[5,8,8],9,9, -- loop for every
    /**
     * @param nums: A list of integers.
     * @return: An integer denotes the middle number of the array.
     */
    public int median(int[] nums) {
        // quick sort : select
        // find k = nums.length / 2
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int k = (nums.length - 1) / 2;
        
        return select(nums, k, 0, nums.length - 1);
    }
    private int select(int[] nums, int k, int left, int right) {
        if (left >= right) {
            return nums[right];
        }
        // System.out.println("##" + k + "," + left + "," + right);
        int stopPos = partition2Pivot(nums, left, right);
        // printArray(nums);
        int median = 0;
        if (k < stopPos) {
            median = select(nums, k, left, stopPos - 1); // 一定是stopPos - 1
        } else if (k > stopPos){
            median = select(nums, k, stopPos + 1, right);// 一定是stopPos + 1
        } else if (k == stopPos) {
            return nums[k];
        }
        return median;
    }
    // one pivot has some problem: [4,5,1,2,3] -- > [3,2,1,5,4] , but except [2,1,3,5,4]
    private int partition2Pivot(int[] nums, int left, int right) {
        int pivot = threeMedian(nums, left, right);
        int i = left;
        // System.out.println("pivot:" + pivot);
        
        // 每次partition完后，其实也就pivot的位置是真实的排序后的位置，一步到位
        // 运行期间：condition: left <= i <= right
        // 最终停留位置：left <= right <= i 会停留在
        // 代表的意义： left（不包含）左边都是小于pivot的数， 会停留第一个>= pivot的位置，left的最终点一定是pivot
        //              left到i（不包含）之间是=pivot的数, 第一个>pivot的位置
        //              right（不包含）右边都是>pivot的数，第一个<= pivot的位置
        //  每个位置都表示的下一需要确定的位置（即表明这个位置是不确定的，需要检查或更新）
        while(i <= right) {
            if (nums[i] < pivot) {
                int tmp = nums[left];
                nums[left] = nums[i];
                nums[i] = tmp;
                left++;
                i++;// nums[left] <= nums[i],所以i++
            } else if (nums[i] > pivot) {
                int tmp = nums[right];
                nums[right] = nums[i];
                nums[i] = tmp;
                right--;
            } else {
                i++;
            }
        }
        // System.out.println("select point:" + (left + (i - left) / 2));
        return left + (i - left) / 2; // 到底返回left ，i，right？ 仔细考虑下三轴的结果
        // 防止[1,1,1,1,1,1]这种数据
    }
    private int threeMedian(int[] nums, int left, int right) {
        int middle = left + (right - left) / 2;
        int pivot = nums[left];
        if (nums[left] < nums[right]) {
            if (nums[left] < nums[middle]) {
                if (nums[middle] < nums[right]) {
                    pivot = nums[middle];
                } else {
                    pivot = nums[right];
                }
            } else {
                pivot = nums[left];
            }
        } else {
            if (nums[right] < nums[middle]) {
                if (nums[middle] < nums[left]) {
                    pivot = nums[middle];
                } else {
                    pivot = nums[left];
                }
            } else {
                pivot = nums[right];
            }
        }
        return pivot;
    }
    private void printArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println("over");
    }
}
