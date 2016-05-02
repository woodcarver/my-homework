package lintcode;
/**
 * 模板
 * ￼￼state: f[x]表示x点是否能走到终点
 * function: f[x] = OR (f[x - i] && A[x - i]>=i)
 * initialize: f[x] = -1
 * answer: f[0] == true
 * 
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 * @author xiedandan
 *
 */
public class JumpGame {
	public boolean canJumpBruteFource(int[] A) {
		if (A.length == 0) {
			return true;
		}
		return canJumpBruteFourceHelper(A, 0);
	}
    private boolean canJumpBruteFourceHelper(int[] A, int jumpPos) {
        //Divide & conquer
    	if (jumpPos == A.length - 1) {
    		return true;
    	}

    	for (int i = 1; i < A[jumpPos]; i++) {
    		if (canJumpBruteFourceHelper(A, jumpPos + i)) {
    			return true;
    		}
    	}
    	return false;
    }
	public boolean canJumpRDP(int[] A) {
		if (A.length == 0) {
			return true;
		}
		int[] jumps = new int[A.length];
		for (int i = 0, len = A.length; i < len; i++ ) {
			jumps[i] = -1;
		}
		jumps[A.length - 1] = 1;
		return canJumpRDPHelper(A,jumps,0);
	}
    public boolean canJumpRDPHelper(int[] A,int[] jumps, int jumpPos) {
        //Divide & conquer
    	if (jumpPos == A.length - 1) {
    		return true;
    	}
    	//防止打转
    	if (A[jumpPos] == 0) {
    		jumps[jumpPos] = 0;
    		return false;
    	}
    	if (jumps[jumpPos] == -1) {
	    	for (int i = 1; i <= A[jumpPos]; i++) {
	    		if (jumps[jumpPos + i] == 0) {
	    			continue;
	    		}
	    		if (jumps[jumpPos + i] == 1 || canJumpRDPHelper(A, jumps, jumpPos + i)) {
		    		jumps[jumpPos] = 1;
		    		return true;
	    		}
	    	}
	    	jumps[jumpPos] = 0;
    	}
    	
    	return jumps[jumpPos] == 1 ? true : false;
    }
   public boolean canJump(int[] A) {
		if (A.length == 0) {
			return true;
		}
		// initial
		boolean[] jumps = new boolean[A.length];
//		for (int i = 0, len = A.length; i < len; i++ ) {
//			jumps[i] = 0;
//		}
		jumps[A.length - 1] = true;
		// function
		for (int len = A.length, i = len -1; i >= 0; i--) {
		  //  System.out.println(i);
		    for (int j = 0; j <= A[i] && i + j < len; j++) {
		        if (jumps[i + j]) {
		            jumps[i] = true;
		            continue;
		        }
		    }
		}
		// answer: jumps[0] == 1
		return jumps[0];
   }
}
