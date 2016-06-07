package lintcode.arrayNumbers;

public class SortColorsII {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        // counting sort?
        // when k = 2的时候可以用使用一遍partition
        // 当时当k > 2的时候应该使用什么呢?
        // counting sort according by challenge
        if (colors == null || k < 1 || colors.length < k ) {
            return;
        }
        int len = colors.length;
        // counting
        int[] count = new int[k + 1];
        for (int i = 0; i < len; i++) {
            if (colors[i] > k) {
                return;
            }
            count[colors[i]]++;
        }
        // printArray(count);
        // ??
        for (int j = 1; j < k + 1; j++) {
            count[j] += count[j - 1];
        }
        // printArray(count);
        // copy
        // int[] newColors = new int[len];
        for (int j = 0; j < k; j++) {
            for (int t = count[j]; t < count[j + 1]; t++) {
                colors[t] = j + 1; // recovered without consideration
            }
        }
    }
    private void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println("over");
    }
}
