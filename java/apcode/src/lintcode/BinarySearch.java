package lintcode;
/**
 * 
 * @author xiedandan
 * 非递归——居然反了这么大的错误，忘记更新了mid。。。。。
 * 递归——反了子模式没有return。。。。
 * ！！！！！！最简单的算法写成这样，我也是醉了，居然都不能一次通过。头上的乌云，怎么验证程序的正确性！！！！
 * 模板：
 * left， right， mid
 * 每次循环记住三个都要更新
 * 结束条件是 left > right, 当left==right的时候，子序列还有一个元素，还是需要检查
 * left = mid + 1
 * right = mid -1 一定要避开==mid， 因为mid已经被检查过了
 * 循环结束后，left是第一个大于或等于target的元素，如果target没有被找到，那么就是大于
 * 同理right是第一小于或等于target的元素，如果target没有被找到，那么就是小于
 * binarysearch天然支持数据重复的情况，但是不保证查到的index是重复数据的第一个还是最后一个，这个是重复数据序列中的任意一个。
 * 
 * 成套的模板：原则每次left和right必须得到更新，不能停留在原地打转, 所有的 mid = (right - left)/2 + left,
 * left <= right (have 0 element after loop), left = mid + 1, right = mid - 1
 * left < right (have 1 element after loop), left = mid + 1, right = mid - 1, 后续检查最后留的
 * left + 1 < right (have 2 element after loop), left = mid + 1, right = mid - 1
 * 				left = mid, right = mid, 需要后续检查最后留的
 */
public class BinarySearch {
    public int searchInsert(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
        	int mid = (right - left) / 2 + left;//smaller element first
            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target ) {
                right = mid -1;
            } else {
                return mid;
            }
        }
        // not found
        return left;
    }
    public int searchInsertRecu(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return searchInsertHelper(arr, target, 0, arr.length - 1);
    }
    private int searchInsertHelper(int[] arr, int target, int left, int right) {
        if (left > right) {
            return left;
        }
        int mid = (right - left)/2 + left;
        if (arr[mid] > target) {
        	//特别注意这里也要return
            return searchInsertHelper(arr, target, left, mid - 1);
        } else if (arr[mid] < target) {
        	//特别注意这里也要return
            return searchInsertHelper(arr, target, mid + 1, right);
        } else {
            return mid;
        }
    }
    //变种binarySearch，规定了寻找第一个位置
    public int  firstPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                int i = mid;
                while (i >= 0 && (nums[i] == target)) {
                    i--;
                }
                return i + 1;
            }
        }
        return -1;
    }
    public static void main(String[] args){
    	BinarySearch bs = new BinarySearch();
    	int[][] arrays = {
    			null,
    			{},
    			{1, 1, 1, 9, 9, 9, 10, 10},
    			{1,1,2,5,6,6,9},
    			{1,1,1,9,9,9,10,10,10}
    	};
    	int target = 9;
    	for(int[] array:arrays){
    		int result = bs.firstPosition(array, target);
    		System.out.println("result:" + result);
    	}
    }
}
