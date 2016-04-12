package leetcode;
import java.util.*;
/*提示说会有许多的调用sumRange，是否意味着这个函数要十分高效？难道我直接先计算好结果，然后根据range直接返回值？
 *—— 果然是，碰见简单的题一定不能掉以轻心！！！
 *我打败了0.53%的人!!!!
 *
 *知识点：二维矩阵到一维的映射方法，全矩阵和半矩阵的方法
 *
 *看了3ms答案后，我彻底崩溃了！！！！
 */
public class RangeSumQuery {
	private int[] nums;
	private int[] cacheResult;
    public RangeSumQuery(int[] nums) {
        this.nums=nums;
        //generate cacheResult
        int len=nums.length;
        //cacheResult=new int[len*len];//浪费了一半，果然内存溢出了！！！！
        cacheResult=new int[len*(len+1)/2];
//        System.out.println("cache size:"+cacheResult.length);
        for(int i=0; i<len; i++){
        	//when i==j,the first sum of table
//        	cacheResult[i*len+i]=nums[i];
        	int slot=i*len+i-i*(i+1)/2;
        	cacheResult[slot]=nums[i];
//        	System.out.println("start new line:"+i+"--"+(i*len+i-i*(i+1)/2)+":"+cacheResult[i*len+i-i*(i+1)/2]);
        	for(int j=i+1; j<len; j++){
//        		cacheResult[i*len+j]=cacheResult[i*len+j-1]+nums[j];
        		slot=i*len+j-i*(i+1)/2;
        		cacheResult[slot]=cacheResult[slot-1]+nums[j];
//        		System.out.println((i*len+j-i*(i+1)/2)+":"+cacheResult[i*len+j-i*(i+1)/2]);
        	}
        }
    }
    public int sumRange2(int i,int j){
    	int start=Math.min(i, j);
    	int end=Math.max(i, j);
    	if(start<0 || end>=nums.length){
    		return 0;
    	}
    	return cacheResult[start*nums.length+end-start*(start+1)/2];
    }
    public int sumRange(int i, int j) {
        //preposition: 0<=i<=j<nums.size() or 0<=j<=i<nums.size()
    	int len=nums.length;
    	int start=Math.min(i, j);
    	int end=Math.max(i, j);
    	if(start<0 || end>=len){
    		return 0;
    	}
    	int sum=0;
    	for(int k=0; k<len; k++){
    		if(k>=start && k<=end){
    			sum+=nums[k];
    		}
    	}
    	return sum;
    }
    public static void main(String[] args){
//    	int[] nums={-2, 0, 3,-5, 2, -1};
    	int[] nums={};
//    	int[] nums={-2, 0, 3};
//    	RangeSumQuery sumQuery=new RangeSumQuery(nums);
//    	System.out.println(sumQuery.sumRange(0,2));
//    	System.out.println(sumQuery.sumRange(2,5));
//    	System.out.println(sumQuery.sumRange(0,5));
//    	System.out.println(sumQuery.sumRange2(0,2));
//    	System.out.println(sumQuery.sumRange2(2,5));
//    	System.out.println(sumQuery.sumRange2(0,5));
    	
    	NumArray numArray=new NumArray(nums);
    	System.out.println(numArray.sumRange(0,2));
    	System.out.println(numArray.sumRange(2,5));
    	System.out.println(numArray.sumRange(0,5));
    }
}

class NumArray {
	private int[] sums;
    public NumArray(int[] nums) {
        sums=new int[nums.length];
        if(nums.length>0){
            sums[0]=nums[0];
        }
        for(int i=1, len=nums.length;i<len; i++){
            sums[i]=sums[i-1]+nums[i];
        }
    }
    public int sumRange(int i,int j){
        int sum=0;
        //precondition: 0<= i <= j <=size
        if(j<i | i<0 | j>=sums.length)
            return sum;
        
        if(i==0){
            return sums[j];
        }else{
            return sums[j]-sums[i-1];
        }
    }
}
