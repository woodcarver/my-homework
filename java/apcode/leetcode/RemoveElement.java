package leetcode;
/**
 * No. 27 Remove Element
 * @author xiedandan
 * Object: get the new length ,like [3,2,2,3] 3-->2
 * 约束条件：内存限制，只能原地进行，数据顺序可以改变
 * 分析难点：因为不能复制，所以如果单纯的删除原数组的元素，每次的O(n)，所以结果是O(n*n)--如果有5亿元素，肯定挂
 * 方案0： 题目有盲点，是不是不管原数组真的删除了，我大可以用一遍扫描进行排除统计，也许他会验证nums的长度，别想了，肯定会
 * 方案一：先对数组排序，然后用binary search查找删除，O(n*log(n))。 缺点只为了搜索一个元素排序很浪费
 * 方案二：
 */
import java.util.*;
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
    	if(nums==null || nums.length==0){
    		return 0;
    	}
    	int len=nums.length;
    	int valCounter=0;
    	for(int i=0; i<len; i++){
    		if(nums[i]==val){
    			nums[i]=Integer.MAX_VALUE;
    			valCounter++;
    		}
    	}
    	Arrays.sort(nums);
//    	for(int j=valCounter+1; j<len; j++){
//    		nums[j]=val;
//    	}
        return len-valCounter;
    }
    // two points 要写正确还真难，各种边界情况，特别是=的时候
    // 这种写法太难，边界情况太多
    public int removeElement0(int[] nums, int val){
    	if(nums==null || nums.length==0){
    		return 0;
    	}
    	int valCounter=0;
    	int point1=0;
    	int point2=nums.length-1;//forget to reduce 1
    	while(point1<=point2){
    		// without point1<=point2 condition, it will out of bound
    		// but you should always use different action for the two condition
    		while(point1<=point2 && nums[point1]!=val){
    			point1++;
    		}
    		if(point1>point2){
    			break;
    		}
    		valCounter++;
    		while(point1<point2 && nums[point2]==val){
    			point2--;
    			valCounter++;
    		}
    		if(point1==point2){
    			break;
    		}
    		nums[point1++]=nums[point2--];//就和快速排序中的一样，不然会原地打转
    		
    	}
    	return valCounter;
    }
    public int removeElement1(int[] nums, int val){
    	if(nums==null || nums.length==0){
    		return 0;
    	}
    	int valCounter=0;
    	int point1=0;
    	int point2=nums.length-1;//forget to reduce 1
    	while(point1<=point2){
    		if(nums[point1]==val){
    			valCounter++;
    			while(point1<point2 && nums[point2]==val){
    				point2--;
    				valCounter++;
    			}
    			if(point1==point2){
    				break;
    			}
    			nums[point1]=nums[point2];//就和快速排序中的一样，不然会原地打转
    			point2--;
    		}
    		point1++;
    	}
    	return nums.length-valCounter;
    }
    public int removeElementF(int[] nums, int val) {
        if(nums.length == 0)
        return 0;

        int p1 = 0;
        int p2 = nums.length;
        while(p1!=p2)
        {
            if(nums[p1] != val)
            p1++;
            else
            {
                p2--;
                if(nums[p2] != val)
                nums[p1] =nums[p2];
            }
        }
        return p1;
    }
    //有种插入算法的思想
    public int removeElementFF(int[] nums, int val){
        int index = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != val){
                nums[index++] = nums[i];
            }
        }
        
        return index;
    }
    public void printSum(int[] nums){
    	System.out.println("[START]");
    	for(int i=0; i<nums.length; i++)
    		System.out.printf("%d,",nums[i]);
    	System.out.println("[END]");
    }
    public static void main(String[] args){
    	RemoveElement removeEle=new RemoveElement();
    	int[] nums={3,2,3,2};
//    	int[] nums={1};
//    	int[] nums={};
//    	int[] nums=null;
    	int val=1;
//    	System.out.println(removeEle.removeElement(nums, val));
//    	System.out.println(removeEle.removeElement0(nums, val));
    	System.out.println(removeEle.removeElement1(nums, val));
//    	System.out.println(removeEle.removeElementF(nums, val));
    	
//    	System.out.println(removeEle.removeElement1(nums, 1));
    	removeEle.printSum(nums);
    }
}
