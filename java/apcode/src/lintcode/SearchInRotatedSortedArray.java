package lintcode;
/**
 * 这道题lintCode上的testCase是错误的，运行结果总是-1
 * @author xiedandan
 * 开始我用的是递归，想的是只要出现a[start] > a[end],就不裁剪，后来发现其实这个假设不是不必要的，因为这道题假设了只有一个rotated point。
 * 如果是多个，那需要多想想了。 
 */
public class SearchInRotatedSortedArray {
    public int search(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        return searchHelper(array, target, 0, array.length - 1);
    }
    private int searchHelper(int[] array, int target, int left, int right) {
        if (left > right) { //子序列已经空了,停止搜索
            return -1;
        }
        int mid = (right - left) / 2 + left;
        //只有在a[left] < a[right] 的情况下，子序列才可以被扔掉
        if (target == array[mid]) {
            return mid;
        }
        int result = -1;
        if (array[left] > array[mid] || target < array[mid]) {
            // System.out.println("left part");
            result = searchHelper(array, target, left, mid - 1);
            if (result > -1) {
                return result;
            }
        }
        if (array[mid] > array[right] || target > array[mid]) {
            // System.out.println("right part");
            result = searchHelper(array, target, mid + 1, right);
        }
        return result;
    }
    public int search2(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target == array[mid]) {
                return mid;
            }
            if (array[left] <= array[mid]) {
                if (array[left] <= target && target <= array[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (array[mid] <= target && target <= array[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
    	SearchInRotatedSortedArray searhArray= new SearchInRotatedSortedArray();
    	int[] array = {14,15,1,2,3,4,5};
    	int target = 15;
    	System.out.println(searhArray.search2(array, target));
    }
}
