package lintcode;
/**
 * 
 * @author xiedandan
 *错误记录
居然写出了死循环：
if (source.charAt(source_pos) != target.charAt(target_pos)) {
	source_pos = source_pos - target_pos + 1;
	target_pos = 0;
}
在["source", "target"]会陷入死循环。
居然还是忘记判断数组是否为空了，太紧张了，看见时间限制就急
 */

public class StrStr {
    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        //precondition: source, target is not null
        if (source == null || target == null) {
            return -1;
        }
        //precondition: 0<=sourcePos<source.length() && 
        //              0<=targetPos<target.length()
        int sourcePos = 0;
        int targetPos = 0;
        //invariant: 
        while (sourcePos < source.length() && targetPos < target.length()) {
            if (source.charAt(sourcePos) != target.charAt(targetPos)) {
                sourcePos = sourcePos - targetPos + 1;
                targetPos = 0;
            } else {
                sourcePos++;
                targetPos++;
            }
        }
        //post: sourcePos==source.length() || targetPos==target.length()
        if (targetPos == target.length()) {
            return sourcePos - targetPos;
        } else {
            return -1;
        }
    }
    public int strStr2(String source, String target) {
        if (source == null || target == null) {
             return -1;
        }
        //["", ""], 边界检查，没有考虑到target.length=source.length=0的情况， 再次提醒一定要检查循环的覆盖范围！！！！
        if (target.length() == 0) {
            return 0;
        }
        for (int i = 0; i < source.length(); i++) {
            int j;
            for (j = 0; j < target.length(); j++) {
                if (source.charAt(i+j) != target.charAt(j)) {
                    break;
                }
            }
            //post: source.charAt(i+j) != target.charAt(j) || j==target.length()
            if (j == target.length()) {
                return i;
            }
        }
        return -1;
     }
}
