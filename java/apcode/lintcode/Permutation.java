package lintcode;
import java.util.*;
/**
 * @author xiedandan
 * 1. Array 和 ArrayList之间怎么转换？貌似很难，asList只是一个Array的view，和ArrayList是两码事
 * 2. 泛型的操作也云里雾里
 * 模板：一个全局的list来不断的更新递归的轨迹，pos来记录进行到了第几个。当list满员（pos==length）的时候，进行dump（copy）到result。
 * 	一个记录单个排列的list，
 * 	一个放下剩下未选中的rest，
 * 	一个放下全排列的result，
 * 	辅助了一个pos记录放入最后一个位置-- 这个个可以不必要，直接利用java的ArrayList实时跟踪，用size()测量
 *  辅助了一个放满的位置
 */
public class Permutation {
	 public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
	        ArrayList<ArrayList<Integer>> result = 
	            new ArrayList<ArrayList<Integer>>();
	        if (nums == null) {
	            return result;
	        }
	        permuteHelper(nums, result, new ArrayList<Integer>(), nums.size() ,0);
	        return result;
	    }
	    private void permuteHelper(ArrayList<Integer> nums,
	        ArrayList<ArrayList<Integer>> result,
	        ArrayList<Integer> list,
	        int length,
	        int pos) {
	        if (pos == length) {
	            System.out.println("list:"+list);
	            result.add(new ArrayList<Integer>(list));
	            return;
	        }
	        int n = nums.size();
	        for (int i = 0; i < n; i++) {
	        	if (pos < list.size()) {
	        		list.set(pos, nums.get(i));
	        	} else {
	        		list.add(nums.get(i));
	        	}
	            ArrayList<Integer> rest=new ArrayList<Integer>(nums);
	            rest.remove(i);
	            System.out.println("pos:"+pos+",rest:"+rest+",list:"+list);
	            permuteHelper(rest, result, list, length, pos + 1);
	        }
	    }
	    public void permuteHelper2(ArrayList<ArrayList<Integer>> rst, ArrayList<Integer> list, int[] num){
	        if(list.size() == num.length) {
	            rst.add(new ArrayList<Integer>(list));
	            return;
	        }
	        
	        for(int i = 0; i<num.length; i++){
	            if(list.contains(num[i])){
	                continue;
	            }
	            list.add(num[i]);
	            permuteHelper2(rst, list, num);
	            list.remove(list.size() - 1);
	        }
	        
	    }
	    public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> nums) {
	        ArrayList<ArrayList<Integer>> result =
	            new ArrayList<ArrayList<Integer>>();
	        if (nums == null || nums.size() ==0) {
	            return result;
	        }
	        permuteUniqueHelper(result, nums, new ArrayList<Integer>(), nums.size() ,0);
	        return result;
	    }
	    private void permuteUniqueHelper(ArrayList<ArrayList<Integer>> result,
	            ArrayList<Integer> rest,
	            ArrayList<Integer> list,
	            int listSize,
	            int pos) {
	            //if (list.size() == listSize) {// 要换成下面的格式
	            if (pos == listSize) {
	                //System.out.println(list);
	                result.add(new ArrayList<Integer>(list));
	                return;
	            }
	            int n = rest.size();
	            for (int i = 0; i < n; i++) {
	                if (pos < list.size()) {
	                    list.set(pos, rest.get(i));
	                } else {
	                    list.add(rest.get(i));
	                }
	                
	                if (! rest.subList(0, i).contains(rest.get(i))) {
	                    //System.out.println("list:" + list);
	                    ArrayList<Integer> restCopy = new ArrayList<Integer>(rest);
	                    restCopy.remove(i);
	                    permuteUniqueHelper(result, restCopy, list, listSize, pos + 1);
	                }
	                //list.remove(list.size() - 1);-- 不必要了
	            }
	    }
	    public static void main(String[] args){
	    	ArrayList<Integer> nums=new ArrayList<>();
	    	nums.add(0);
	    	nums.add(1);
	    	nums.add(2);
	    	nums.add(3);
	    	System.out.println(nums.subList(0, 2));
	    	nums.contains(1);
	    	ArrayList<ArrayList<Integer>> result = new ArrayList<>();
	    	Permutation pem = new Permutation();
	    	System.out.println(pem.permute(nums));
	    }
}
