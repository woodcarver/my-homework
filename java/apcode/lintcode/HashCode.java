package lintcode;

public class HashCode {
    public int hashCode(char[] key,int HASH_SIZE) {
        // write your code here
        if (key == null || HASH_SIZE < 1) {
            return 0; //???
        }
        if (key.length == 0) {
            return 0; //???
        }
        // 果然会溢出： "Wrong answer or accepted?", 1000000007 --》 
        //              -91702745， expected：382528955
        // -1 % 5 = -1 ==> (-1 + 5) % 5
        long code = (int) key[0]; // 为什么保持long就可以保证结果一定是整数呢？
        // 估计是限制了HASH_SIZE是int，所以 2^32 * 2^32 = 2^64, 而HASH_SIZE 是int, (code)结果也不会大于HASH_SIZE
        // code * 33 < 2^32 * 33 ,code * 33 + 2^32 < 2^32 * 33 +  2^32 = 2^32 * 34 <  2^64
        // 所以不会溢出
        
        // int code = (int) key[0];
        for (int i = 1, len = key.length; i < len; i++) {
            code = (code * 33 + (int)key[i]) % HASH_SIZE;
            // System.out.println(code);
        }
        return (int) code;
    }
}
