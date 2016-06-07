package lintcode.systemDesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * error case:
 * dict=["Tree","Lint Code","Problem","Run Code","Fizz Buzz","Binary Tree","Binary Tree Sum"]
search("e")
search("ee")
search("zz")
 * 第一没考虑到了key值重复
 * 第二选取的substring的时候，边界值不对，导致最后一个字母入不了候选集
 *
 */
public class Typeahead {
    private HashMap<String, List<String>> dictMap =
            new HashMap<String, List<String>>();
        // @param dict A dictionary of words dict
        public Typeahead(Set<String> dict) {
            // data structure，字典难道要把所有的subString存储一遍？
            // 感觉是面向字符串建立一个索引树啊
            // 时间O(m*n^2)、内存空间肯定占用的非常厉害，O(m*n^2)
            if (dict == null) {
                return;
            }
            for (String sentence : dict) {
                // System.out.println(sentence);
                int strLen = sentence.length();
                for (int i = 0; i < strLen; i++) {// 注意是i < strLen
                    for (int j = i; j < strLen; j++) {// 注意是j = i
                        String key = sentence.substring(i, j + 1);
                        // System.out.println(i + ","+ j + ","  + key);
                        if (!dictMap.containsKey(key)) {
                            dictMap.put(key, new ArrayList<String>());
                            dictMap.get(key).add(sentence);
                        } else {
                            List<String> list = dictMap.get(key);
                            //exclude duplicate sentence
                            if (list.get(list.size() - 1).equals(sentence)) {
                                continue;
                            }
                            dictMap.get(key).add(sentence);
                        }
                    }
                }
            }
        }

        // @param str: a string
        // @return a list of words
        public List<String> search(String str) {
            // 并非前缀查询？
            if (str == null || str.length() == 0) {
                return new ArrayList<String>();
            }
            if (dictMap.containsKey(str)) {
                return dictMap.get(str);
            }
            return new ArrayList<String>();
        }
}
