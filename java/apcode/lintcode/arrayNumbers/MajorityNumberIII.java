package lintcode.arrayNumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MajorityNumberIII {
    public int majorityNumber(ArrayList<Integer> nums, int k) {
        // 突然发现，到这个级别已经不用使用greedy了，直接普通的hashtable就行
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int[] candidates = new int[k];
        int[] counters = new int[k];
        // HashMap<Integer, Integer> candidates = new HashMap<Integer, Integer>();
        int len = nums.size();
        boolean reduce = true;
        for (int i = 0; i < len; i++) {
            reduce = true;
            for (int j = 0; j < k; j++) {
                // System.out.println(nums.get(i) + "," + candidates[j] + "," + counters[j]);
                if (counters[j] != 0 && candidates[j] == nums.get(i)) {
                    counters[j]++;
                    reduce = false;
                    break;
                } else if (counters[j] == 0) {
                    candidates[j] = nums.get(i);
                    counters[j] = 1;
                    reduce = false;
                    break;
                }
            }
            if (reduce == true) {
                for (int j = 0; j < k; j++) {
                    counters[j]--;
                }
            }
        }
        int[] reCounters = new int[k];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < k; j++) {
                if (candidates[j] == nums.get(i)) {
                    reCounters[j]++;
                }
            }
        }
        int maxCount = 0;
        int maxCandidate = 0;
        for (int j = 0; j < k; j++) {
            // System.out.println(candidates[j] + "," +reCounters[j]);
            if (reCounters[j] > maxCount) {
                maxCandidate = candidates[j];
                maxCount = reCounters[j];
            }
        }
        return maxCount > len / k ?maxCandidate : -1;
    }
    public static void main(String[] args) {
    	MajorityNumberIII mn = new MajorityNumberIII();
    	int[][] arrays = {
    			null,
    			{},
    			{1, 2, 1},
    			{3,1,2,3,2,3,3,4,4,4}
    	};
        int k = 3;
    	for(int[] array : arrays){
    		ArrayList<Integer> nums = mn.toArrayList(array);
    		System.out.println("haystack:" + nums);
    		System.out.println("result:" + mn.majorityNumber(nums, k));
    	}
    }
    private ArrayList<Integer> toArrayList(int[] array) {
    	if (array == null) {
    		return null;
    	}
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	for(int i = 0; i < array.length; i++){
    		list.add(array[i]);
    	}
    	return list;
    }
}
