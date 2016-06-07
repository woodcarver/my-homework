package lintcode.systemDesign;

public class Anagram {
    private String signature(String str) {
        // 假设输入字符只有26个因为字母
        int[] counter = new int[26];
        int len = str.length();
        for (int i = 0; i < len; i++) {
            counter[str.charAt(i) - 'a']++;
        }
        StringBuilder reorderStr = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            while (counter[i] > 0) {
                reorderStr.append((char)(i + 'a'));
                counter[i]--;
            }
        }
        return reorderStr.toString();
    }
    public static void main(String[] args) {
    	Anagram anagram = new Anagram();
    	System.out.println(anagram.signature("bacaa"));
    }
}
