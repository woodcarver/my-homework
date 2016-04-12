package lintcode;
/**
 * 这道题简直就是find first position的翻版
 * @author xiedandan
 *
 */
class SVNRepo {
	private final static boolean[] repo = {true, true, true, true, false, false, false, false};
	public static boolean isBadVersion(int k){
		return repo[k];
	}
}
public class FirstBadVersion {
    public int findFirstBadVersion(int n) {
        if (n < 1) {
            return -1;
        }
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            // System.out.println(left + "," + mid + "," + right);
            if (false == SVNRepo.isBadVersion(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // System.out.println(left + "," + SVNRepo.isBadVersion(left));
        if (true == SVNRepo.isBadVersion(left)) {
            return left;
        }
        return -1;
    }
    //思来想去还是这个答案是最健壮的写法
    public int findFirstBadVersion2(int n) {
        int start = 1, end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (SVNRepo.isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
            
        if (SVNRepo.isBadVersion(start)) {
            return start;
        }
        return end;
    }

}
