package lintcode.arrayNumbers;

import java.util.ArrayList;
import java.util.Collections;

/*
 * [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,-2,1,-15,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1], 20
 * 超时
 * 这个题目符合DP的特征使用DP来解决性能问题
 */
public class MaximunSubarrayIII {
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(int[] nums, int k) {
        // 思路：暴力解法——k-1次loop？
        // subArray可以空的？为什么我脑袋里是一团浆糊，还能写出正确的代码？
        // [-1,-1,-2], 1 --> result: -1 -->
        //      **感觉不一致，凭什么k刀可以切出来空的，但是subArray不可以是空的**理解错误
        // 边界情况还是搞不清楚
        //  k-1 combination, 需要递归
        if (nums == null || nums.length ==0 || nums.length < k) {
            return Integer.MIN_VALUE;
        }
        return maxSubArrayHelper(nums, new ArrayList<Integer>(), k - 1, 1);
        // return maxSubArrayHelper(nums, new ArrayList<Integer>(), k, 1, nums.length - k + 1);
    }
    private int maxSubArrayHelper(int[] nums, ArrayList<Integer> list, int rest, int start) {
        if (rest == 0) {
            return maxSum(nums, new ArrayList<Integer>(list));
        }
        ArrayList<Integer> sums = new ArrayList<Integer>();
        int end = nums.length - rest + 1;
        for (int i = start; i < end; i++) {
            list.add(i);
            sums.add(maxSubArrayHelper(nums, list, rest - 1, i + 1));
            list.remove(list.size() - 1);
        }
        
        int max = Integer.MIN_VALUE;
        for (int j = 0, len = sums.size(); j < len; j++) {
            if (max < sums.get(j)) {
                max = sums.get(j);
            }
        }
        return max;
    }
    private int maxSum(int[] nums, ArrayList<Integer> list) {
        // [-1,-1,-2], 1(相当于不切) --> -1
        // if (nums == null || list == null || list.size() == 0) {
        if (nums == null || list == null) {
            return Integer.MIN_VALUE;
        }
        list.add(0);
        list.add(nums.length);
        Collections.sort(list);
        // System.out.println("##" + list);
        
        int sum = 0;
        for (int i = 1, len = list.size(); i < len; i++) {
            sum += findMaxSubSum(nums, list.get(i - 1), list.get(i));
            // System.out.println(list.get(i - 1) + "," + list.get(i) + "," + sum);
        }
        // System.out.println("**" + sum);
        return sum;
    }
    private int findMaxSubSum(int[] nums, int start, int end) {
        if (start < 0 || end <= start) {
            return 0;
        }
        int local = Integer.MIN_VALUE;
        int global = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (local < 0) {
                local = nums[i];
            } else {
                local += nums[i];
            }
            if (global < local) {
                global = local;
            }
        }
        return global;
    }
}
