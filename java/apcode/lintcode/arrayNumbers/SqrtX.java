package lintcode.arrayNumbers;

public class SqrtX {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // 1. 二分法
        // 2. 牛顿法？？
        // 7结果是2，但是3，所以sqrt*sqrt必须小于x
        // 999999999 --> middle * middle overflow,
        //      so can not using middle * middle or middle + middle etc.
        int left = 1;
        // int right = x / 2;
        int right = x;
        int mini = right;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (x / middle ==  middle) {
                return middle;
            }
            if (x / middle < middle) {
                right = middle - 1;
            }
            if (x / middle > middle) {
                // System.out.println(left + "," + right + "," + middle + "," + mini);
                int currentGap = x - middle * middle;
                int minGap = x - mini * mini; // mini * mini may overflow and maybe negative, overflow will not change the sign in java
                if (minGap < 0 || currentGap < minGap) {
                    mini = middle;
                }
                left = middle + 1;
            }
        }
        return mini;
    }
}
