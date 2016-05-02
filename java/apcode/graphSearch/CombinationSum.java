package lintcode.graphSearch;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import algorithms.Util;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 这个题如果出现负数，可能导致程序不能终止，因为(1,-1) + x可以无限选择下去
    	// [8,7,4,3], 11 --- 骗子， 误解了，输入集合无序。
        // 集合是有序的，如果无序首先拍一遍序，其无重复。如果出现重复直接跳过，因为同样的情况已经处理过了。
        // 也不能有零的情况，同样会产生不终止程序
        // 暴力解法：每个元素作为开头继续下去，直到> target
        // 和subset的情况非常相似，但又不同
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (candidates == null || target <= 0) {
            return result;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(candidates);
        combinationSumHelper(result, candidates, list, target, 0);
        return result;
    }
    private void combinationSumHelper(List<List<Integer>> result,
        int[] candidates, ArrayList<Integer> list, int rest, int pos) {
    	// 结束条件放在这里和放在循环里是不一样的，放在这里，基本循环还是要走到底。但是放在loop里就可以及时中断程序
        // if (rest < 0){
        //     return;
        // }
        // if (rest == 0) {
        //     result.add(list);
        //     return;
        // }
        for (int i = pos; i < candidates.length; i++) {
            list.add(candidates[i]);
            if (rest - candidates[i] < 0) {
                list.remove(list.size() - 1);
                break;
            }
            if (rest - candidates[i] == 0) {
                result.add(new ArrayList<Integer>(list));//一定记住要重新分配一个空间
                list.remove(list.size() - 1);
                return;
            }
            combinationSumHelper(result, candidates, list, rest - candidates[i], i);
            list.remove(list.size() - 1);
        }
    }
    public static void main(String[] args) {
    	CombinationSum comSum = new CombinationSum();
    	int[][] arrays = {
    			null,
    			{},
    			{8,7,4,3},
    			{1, 2, 3},
    			{2, 5, 7}
    	};
    	int target = 11;
    	for(int[] array : arrays){
    		Util.printListList(comSum.combinationSum(array, target));
    	}
    }
}
