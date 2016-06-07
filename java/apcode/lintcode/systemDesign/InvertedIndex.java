package lintcode.systemDesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Create an inverted index with given documents.
 * Example
Given a list of documents with id and content. (class Document)
[
  {
    "id": 1,
    "content": "This is the content of document 1, it's very short"
  },
  {
    "id": 2,
    "content": "This is the content of document 2, it's very long. bilabial bilabial heheh hahaha ..."
  },
]
Return an inverted index (HashMap with key is the word and value is a list of document ids).
{
   "This": [1, 2],
   "is": [1, 2],
   ...
}
 *
 * 重点：
 * 难点：
 * 	1. 字符串处理
 *  2. 分词处理
 *  3. 数据结构设计
 * 错点：[{"id":1,"content":"This is  the content of document1"}, 
 * 		{"id":2,"content":"This is the       content of document3"}]
 * ===> {"This":[1,2],"content":[1,2],"document1":[1],"document3":[2],"is":[1,2],"of":[1,2],"the":[1,2]}
 * but => {"":[1,2],"This":[1,2],"content":[1,2],"document1":[1],"document3":[2],"is":[1,2],"of":[1,2],"the":[1,2]}
 */
class Document {
	public int id;
	public String content;
}
public class InvertedIndex {
    public Map<String, List<Integer>> invertedIndex(List<Document> docs) {
        // 使用空间换时间的方法？？
        Map<String, List<Integer>> invertedIndex = new HashMap<String, List<Integer>>();
        Map<String, Set<Integer>> invertedMap = new HashMap<String, Set<Integer>>();
        if ( docs == null) {
            return invertedIndex;
        }
        for (Document doc : docs) {
            if (doc.content == null || doc.content.equals("")) {
                continue;
            }
            String[] words = doc.content.split(" ");
            for (int i = 0, len = words.length; i < len; i++) {
               if (invertedMap.containsKey(words[i])) {
                   Set<Integer> docList = invertedMap.get(words[i]);
                   docList.add(doc.id);
               } else {
                   Set<Integer> docList = new TreeSet<Integer>();
                   docList.add(doc.id);
                   invertedMap.put(words[i], docList);
               }
            }
        }
        // convert set to list
        for (Map.Entry<String, Set<Integer>> entry : invertedMap.entrySet()) {
            List<Integer> list = new ArrayList<Integer>(entry.getValue());
            invertedIndex.put(entry.getKey(), list);
        }
        return invertedIndex;
    }
}
