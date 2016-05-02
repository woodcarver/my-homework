package lintcode;

import java.util.Arrays;
import java.util.Comparator;

public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        // 思路：O(n) Space, O(nlogn) Time 这个肯定是要对输入数组排序了
        //      O(n) Space, O(n) Time 这个hashMap感觉行不通啊，比如有重复元素的情况呢？
    	int[] result = new int[2];
    	if (numbers == null || numbers.length <= 1) {
    		return result;
    	}
    	Pair[] numPair= new Pair[numbers.length];
    	for (int i = 0; i < numbers.length; i++) {
    		numPair[i] = new Pair(numbers[i], i);
    	}
    	Comparator<Pair> comparator = new Comparator<Pair>(){
			@Override
			public int compare(Pair a, Pair b) {
				return a.number - b.number;
			}
    	};
    	Arrays.sort(numPair, comparator);
    	Pair tmp = null;
    	for (int i = 0; i < numbers.length; i++) {
    		tmp = new Pair(target - numbers[i], i);
    		// error case: [1,0,-1] -- can not be [1,1] ,must two number
    // 		if (tmp.number == 0) {
    // 			result[0] = i + 1;
    // 			result[1] = i + 1;
    // 			return result;
    // 		}
    		tmp = search(numPair, tmp);
    // 		System.out.println("number:" + numbers[i] + "," + (numbers[i] - target));
    		if (tmp != null) {
    		  //  System.out.println("tmp:" + tmp.number);
    			result[0] = i + 1;
    			result[1] = tmp.index + 1;
    			return result;
    		}
    	}
    	return result;
    }
	private Pair search(Pair[] numPair, Pair target) {
		int left = 0;
		int right = numPair.length - 1;
		while (left <= right) {
			int middle = left + (right - left) / 2;
			if (numPair[middle].number == target.number) {
			    if (numPair[middle].index == target.index 
			        && middle > 0 
			        && numPair[middle - 1].number == target.number) {
			        middle = middle - 1; 
			    } else if (numPair[middle].index == target.index
			        && middle < numPair.length - 1
			        && numPair[middle + 1].number == target.number) {
			           middle = middle + 1;
			    }
				return numPair[middle];
			}
			if (numPair[middle].number > target.number) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		return null;
	}
	class Pair implements Comparable {
		int number;
		int index;
		Pair(int number, int index) {
			this.number = number;
			this.index = index;
		}
		@Override
		public int compareTo(Object o) {
			int compara = ((Pair) o).number;
			return number - compara;
		}
	}
}
