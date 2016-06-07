package lintcode.systemDesign;

import java.util.HashMap;

/**
 * 
Given a long url, make it shorter. To make it simpler, let's ignore the domain name.

You should implement two methods:

longToShort(url). Convert a long url to a short url.
shortToLong(url). Convert a short url to a long url starts with http://tiny.url/.
You can design any shorten algorithm, the judge only cares about two things:

The short key's length should equal to 6 (without domain and slash). And the acceptable characters are [a-zA-Z0-9]. For example: abcD9E
No two long urls mapping to the same short url and no two short urls mapping to the same long url.

Example
Given url = http://www.lintcode.com/faq/?id=10, run the following code (or something similar):

short_url = longToShort(url) // may return http://tiny.url/abcD9E
long_url = shortToLong(short_url) // return http://www.lintcode.com/faq/?id=10
The short_url you return should be unique short url and start with http://tiny.url/ and 6 acceptable characters. For example "http://tiny.url/abcD9E" or something else.

The long_url should be http://www.lintcode.com/faq/?id=10 in this case.
 *
 *
 *关于这个题的sharding问题，和其id的怎么设计：
 *http://instagram-engineering.tumblr.com/post/10853187575/sharding-ids-at-instagram
 *http://mp.weixin.qq.com/s?__biz=MzA5MzE4MjgyMw==&mid=2649454808&idx=1&sn=dee934fccd140f2456f32b6a225b2372&scene=23&srcid=0521oymbUOPByn1Jc1LJrFPt#rd
 *
 *仔细想了下Tiny Url这个系统过后，发现网上的二维码系统也是这个原理，所以必须要联网才行
 *
 *http://srinathsview.blogspot.com/2012/04/generating-distributed-sequence-number.html
 */
public class TinyUrl {
    // sharding key is ids
    private HashMap<Long, String> urls = new HashMap<Long, String>();
    private long lastId = 0; // must long, because int 2^23 = 4 billion = 40亿，62^6 = 150亿
    private String domain = "http://tiny.url/";
    
    // 经典，把逻辑封装起来
    private String getShortKey(String url) {
        return url.substring(domain.length());
    }
    private String wrapShortKey(String shortKey) {
        return domain + shortKey;
    }
    /**
     * @param url a long url
     * @return a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
    	// 这个理由个漏洞，就是可能用户会添加相同的url
    	if (urls.containsKey(url)) {
    		//do something to find, need another hashmap longtoshort
    	}
        long shortId = lastId++;
        urls.put(shortId, url);
        return wrapShortKey(IdToShortKey(shortId));
    }

    /**
     * @param url a short url starts with http://tiny.url/
     * @return a long url
     */
    public String shortToLong(String url) {
        String shortKey = getShortKey(url);
        long shortId = shortKeyToId(shortKey);
        if (urls.containsKey(shortId)) {
            return urls.get(shortId);
        } else {
            return "Wrong short url"; // talk with interviewer
        }
    }
    // 26+26+10=62,62进制
    private String IdToShortKey(long id) {
        if (id < 0) {
            return null;
        }
        int slotSize = 6;
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] shortKey = new char[slotSize];
        for (int i = slotSize - 1; i >= 0; i--) {
            long slot = id - (id / 62) * 62;
            shortKey[i] = chars.charAt((int)slot);
            id = id / 62;
        }
        return new String(shortKey);
    }
    private long shortKeyToId(String str) {
        if (str == null) {
            return -1;
        }
        int id = 0;
        for (int i = 0, len = str.length(); i < len; i++) {
            id = id * 62 + toBase62(str.charAt(i));
        }
        return id;
    }
    // 对于char ，stirng，一定要考虑的是全集，用户什么输入都可能
    // 对于int，long，doule等都要考虑
    private int toBase62(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 36;
        }
        // unknow character
        throw new IllegalArgumentException();
    }
    
    public static void main(String[] args) {
    	TinyUrl tu = new TinyUrl();
    	System.out.println(tu.longToShort("http://www.lintcode.com/faq/?id=10"));
    	System.out.println(tu.shortToLong("http://tiny.url/000000"));
    	System.out.println(tu.longToShort("http://www.google.com"));
    	System.out.println(tu.shortToLong("http://tiny.url/000001"));
    }
}
