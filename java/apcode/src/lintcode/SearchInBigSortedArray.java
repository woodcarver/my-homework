package lintcode;
/**
 * 注意点： 这道题是寻找第一个出现的位置，所以一定注意重复数据的问题。
 * @author xiedandan
 * Given a big sorted array with positive integers sorted by ascending order. The array is so big so that you can not get the length of the whole array directly, and you can only access the kth number by ArrayReader.get(k) (or ArrayReader->get(k) for C++). Find the first index of a target number. Your algorithm should be in O(log k), where k is the first index of the target number.Return -1, if the number doesn't exist in the array.

Have you met this question in a real interview? Yes
 Notice

If you accessed an inaccessible index (outside of the array), ArrayReader.get will return -1.

Example
Given [1, 3, 6, 9, 21, ...], and target = 3, return 1.

Given [1, 3, 6, 9, 21, ...], and target = 4, return -1.
 * 因为是： 1. positive 2. sorted 3. ascending 所以假设target能出现的最大位置就是第target位上，这种时候区间是最小的连续充满。
 * 但是事实证明我too naive了，人家数据可以重复，轻轻松松把我的假设打破。如下面的例子：
 * [1,1,1,1,2,2,3,3,3,4,4,4,5,5,5,5,5,5,5,6,6,6,6,9,9,10,10,10,11,11,11,12], 4
 */
class ArrayReader {
    // get the number at index, return -1 if not exists.
	private int[] elementData;
	public int get(int index){
		return elementData[index];
	}
}
public class SearchInBigSortedArray {
	// base on no duplicate data.
    public int searchBigSortedArray1(ArrayReader reader, int target) {
        int left = 0;
        int right = target - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            int midVal = reader.get(mid);
            if (midVal == -1 || target < midVal) {
                right = mid - 1;
            } else if (target > midVal) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    // correct version
    // but 这个方法超时，太慢(不是太慢，而是有死循环)，不太是log(k)的方法
    // 教训就是特别注意边界检车。。。。
    public int searchBigSortedArray2(ArrayReader reader, int target) {
        int left = 0;
        int right = target - 1;
        // find the first element is bigger than target
        // while (reader.get(right) < target) { //这里可能为踩空
        while (reader.get(right) != -1 && reader.get(right) < target) { 
            left = right;
            right = 2 * right;
        }
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            int midVal = reader.get(mid);
            //if (target < midVal) { //这里可能为踩空
            if (midVal == -1 || target < midVal) {
                right = mid - 1;
            } else if (target > midVal) {
                left = mid + 1;
            } else {
                int i = mid;
                while ((i>= left) && (reader.get(i) == target)){
                    i--;
                }
                return i + 1;
            }
        }
        return -1;
    }
}
