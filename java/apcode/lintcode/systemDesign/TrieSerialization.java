package lintcode.systemDesign;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

// 感觉和B树的表示方式很像
// a,b,c,d...不再是node value，而变成子节点的key
// 直觉上而二叉树不太一样，因为二叉数的left，right，子节点的key是固定的。而搜索是用val来对比，但是trie确实使用子节点的key来做对比
class TrieNode {
	int val; // 可以用于表示trie是否是个完整的词
	public NavigableMap<Character, TrieNode> children;
	public TrieNode() {
		val = 0;
		children = new TreeMap<Character,TrieNode>();
	}
}
public class TrieSerialization {
    private final String BLOCK_START = "(";
    private final String BLOCK_END = ")";
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a trie which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TrieNode root) {
        // dfs,不能再采用levelorder，因为bfs需要dummynode填充才能确认parent-child关系
        // 耗费的dummynode太多，不像binary Tree那么少
        StringBuilder data = new StringBuilder();
        if (root == null) {
            return data.toString();
        }
        serializeHelper(root, data);
        // System.out.println(data.toString());
        return data.toString();
    }
    private void serializeHelper(TrieNode root, StringBuilder data) {
        if (root == null) {
            return;
        }
        data.append(BLOCK_START);
        for (Map.Entry<Character, TrieNode> entry :
            root.children.entrySet()) {
            data.append(entry.getKey());
            serializeHelper(entry.getValue(), data);
        }
        data.append(BLOCK_END);
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TrieNode deserialize(String data) {
        // using dfs to deserialize
        if (data == null || data.length() == 0) {
            return null;
        }
        TrieNode root = new TrieNode();
        deserializeHelper(data, root, 0);
        return root;
    }
    private int deserializeHelper(String data, TrieNode root, int dataPos) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        // System.out.println("this level:" + dataPos + "," +data.charAt(dataPos));
        if (data.charAt(dataPos) != BLOCK_START.charAt(0)) {
            throw new IllegalArgumentException("can not deserialize"); // error! can not deserialize
        } else {
            dataPos++;
        }
        root.children = new TreeMap<Character, TrieNode>();
        int strLen = data.length();
        while (dataPos < strLen && data.charAt(dataPos) != BLOCK_END.charAt(0)) {
            // System.out.println("inside:" + dataPos + "," +data.charAt(dataPos));
            TrieNode child = new TrieNode();
            root.children.put(data.charAt(dataPos++), child);
            dataPos = deserializeHelper(data, child, dataPos);
            // System.out.println("ouside:" + dataPos + "," +data.charAt(dataPos));
        }
        dataPos++;
        return dataPos;
        // return dataPos++; // bug, 注意后自加,写王就这一个bug，不知道是喜是悲
    }
}
