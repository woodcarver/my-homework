package lintcode.arrayNumbers;

import java.util.HashSet;

public class SingleNumber {
    /**
     *@param A : an integer array
     *return : a integer 
     */
   public int singleNumber(int[] A) {
       // 1. visited Set to remember occurrence count of every number, 
       //      space and time O(n)
       // 2. sort and find, space O(1), time O(nlogn + n)
       // 3. binary and bit operation
       // version 1
       if (A == null || A.length < 1) {
           return 0;
       }
       int len = A.length;
       HashSet<Integer> visited = new HashSet<Integer>(len / 2 + 1);
       for (int i = 0; i < len; i++) {
           if (visited.contains(A[i])) {
               visited.remove(A[i]);
           } else {
               visited.add(A[i]);
           }
       }
       for (int value : visited) {
           return value;
       }
       return 0;
   }
	// 彻底被折服了
    public int singleNumberJiuzhang(int[] A) {
        if(A == null || A.length == 0) {
            return -1;
        }
        int rst = 0;
        for (int i = 0; i < A.length; i++) {
            rst ^= A[i];
        }
        return rst;
    }
}
