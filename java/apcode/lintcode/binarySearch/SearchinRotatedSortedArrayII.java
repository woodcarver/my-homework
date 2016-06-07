package lintcode.binarySearch;
/*
 * [3,4,1,2,3,3,3], 3
 * 大错误出在了target < A[end] --》 target <= A[end]； target > A[start] ---> target >= A[start]
 */
public class SearchinRotatedSortedArrayII {
    public boolean search(int[] A, int target) {
        // 两个重要的点，start和last
        // 1. 在pivot左区间( end <= start <= x): A[middle] > A[last]
        //      target < A[middle] && target > A[start] --> left
        //      or (target > A[middle]) --> right
        // 2. 在pivot右区间(x <= end <= start): A[middle] < A[last]
        //      target > A[middle] && target < A[last] --> right
        //      or (target < A[middle]) --> left
        //      
        // 3. 就是pivot: A[middle] < A[middle - 1]
        //      target > A[middle] --> return -1
        //      target < A[middle] && target > A[start] --> left
        //      or --> right
        // Would this affect the run-time complexity? -- yes
        // How and why? -- 因为当出现重复元素的时候，
        // 当A[middle] == A[last]没有办法判断是在p左还是在p右。所以不能去掉一般搜索空间。
        if (A == null || A.length == 0) {
            return false;
        }
        int start = 0;
        int end = A.length - 1;
        int left = start;
        int right = end;
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            if (target == A[middle]) {
                return true;
            }
            if (A[middle] > A[end]) { // left
                if (target < A[middle] && target >= A[start]) {
                    right = middle;
                } else {
                    left = middle;
                }
            } else if (A[middle] < A[end]) { // right
                if (target > A[middle] && target <= A[end]) {
                    left = middle;
                } else {
                    right = middle;
                }
            } else {
                end--;
                right = end;
            }
        }
        
        if (A[left] == target || A[right] == target) {
            return true;
        }
        return false;
    }
}
