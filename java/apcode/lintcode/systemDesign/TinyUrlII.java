package lintcode.systemDesign;

import java.util.HashMap;

/*
 * 一句话，不容易。。。。
 * 
 * createCustom("http://www.lintcode.com/", "lccode")
longToShort("http://www.lintcode.com/problem/")
shortToLong("http://tiny.url/lccode")
createCustom("http://www.lintcode.com/", "lc")
createCustom("http://www.lintcode.com/en/ladder/", "lccode") -- > lccode已经被占用，所以还要检查shortkey是否被占用


createCustom("http://www.lintcode.com/p1", "000001")
createCustom("http://www.lintcode.com/p2", "000002")
createCustom("http://www.lintcode.com/p3", "000003")
createCustom("http://www.lintcode.com/p4", "000004")
longToShort("http://www.lintcode.com/problem/")
shortToLong("http://tiny.url/000002")
shortToLong("http://tiny.url/000004")
createCustom("http://www.facebook.com", "facebook")
createCustom("http://www.facebook.com", "facebook")
createCustom("http://www.google.com", "google")
createCustom("http://www.lintcode.com", "lc")
shortToLong("http://tiny.url/lc")
shortToLong("http://tiny.url/google")
createCustom("http://www.google.com", "google")
shortToLong("http://tiny.url/facebook")
longToShort("http://www.lintcode.com")
longToShort("http://www.google.com")
longToShort("http://www.facebook.com")
 * 
 * 
 * 这道题真是不简单，各种情况检查
 */
public class TinyUrlII {
	 /* 
     * 不牵扯到sharding
     * 其实我觉得一个long_url对应多个id是没有问题的，所不需要long2id这个map是可行的
     * long --> short 只是写
     * short --> long 只是查
     */
    private HashMap<String, Long> long2Id =
        new HashMap<String, Long>(); // for duplicate long_url to id
    private HashMap<Long, String> id2Long =
        new HashMap<Long, String>();
    private HashMap<String, String> customLong2ShortKey =
        new HashMap<String, String>();
    private HashMap<String, String> customShortKey2Long =
        new HashMap<String, String>();    
    private String domain = "http://tiny.url/";
    private long GLOBAL_ID = 1;

    private String wrapShortKey(String shortKey) {
        // if (shortKey == null) {
        //     throw new IllegalArgumentException();
        // }
        return domain + shortKey;
    }
    private String decodeShortKey(String shortUrl) {
        // if (shortUrl == null) {
        //     throw new IllegalArgumentException();
        // }
        return shortUrl.substring(domain.length());
    }
    private boolean isNormalShortKey(String shortKey) {
        if (shortKey.length() != 6) {
            return false;
        }
        for (int i = 0; i < 6; i++) {
            char c = shortKey.charAt(i);
            if (c >= '0' && c <= '9') {
                continue;
            }
            if (c >= 'a' && c <= 'z') {
                continue;
            }
            if (c >= 'A' && c <= 'Z') {
                continue;
            }
            return false;
        }
        return true;
    }
    /**
     * @param long_url a long url
     * @param a short key
     * @return a short url starts with http://tiny.url/
     */
    public String createCustom(String longUrl, String shortKey) {
    	if (longUrl == null || shortKey == null) {
    		throw new IllegalArgumentException();
    	}

        if (isNormalShortKey(shortKey)) {
            long id = shortKey2Id(shortKey);
            /*
             * longToShort("http://www.lintcode.com/p7")
             * createCustom("http://www.lintcode.com/p1", "000001")
             */
            if (long2Id.containsKey(longUrl) && long2Id.get(longUrl) != id) {
            	// this id is used by other long url
                return "error";
            }
            if (id2Long.containsKey(id) && !id2Long.get(id).equals(longUrl)) {
            	// 此longurl已经被占用
                return "error";
            }
            // 怎么保证custom的ordinary的key不冲突
            if (id2Long.containsKey(id) || long2Id.containsKey(longUrl)) {
                return wrapShortKey(shortKey);
            }
        }
        if (customLong2ShortKey.containsKey(longUrl) && !customLong2ShortKey.get(longUrl).equals(shortKey)) {
        	return "error";
        }
        if (customShortKey2Long.containsKey(shortKey) && !customShortKey2Long.get(shortKey).equals(longUrl)) {
        	return "error";
        }
        /* 
         * createCustom("http://www.lintcode.com/", "lccode")
		 * createCustom("http://www.lintcode.com/en/ladder/", "lccode")
         */
//        if (customLong2ShortKey.containsKey(longUrl) ||
//            customShortKey2Long.containsKey(shortKey)) {
//            if (!customLong2ShortKey.get(longUrl).equals(shortKey)) {
//        	    return "error";
//            }
//        }
        customShortKey2Long.put(shortKey, longUrl);
        customLong2ShortKey.put(longUrl, shortKey);
        // long2Id.put(longUrl, (long)0); // value 0 represent custom url
        return wrapShortKey(shortKey);
    }

    /**
     * @param long_url a long url
     * @return a short url starts with http://tiny.url/
     */
    public String longToShort(String longUrl) {
        if (longUrl == null) {
            throw new IllegalArgumentException();
        }
        if (customLong2ShortKey.containsKey(longUrl)) {
            return wrapShortKey(customLong2ShortKey.get(longUrl));
        }
        if (long2Id.containsKey(longUrl)) {
            return wrapShortKey(id2ShortKey(long2Id.get(longUrl)));
        }
        long id = GLOBAL_ID++;
        while (customShortKey2Long.containsKey(id2ShortKey(id))) {
            id = GLOBAL_ID++;
        }
        id2Long.put(id, longUrl);
        long2Id.put(longUrl, id);
        return wrapShortKey(id2ShortKey(id));
    }

    /**
     * @param short_url a short url starts with http://tiny.url/
     * @return a long url
     */
    public String shortToLong(String shortUrl) {
        if (shortUrl == null) {
            throw new IllegalArgumentException();
        }
        String shortKey = decodeShortKey(shortUrl);
        // searching customId2Long first
        if (customShortKey2Long.containsKey(shortKey)) {
            return customShortKey2Long.get(shortKey);
        }
        long id = shortKey2Id(shortKey);
        return id2Long.get(id);
    }
    private String id2ShortKey(long id) {
        if (id < 0) {
            return "000000";
        }
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] shortKey = new char[6];
        for (int i = 5; i >= 0; i--) {
            // last slot
            shortKey[i] = chars.charAt((int)(id - id / 62 * 62));
            id /= 62;
        }
        return new String(shortKey);
    }
    private long shortKey2Id(String shortKey) {
        if (shortKey == null) {
            throw new IllegalArgumentException();
        }
        long id = 0;
        for (int i = 0, len = shortKey.length(); i < len; i++) {
            id = id * 62 + toBase62(shortKey.charAt(i));
        }
        return id;
    }
    private long toBase62(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 36;
        }
        throw new IllegalArgumentException("can not parse this character");
    }
    
    public static void main(String[] args) {
    	TinyUrlII tu = new TinyUrlII();
//    	System.out.println(tu.longToShort("http://www.lintcode.com/problem/"));
//    	System.out.println(tu.createCustom("http://www.lintcode.com/", "lccode"));
//    	System.out.println(tu.createCustom("http://www.lintcode.com/en/ladder/", "lccode"));
    	System.out.println(tu.longToShort("http://www.lintcode.com/p7"));
    	System.out.println(tu.createCustom("http://www.lintcode.com/p1", "000001"));
//    	System.out.println(tu.createCustom("http://www.google.com/", "g.ccn"));
//    	System.out.println(tu.createCustom("http://www.lintcode.com/", "lccode"));
//    	System.out.println(tu.shortToLong("http://tiny.url/lccode"));
//    	System.out.println(tu.longToShort("http://www.lintcode.com/faq/?id=10"));
//    	System.out.println(tu.shortToLong("http://tiny.url/000001"));
//    	System.out.println(tu.longToShort("http://www.google.com"));
//    	System.out.println(tu.shortToLong("http://tiny.url/000002"));
    }
}
