package lintcode;

import java.util.List;
import java.util.ArrayList;

public class MergeKSortedArrays {
    /**
     * @param arrays k sorted integer arrays
     * @return a sorted array
     * 这个题还是适合用priorityQueue和两两合并的方法好
     * 因为如果其中一个数据很长，那么压榨的深度会是logL, 而且额外空间分配的也特别频繁
     */
    public List<Integer> mergekSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<Integer>();
        if (arrays == null || arrays.length == 0) {
            return result;
        }
        result = mergekSortedArraysHelper(arrays, 0, arrays.length - 1);
        return result;
    }
    private List<Integer> mergekSortedArraysHelper(int[][] arrays, int start, int end) {
        // System.out.println("##" + start + "," + end + "," + (start + (end - start) / 2));
        if (start == end) {
            // ArrayList 和array之间的转换总是很烦人
            return arrayToList(arrays[start]);
        }
        int mid = start + (end - start) / 2;
        List<Integer> left = mergekSortedArraysHelper(arrays, start, mid);
        List<Integer> right = mergekSortedArraysHelper(arrays, mid + 1, end);
        return mergeTwoArrays(left, right);
    }
    private List<Integer> arrayToList(int[] array) {
        if (array == null) {
            return null;
        }
        List<Integer> list = new ArrayList<Integer>(array.length);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }
    //没有办法确定每个数组在结果集中的位置，所以很难做到不每次都临时分配
    private List<Integer> mergeTwoArrays(List<Integer> left, List<Integer> right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        int i = 0;
        int j = 0;
        int leftLen = left.size();
        int rightLen = right.size();
        List<Integer> mergeArray = new ArrayList<Integer>(leftLen + rightLen);
        while (i < leftLen && j < rightLen) {
            if (left.get(i) <= right.get(j)) {
                mergeArray.add(left.get(i));
                i++;
            } else {
                mergeArray.add(right.get(j));
                j++;
            }
        }
        
        while (i < leftLen) {
            mergeArray.add(left.get(i));
            i++;
        }
        while (j < rightLen) {
            mergeArray.add(right.get(j));
            j++;
        }
        return mergeArray;
    }
}
