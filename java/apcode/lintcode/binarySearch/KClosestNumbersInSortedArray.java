package lintcode.binarySearch;
/**
 * 经验，特殊边界除了检查，还要询问面试官改怎么处理？
 * 心中还是要有个Dijkstra —— 如何验证程序，不要偷懒
 */
public class KClosestNumbersInSortedArray {
    public int[] kClosestNumbers(int[] A, int target, int k) {
        // 1. find the closest
        // 2. find left closets
        // 3. find right closets
        int[] closests = new int[k];
        // if (A == null || A.length < 1 || k < 1 || k > A.length) {
        if (A == null || A.length < 1 || k < 1) {
            return closests;
        }
        
        int left = 0;
        int right = A.length - 1;
        int theClosest = -1;
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            if (target == A[middle]) {
                theClosest = middle;
                break;
            } else if (target < A[middle]) {
                right = middle;
            } else if (target > A[middle]) {
                left = middle;
            }
        }
        if (theClosest == -1) {
            theClosest = left;
            if (Math.abs(target - A[left]) > Math.abs(target - A[right])) {
                theClosest = right;
            }
        }
        // find k closests
        // System.out.println("theClosest:" + theClosest);
        closests[0] = A[theClosest];
        int pos = 1;
        int low = theClosest - 1;
        int high = theClosest + 1;
        while (pos < k && low >= 0 && high < A.length) {
            if (Math.abs(target - A[low]) <= Math.abs(target - A[high])) {
                closests[pos++] = A[low];
                low--;
            } else if (Math.abs(target - A[low]) >= Math.abs(target - A[high])) {
                closests[pos++] = A[high];
                high++;
            }
        }
        while (pos < k && low < 0 && high < A.length) {
            closests[pos++] = A[high];
            high++;
        }
        while (pos < k && low >= 0 && high >= A.length) {
            closests[pos++] = A[low];
            low--;
        }
        return closests;
    }
}
