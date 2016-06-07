package lintcode.arrayNumbers;

public class CheckPowerOf2 {
    public boolean checkPowerOf2Jiuzhang(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n-1)) == 0;
    }
    public boolean checkPowerOf2Me(int n) {
        // n = 2^x?
        // binary base: 10,100,1000...1000000 , only has one number 1.
        // O(32) -- O(1)
        // how to do with negative number? (-,0,+)
        if (n <= 0) {
            // n = n * -1;
            return false;
        }
        int count = 0;
        while (n != 0) {
            if ((n & 1) != 0) {
                count++;
            }
            n = n >> 1;
        }
        if (count > 1) {
            return false;
        }
        return true;
    }
}
