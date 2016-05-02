package lintcode;

public class PalindromePartitioning {
	// 这个方法还是有问题，abcdecccediobc 这个case测试不通过 --- 改成isPalindrom()函数判断，而不是通过minCuts的差值到了
	// ccaacabacb -- 测试不通过, 当两个回文重叠的时候原先的程序是有问题的，具体版本看lintCode
	// abbab -- 这个也有问题，@@
	// abbaaaa -- 这个结果是2，但期望的应该是1
    public int minCut(String s) {
        // naive thought: at most n - 1 cuts, so the total cut combinations is 2^(n-1).
        // how to optimize it ? find the repeat pices.
        // state: minCut[i] 表示跳到0~i区间中的minCut
        // fucntion: minCut[i] = min{minCut[i], Cut[j] + 1}, j是最后一个找到的回文终点位置
        // initailize: minCut[0] = 0
        // answer: minCut[n]
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] cuts = new int[len + 1];
        cuts[0] = -1;
        
        for (int i = 1; i < len + 1; i++) {
            cuts[i] = cuts[i - 1] + 1;//这个步骤很重要，表示不参与（不破坏）前面的回文
            for (int j = 0; j < i; j++) {
//                 System.out.println("chars: " + s.charAt(i - 1) + "," + s.charAt(j - 1) + ",substring:" + s.substring(j - 1, i));
//                 System.out.println(i + "," + j + "--" + isPalindrome(s.substring(j - 1, i)));
                // 这块粗心把cuts[i - 1] <= cuts[j] + 1 写成了 cuts[i - 1] + 1 <= cuts[j]
                if (isPalindrome(s.substring(j, i))) {
                	System.out.println(j + "," + i + "--" + cuts[j] + "," + cuts[i]);
                	cuts[i] = Math.min(cuts[i], cuts[j] + 1);
//                    break;// 查看abbaaa 这个case，匹配到第一个a不行，必须要等到第二a才行，所以不能break
                }
            }
//            System.out.println("###result:" + i + "," + cuts[i]);
        }
        return cuts[len];
    }
    private boolean isPalindrome(String s) {
    	if (s == null || s.length() == 0) {
    		return true;
    	}
    	for (int i = 0, j = s.length() -1 ; i <= j; i++, j--) {
    		if (s.charAt(i) != s.charAt(j)) {
    			return false;
    		}
    	}
    	return true;
    }
    public static void main(String[] args) {
    	PalindromePartitioning pp = new PalindromePartitioning();
    	String[] arrays = {
//    			null,
//    			"deed",
//    			"abcdecccedi",
//    			"abcdecccediobc",
//    			"ccaac",
//    			"ccaacabacb",
//    			"abbab",
//    			"abcbab",
//    			"abbaa", // 这个和下面这个微妙的关系
    			"abbaaa",
    			""
    	};
    	for(String array : arrays){
    		System.out.println("result:" + pp.minCut(array));
    	}
    }
}
