package lintcode.dataStructure;

public class LargestRectangleinHistogram {
    public int largestRectangleArea(int[] height) {
        // 1. 暴力法，每个桩都尝试一遍，然后筛选出最大的那个。
        //    时间复杂度：O(n^2)
        // 2. 优化第一种方案，排个序，减少round中的比较次数
        //    更优化的方案就是由小往大排，第一个数的area是height[0]*height.length
        //    第二个数的area是height[1]*(height.length-1)
        //    第i个数是 height[i]*(height.length - i)
        //    时间复杂度:O(nlogn + n), extra space is O(1)
        // 忽略一个大问题，面积必须要连续吗？？？如果要连续，是没有办法排序的。
        // [2,1,5,2,6,2,3] 答案是10，果然验证了需要连续的假设。方案2不能用
        //  3. 
        if (height == null || height.length == 0) {
            return 0;
        }
        int len = height.length;
        // Arrays.sort(height);
        int global = 0;
        // for (int i = 0; i < len; i++) {
        //     int local = height[i] * (len - i);
        //     global = Math.max(global, local);
        // }
        for (int i = 0; i < len; i++) {
            int local = height[i];
            // System.out.println("##:" + height[i] + "," + local);
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] < height[i]) {
                    break;
                }
                local += height[i];
            }
            for (int k = i + 1; k < len; k++) {
                if (height[k] < height[i]) {
                    break;
                }
                local += height[i];
            }
            if (local > global) {
                global = local;
            }
        }
        return global;
    }
}
