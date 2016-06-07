package lintcode.binarySearch;
/*
 * [[62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80],
 *  [63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81],
 *  [64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82],
 *  [65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83],
 *  [66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84],
 *  [67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85]], 81
 * -- expected 5, but 58
 */
public class SearchA2DMatrixII {
    public int searchMatrixII(int[][] matrix, int target) {
        // 我想到最直接的方法就是O(mlogn), 对每行进行二分法查找
        // 但是注意有Integers in each column are sorted from up to bottom 这个条件
        // 注意程序要符合locality
        // 一个大错误，这个题不是让找出在什么位置，而是找出出现了多少次！！！
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0; // 规定返回0
        }
        int occurence = 0;
        for (int i = 0, matrixLen = matrix.length; i < matrixLen; i++) {
            occurence += totalOccurrence(matrix[i], target);
        }
        return occurence;
    }
    private int totalOccurrence(int[] line, int target) {
        int lineLen = line.length;
        int left = 0;
        int right = lineLen - 1;
        int middle = 0;
        while (left + 1 < right) {
            middle = left + (right - left) / 2;
            if (target > line[middle]) {
                left = middle;
            } else {
                right = middle;
            }
        }
        int start = 0;
        if (target == line[left]) {
            start = left;
        } else if (target == line[right]) {
            start = right;
        } else {
            return 0;
        }
        
        left = start;
        right = lineLen - 1;
        while (left + 1 < right) {
             middle = left + (right - left) / 2;
             if (target < line[middle]) {
                 right = middle;
             } else {
                 left = middle;
             }
        }
        
        int end = left;
        if (target == line[right]) {
            end = right;
        }
        return end - left + 1;
    }
}
