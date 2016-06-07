package lintcode.binarySearch;

import java.util.Arrays;

/*
 * Given n pieces of wood with length L[i] (integer array). 
 * Cut them into small pieces to guarantee you could have equal 
 * or more than k pieces with the same length. 
 * What is the longest length you can get from the n pieces of wood? 
 * Given L & k, return the maximum length of the small pieces.
 * Example:
 * For L=[232, 124, 456], k=7, return 114.
 * 考虑到情况，k的个数小于L的给出的长度，则返回第L.len - k大
 * 
 * 错误case：
 * {2, 3, 2, 2, 2, 2, 1} 7 - expected 2 ，but 1 —— 没有考虑到重复元素的情况
 * {2, 4, 2, 2, 2, 2, 1} 7 - expected 2, but 1 -- 不能直接判断L.len > k，就直接返回第k大的数
 * 时间复杂度: 每次counte就增加一个，而每次都需要耗费2n的时间，所以是O(k*n)
 */
public class WoodCut {
    public int woodCut(int[] L, int k) {
        // 看到longest  length想到的就是DP, 不是
        // state: f[min] 表示当段长为min的时候，能切几块
        //        cut[i] 表示第几根木头被切了几块
        //        当发现f[min] < k的时候，寻找一个增加一块后最大长度的那个段长作为新的min
    	//   发现叙述不清楚，一点用处都没有   
        if (L == null || L.length == 0 || k <= 0) {
        	// L.length == 0 没有检查到，大失误，参数传入的时候一定把所有的情况都要考虑到，
        	//不然 程序会崩溃
            return 0;
        }
        Arrays.sort(L);
//        if (k <= L.length) { // 这个地方是错误的，应该是返回第k大，不二不是第k小
//        	return L[L.length - k];
//        }
        int[] cuts = new int[L.length];
//        for (int i = 0; i < L.length && i < k; i++) {
//        	cuts[i] = 1;
//        }
//        int counter = L.length;
        int counter = 0;
        int max = 0;
        while(counter < k) {
        	max = 0;
//        	int selected = 0;
        	for (int i = 0; i < L.length; i++) {
        		int cutLen = L[i] / (cuts[i] + 1);
        		if (cutLen > max) {
//        			selected = i;
        			max = cutLen;
        		}
//        		System.out.println(cutLen);
        	}
//        	cuts[selected] += 1;
//        	counter += 1;
        	// 没有预想到L可能不够k分的情况，即max==0的情况	
        	if (max == 0) {
        		return 0;
        	}
        	counter = caluateCounter(L, cuts, max);
        }
        return max;
    }
    private int caluateCounter(int[] L, int[] cuts, int max) {
    	int counter = 0;
    	for (int i = 0; i < L.length; i++) {
    		cuts[i] = L[i] / max;
    		counter += cuts[i];
    	}
    	return counter;
    }
    public static void main(String[] args) {
    	WoodCut wt = new WoodCut();
    	int[][] arrays = {
//    			null,
    			{232, 124, 456},
    			{2, 4, 2, 2, 2, 2, 1},
    			{2,2,1},
    			{1,1,1,100},
    			{1, 2, 6, 3, 4, 5, 7, 6},
    			{}
    	};
        int k = 7;
    	for(int[] array : arrays){
    		System.out.println("result:" + wt.woodCut(array, k));
    	}
    }
}
