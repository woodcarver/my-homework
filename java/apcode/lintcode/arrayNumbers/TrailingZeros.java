package lintcode.arrayNumbers;

import java.util.TreeMap;

public class TrailingZeros {
    /*
     * param n: As desciption
     * return: An integer, denote the number of trailing zeros in n!
     */
    public long trailingZerosTimeoutVersion(long n) {
        // 其实就是找1~n中5的倍数（偶数的数据量 >> 5的倍数），10的倍数
        // 因为就是求解末尾的零的个数
        // 因式中更有多少个5
        if (n < 5) {
            return 0;
        }
        // int factorial = 1;
        // for (int i = 2; i <= n; i++) {
        //     factorial *= i;
        // }
        int zeroCount = 0;
        for (int i = 5; i <= n; i += 5) {
            // i = 0, 错误，因为num % 5 == 0 会陷入死循环
            int num = i;
            // System.out.println("num:" + num);
            // 30 / 5 = 6 > 4,但是不是5的倍数了
            while ((num % 5) == 0) {
                zeroCount++;
                num = num / 5;
            }
        }

        return zeroCount;
    }
    
    /*
     * param n: As desciption
     * return: An integer, denote the number of trailing zeros in n!
     */
    public long trailingZerosWrong(long n) {
        // 其实就是找1~n中5的倍数（偶数的数据量 >> 5的倍数），10的倍数
        // 因为就是求解末尾的零的个数
        // 因式中更有多少个5
        // 超时：5555550000000
        // 朴素法超时，感觉可以构建一个DP，因为发现每次计算num里含多少5有重复
        // state: f(n) --> n contains x 5 factors
        // function: f(n+5) = f((n + 5) / 5) + 1
        // initalization: f(n<5) = 0, f(5) = 1
        // DP做不下去，funciton是不对的，并且我也没办法证明其是对的
        if (n < 5) {
            return 0;
        }
        // int factorial = 1;
        // for (int i = 2; i <= n; i++) {
        //     factorial *= i;
        // }
        int result = 0;
        // num, 5s
        TreeMap<Integer, Integer> zeroCount = new TreeMap<Integer, Integer>();
        zeroCount.put(1, 1);
        // zeroCount.put(5, 1);
        for (int i = 5; i <= n; i += 5) {
            // i = 0, 错误，因为num % 5 == 0 会陷入死循环
            // int num = i;
            // 30 / 5 = 6 > 4,但是不是5的倍数了
            // while ((num % 5) == 0) {
            //     zeroCount++;
            //     num = num / 5;
            // }
            int current = zeroCount.floorEntry(i / 5).getValue();
            if ((i / 5) % 5 == 0) {
                current++;
            }
            zeroCount.put(i, current);
            if (i > 9900) {
                System.out.println("num:" + i + "," + current);
            }
            result += current;
        }

        return result;
    }
}
