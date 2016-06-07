package lintcode.binarySearch;

public class ClosestNumberinSortedArray {
    public int closestNumber(int[] A, int target) {
        // Write your code here
        if (A == null || A.length < 1) {
            return 0;
        }
        int left = 0;
        int right = A.length - 1;
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            if (target == A[middle]) {
                return middle;
            } else if (target < A[middle]) {
                right = middle;
            } else if (target > A[middle]) {
                left = middle;
            }
        }
        int closest = left;
        if (Math.abs(target - A[left]) > Math.abs(target - A[right])) {
            closest = right;
        }
        return closest;
    }
    public static void main(String[] args) {
    	ClosestNumberinSortedArray cs = new ClosestNumberinSortedArray();
    	int[][] arrays = {
//    			null,
    			{1,2,3},
    			{1,1,1,2},
    			{-100,-10,-3,-3,0,0,0},
    			{}
    	};
        int k = 2;
    	for(int[] array : arrays){
    		System.out.println("result:" + cs.closestNumber(array, k));
    	}
    }
}
