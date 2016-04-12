package lintcode;
/**
 * 思路就是把二维转化成一维数组
 * @author xiedandan
 * 错误：访问一个元素之前，一定要判断是否其值为null
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        //if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
    	if (matrix == null || matrix.length == 0 
    		|| matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length; //row length
        int n = matrix[0].length; //col length
        int left = 0;
        int right = m * n - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target > matrix[mid / n][mid - mid / n * n]) {
                left = mid + 1;
            } else if (target < matrix[mid / n][mid - mid / n * n]) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
