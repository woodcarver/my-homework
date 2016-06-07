package lintcode.arrayNumbers;

import java.util.ArrayList;
import java.util.Arrays;

public class ThreeSum {
    /**
     * @param numbers : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     * 
     * 000000 --> ?? (0,0,0),(0,0,0)...(0,0,0)??
     */
    public ArrayList<ArrayList<Integer>> Sum3(int[] numbers) {
        // 什么时候用到prefix sum -- subarray?
        // hash table vs sort+two point
        if (numbers == null || numbers.length < 3) {
            return new ArrayList<ArrayList<Integer>>();
        }
        Arrays.sort(numbers);
        ArrayList<ArrayList<Integer>> sums = new ArrayList<ArrayList<Integer>>();
        int length= numbers.length;
        for (int i = 0; i < length; i++) {
            if (i > 0 && (numbers[i] == numbers[i - 1])) {
                continue;
            }
            int newTarget = 0 - numbers[i];
            // turn to 2sum problem
            // int left = 0;
            int left = i + 1;// 保证i <= left <= right
            int right = length - 1;
            while (left < right) {
                if (left == i) {
                    left++;
                } else if (right == i) {
                    right--;
                }
                int sum = numbers[left] + numbers[right];
                if (newTarget == sum) {
                    // 如何去重？
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(numbers[i]);
                    list.add(numbers[left]);
                    list.add(numbers[right]);
                    // Collections.sort(list);
                    sums.add(list);
                    // break;
                    left++;
                    right--;
                    while (left < right && numbers[left - 1] == numbers[left]) {
                    	left++;
                    }
                    while (right >= 0 && numbers[right + 1] == numbers[right]) {
                    	right--;
                    }
                }
                if (newTarget < sum) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        //BB: left + 1 = right --> left, right
        // 这个思路很难解析去，怎么移动left，right？
        // while (left + 1 < right) {
        //     int gap = 0 - (numbers[left] + numbers[right]);
        //     int start = left + 1;
        //     int end = right - 1;
        //     while (start <= end) {
        //         middle = start + (end - start) / 2;
        //         if (gap < numbers[middle]) {
        //             end = middle - 1;
        //         } else if (gap > numbers[middle]) {
        //             start = middle + 1;
        //         } else {
        //             ArrayList<Integer> list = new ArrayList<Integer>();
        //             list.add(numbers[left]);
        //             list.add(numbers[middle]);
        //             list.add(numbers[right]);
        //             Collections.sort(list);
        //             sums.add(list);
        //             break;
        //         }
        //     }
        //     // invarant?
        //     left++;
        //     right--;
        // }
        
        return sums;
    }
    public static void main(String[] args) {
    	ThreeSum threeSum = new ThreeSum();
    	int[] numbers = {0,0,0,0,0,0,0,0};
    	ArrayList<ArrayList<Integer>> result = threeSum.Sum3(numbers);
    	for (ArrayList<Integer> list : result) {
    		System.out.println(list);
    	}
    }
}
