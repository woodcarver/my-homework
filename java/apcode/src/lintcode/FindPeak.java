package lintcode;

import java.util.Arrays;
/**
 * 
 * @author xiedandan
 *There is an integer array which has the following features:

The numbers in adjacent positions are different.
A[0] < A[1] && A[A.length - 2] > A[A.length - 1]. --- 这个保证了其中必有一个peek>= A[0] && peek >= A[A.length - 1]
We define a position P is a peek if:

A[P] > A[P-1] && A[P] > A[P+1]
 */
public class FindPeak {
	// O(n)的方法
    public int findPeak1(int[] array) {
        if (array == null || array.length == 0) {
            return -1;//需要清楚这种情况该怎么定义
        }
        int n = array.length;
        if (n == 1) {
            return 0;
        }
        for (int i = 1; i < n - 1; i++) {
            //System.out.println(array[i - 1] + "," + array[i] + "," + array[i + 1]);
            if (array[i - 1] < array[i] && array[i] > array[i + 1]) {
                //System.out.println(i);
                return i;
            }
        }
        return -1;
    }
    // O(log(n))
    public int findPeak2(int[] array) {
        if (array == null || array.length == 0) {
            return -1;//需要清楚这种情况该怎么定义
        }
        int n = array.length;
        if (n < 3) {
        	return -1;
        }
        int left = 0;
        int right = n - 1;
        while (left + 1 < right) { //子区间至少有3个元素
        	int mid = (right - left) / 2 +left;
        	/* based on "The numbers in adjacent positions are different."
        	 * /\ ==> bingo, found and over
        	 * \  ==> turn to left part
        	 * /  ==> turn to right part
        	 * \/ ==> turn to right part(left part is also ok)
        	 */
        	System.out.println(array[left] + "," + array[mid] + "," + array[right]);
        	if (array[mid - 1] < array[mid] && array[mid] > array[mid + 1]) {
        		return mid;
        	}
        	if (array[mid - 1] > array[mid] && array[mid] > array[mid + 1]) {
        		right = mid; // 不能改成right = mid - 1; 因为mid需要保留着以后构成3数比较关系
        	} else {
        		left = mid;
        	}
        }
        // left + 1 < right 结束后区间中的元素个数<=2,只有当原来的数组的length<2
        return -1;
    }
    public static void main(String[] args) {
    	FindPeak fp = new FindPeak();

    	int[][] arrays = {
    			null,
    			{},
    			{1, 2, 1},
    			{1, 2, 6, 3, 4, 5, 7, 6}
    	};
    	for(int[] array:arrays){
    		System.out.println("result:" + fp.findPeak2(array));
    	}
    }
}
