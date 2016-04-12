package lintcode;

import java.util.ArrayList;
import java.util.Collections;
/**
 * 1. 元素可重复
 * 2. 元素不可重复
 * @author xiedandan
 * 模板：
 * 	遍历当前序列， for
 * 	怎么生产子序列，并递归操作， rest的创建
 * 
 * 总感觉lintCode是有错误的，对于[4,1,0]这种生产的结果只是子序列不一样而已，也判定为错的。
 */
public class SubSet {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> S) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        result.add(new ArrayList<Integer>());
        if ( S == null || S.size() == 0) {
            return result;
        }
        Collections.sort(S);// just for lintCode
        subsetsWithDupHelper(result, S, new ArrayList<Integer>(), 0);
        return result;
    }
    private void subsetsWithDupHelper(ArrayList<ArrayList<Integer>> result,
        ArrayList<Integer> nums, ArrayList<Integer> list, int pos) {
        for(int i = pos; i < nums.size(); i++) {
            if (nums.subList(pos, i).contains(nums.get(i))) {
                System.out.println("continue");
                continue;
            }
            list.add(nums.get(i));
            result.add(new ArrayList<Integer>(list));
            // System.out.println("pos:" + pos +",i:" + i + ",list:" + list + 
            // ",sublist:" + nums.subList(pos, i) + ",num:" + nums.get(i));
            subsetsWithDupHelper(result, nums, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
