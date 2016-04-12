package leetcode;
/**
 * NO. 26
 * @author xiedandan
 * 和RemoveElement的一种解决方法非常相似
 * 这两道题都在提示用binary index tree(Fenwick trees)
 * http://cs.stackexchange.com/questions/10538/bit-what-is-the-intuition-behind-a-binary-indexed-tree-and-how-was-it-thought-a
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
    	if(nums==null || nums.length==0){
    		return 0;
    	}
        int index=0;//首先必须让进入一个作为对比基数， 演绎法的base
        for(int i=1, len=nums.length; i<len; i++){
        	if(nums[i]!=nums[index]){
        		nums[++index]=nums[i];
        	}
        }
        return index+1;
    }
    public void printSum(int[] nums){
    	System.out.println("[START]");
    	for(int i=0; i<nums.length; i++)
    		System.out.printf("%d,",nums[i]);
    	System.out.println("\n[END]");
    }
    public static void main(String[] args){
    	int[] nums=null;
//    	int[] nums={};
//    	int[] nums={3,3,4,5,6};
    	RemoveDuplicatesFromSortedArray rdsa=new RemoveDuplicatesFromSortedArray();
    	System.out.println(rdsa.removeDuplicates(nums));
    	rdsa.printSum(nums);
    }
}
