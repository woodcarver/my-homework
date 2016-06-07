package lintcode.binarySearch;
/*
 * [4,4,4,4,4,4,4,4,4,1,4]
 * 这种时候的时间复杂度基本就是O(n)
 */
public class FindMinimuminRotatedSortedArrayII {
	// has no duplicate numbers
    public int findMinI(int[] num) {
        if (num == null || num.length == 0) {
            return -1;
        }
        int left = 0;
        int right = num.length - 1;
        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;
            if (num[mid] > num[right]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (num[left] < num[right]) {
            return num[left];
        } else {
            return num[right];
        }
    }
 // has duplicate numbers
    public int findMinII(int[] num) {
        // 这道题有个难点就是假如大部分数都一样，比如一堆4中有个1，444441444, 44414444
        // 问题变成了一堆数中找不同
        if (num == null || num.length == 0) {
            return -1; // need to talk to interviewee
        }
        return findMinHelper(num, 0, num.length - 1);
    }
    private int findMinHelper(int[] num, int left, int right) {
        if (left + 1 >= right) {
            return Math.min(num[left], num[right]);
        }
        int min = 0;
        int last = num.length - 1;
        int middle = left + (right - left) / 2;
        if (num[middle] < num[middle - 1]) {
            min = num[middle];
        } else if (num[middle] < num[last]) {
            // turn to left
            min = findMinHelper(num, left, middle - 1);
        } else if (num[middle] > num[last]) {
            // turn to right
            min = findMinHelper(num, middle + 1, right);
        } else if (num[middle] == num[last]) {
            min = Math.min(findMinHelper(num, left, middle - 1),
                findMinHelper(num, middle + 1, right));
        }
        return min;
    }
    public int findMinII2(int[] num) {
        // 这道题有个难点就是假如大部分数都一样，比如一堆4中有个1，444441444, 44414444
        // 问题变成了一堆数中找不同
        if (num == null || num.length == 0) {
            return -1; // need to talk to interviewee
        }
        int end = num.length -1;
        int left = 0;
        int right = end;
        while (left + 1 < right && end >= 0) {
            int middle = left + (right - left) / 2;
            if (num[middle] < num[end]) {
                right = middle; // not middle - 1, 不然会把middle排除掉的
            } else if (num[middle] > num[end]) {
                left = middle + 1;
            } else {
                end--;
                right = end; // keep right is less than end
            }
        }
        return Math.min(num[left], num[right]);
    }
}
