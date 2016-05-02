package lintcode;
/**
 * 这个题的教训就是一定注意区域和坐标不同，特别注意边界的初始化。如果[i][0],[0][j],[0][0]
 * @author xiedandan
 *
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        // state: f(x,y) 表示s3的前i+j是由s1的前i和s2的前j个字符 interleave形成
    	// function: f(x,y) = s1[x-1] == s3[x+y-1] && f(x-1,y) || f(x,y-1)
    	// Initialize: f(i,0)=f(0,j)=true
    	// answer: f(m,n)
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        if (s3.length() != (s1.length() + s2.length())) {
            return false;
        }
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i < s1.length() + 1; i++) {
        	if (s3.charAt(i - 1) == s1.charAt(i - 1)) {
        		dp[i][0] = true;
        	} else {
        		dp[i][0] = false;
        	}
        }
        for ( int j = 1; j < s2.length() + 1; j++) {
        	if (s3.charAt(j - 1) == s2.charAt(j - 1)) {
        		dp[0][j] = true;
        	} else {
        		dp[0][j] = false;
        	}
        }
        dp[0][0] = true; // error case : ["", "", ""]
        printMatrix(dp);
        for (int i = 1; i < s1.length() + 1; i++) {
        	for ( int j = 1; j < s2.length() + 1; j++) {
            	dp[i][j] = false;
//            	System.out.println(s3.charAt(i + j - 1) + "," + s1.charAt(i - 1) + "," + s2.charAt(j - 1));
//            	System.out.println(dp[i - 1][j] + "--" + dp[i][j - 1]);
        		if (s3.charAt(i + j - 1) == s1.charAt(i - 1)) {
        			dp[i][j] = dp[i - 1][j];
        		}
        		if (s3.charAt(i + j - 1) == s2.charAt(j - 1)) {
        			dp[i][j] = dp[i][j] || dp[i][j - 1];
        		}
        		System.out.println(i + "," + j +"--" + dp[i][j]);
        	}
        }
        System.out.println(s1.length()+ "," + s2.length() + "--" + dp[s1.length()][s2.length()]);
        return dp[s1.length()][s2.length()];
    }
    private void printMatrix(boolean[][] matrix) {
    	for (int i = 0; i < matrix.length; i++) {
    		for (int j = 0; j < matrix[i].length; j++) {
    			System.out.print(matrix[i][j] + ",");
    		}
    		System.out.println("$");
    	}
    }
    public static void main(String[] args) {
    	InterleavingString ils = new InterleavingString();
//    	String s1 = "aafaa";
//    	String s2 = "cb";
//    	String s3 = "acbaaaf";
    	String s1 = "a";
    	String s2 = "";
    	String s3 = "a";
    	System.out.println("Result:" + ils.isInterleave(s1, s2, s3));
    }
}
