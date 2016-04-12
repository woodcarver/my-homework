package lintcode;
/**
 * 果然有死循环，特别注意取第一个位置和取最后一个位置的写法
 * @author xiedandan
 *
 */
public class SearchForARange {
    public int[] searchRange1(int[] A, int target) {
        int[] result = {-1,-1};
        if (A == null || A.length == 0) {
            return result;
        }
        // find the first position
        int left = 0;
        int right = A.length -1 ;
        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;
            System.out.printf("mini--left:%d, mid:%d, right:%d\n",left,mid,right);
            if (target > A[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (target == A[left]) {
        	result[0] = left;
        } else if (target == A[right]) {
            result[0] = right;
        } else {
        	return result;
        }
        // find the last position
        //left = 0; // useless because we find the first position and last position must equal to or bigger than FP.
        right = A.length -1 ;
        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;
            System.out.printf("max--left:%d, mid:%d, right:%d\n",left,mid,right);
            if (target < A[mid]) {
                right = mid - 1; // can be right = mid too.
            } else {
                left = mid; // must be left = mid not mid + 1, because target can be equal to A[mid] and this mid must be kept.
            }
        }
        if (target == A[right]) {
        	result[1] = right;
        } else {
        	result[1] = left;
        }
        
        return result;
    }
    public static void main(String[] args) {
    	SearchForARange instant = new SearchForARange();

    	int[][] arrays = {
    			null,
    			{},
    			{1, 1, 1, 9, 9, 9, 10, 10},
    			{1,1,2,5,6,6,9},
    			{1,1,1,9,9,9,10,10,10}
    	};
    	int target = 9; 
    	for(int[] array:arrays){
    		int[] result = instant.searchRange1(array, target);
    		System.out.println("result:" + result[0] + "," + result[1]);
    	}
    }
}
