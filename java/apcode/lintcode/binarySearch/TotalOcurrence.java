package lintcode.binarySearch;
/**
 * error case:
 * [1,1,1,1,1], 2
 * excepted 0 ,but 1. 错误在最后一句rightPos - leftPos + 1 这里，当没有找到怎么办？？
 *
 */
public class TotalOcurrence {
    public int totalOccurrence(int[] A, int target) {
        // 思路：最简答解法O(n)
        // binary search : O(logn)
        // 找到最第一个target后，然后再找到最右边的 2logn。
        // 不能只最左边，然后继续向后扫描。2 2 2 2 2，target = 2 这样是O(n)
    	// 小小的一个程序犯了多少错误！！！！！这就是教训！！！ 一定不能大意，要时时刻刻证明程序是正确的，边界是正确的，一定不能偷懒
    	// 
    	// 二分法是一个特别容易写错的算法，一定注意
    	//
    	// http://www.jiuzhang.com/solutions/binary-search/
    	// 首先要画图，保证其思路正确
    	// 其次要带入case去人肉测试
    	// 参数范围和边界检查
        if (A == null || A.length < 1) {
            return 0; //need to talk
        }
        int left = 0;
        int right = A.length - 1;
        // find left pos
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            if (target > A[middle]) {
                left = middle;
            } else {
                right = middle; // 下一轮情况绝对不会出现right == middle，因为left + 1<right && middle = left + (right - left) / 2;保证了结果。
            }
        }
        System.out.println("find left:" + left + "," + right);
        
        int leftPos = 0;
        if (A[left] == target) {
        	leftPos = left;
        } else if (A[right] == target) {
        	leftPos = right;
        } else {
        	// find nothing
        	return 0;
        }
        
        left = leftPos;
        right = A.length - 1;
        // find right pos
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            if (target < A[middle]) {
                right = middle;
            } else {
                left = middle;
            }
        }
        System.out.println("find right:" + left + "," + right);
        // 这里需要优先赋值成right，而不是left
        int rightPos = left;
        if (A[right] == target) {
            rightPos = right;
        }
        System.out.println(leftPos + "," + rightPos);
        return rightPos - leftPos + 1;
    }
    public static void main(String[] args) {
    	TotalOcurrence oc = new TotalOcurrence();
    	int[][] arrays = {
//    			null,
//    			{1,1,1,1,5},
    			{1,3,3,4,5},
    			{}
    	};
        int k = 3;
    	for(int[] array : arrays){
    		System.out.println("result:" + oc.totalOccurrence(array, k));
    	}
    }
}
