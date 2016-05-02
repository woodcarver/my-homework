package lintcode;
/**
 * 这个题是重中之重，犯的错误非常多，而且涉及到Two pointer中的边界情况控制也很tricky。
 * @author xiedandan
 * 对于这种while中嵌入while，想办法变成一层while。即每次直走一个步，绝不多走，会简单很多。
 */
public class SortColors {
	/**
	 * case error:
	 * [2,0,0,1,2,0,2] 为什么把red和blue的while放在外层，这个case会陷入死循环？？？？
	 * 因为这样不能保证white > red, 所以结果会错误，但是也不至于死循环呢！
	 * 又是陷入泥潭出不来啊！！！这个case提交了3次，都不过
	 */
    public void sortColors1(int[] nums) {
        // 思路：quick sort中的2way partition
        if (nums == null || nums.length == 0) {
            return;
        }
        int red = 0;
        int white = 0;
        int blue = nums.length - 1; // bug!!! forget -1
        int poivt = 1;
        // move red
        while (red < nums.length && nums[red] < poivt) {
            red++;
        }
        white = red;
        // move blue
        while (blue >= 0 && nums[blue] > poivt) {
            blue--;
        }
        while (white <= blue) { // must be <= not <, for must checker the last number
            while (white <= blue) {
                int temp;
                if (nums[white] < poivt) {
                    temp = nums[white];
                    nums[white] = nums[red];
                    nums[red] = temp;
                    red++;
                } else if (nums[white] > poivt){
                    temp = nums[white];
                    nums[white] = nums[blue];
                    nums[red] = temp; // 原来死循环的bug在这里，是nums[blue] not nums[red]
                    blue--;
                } else {
                    white++;
                }
            }
        }
    }
    public void sortColors(int[] nums) {
        // 思路：quick sort中的2way partition
        if (nums == null || nums.length < 1) {
            return;
        }
        int red = 0;
        int white = 0;
        int blue = nums.length - 1; // bug!!! forget -1
        int poivt = 1;
        while (white <= blue) { // must be <= not <, for must checker the last number
            // move red
            while (red < nums.length && nums[red] < poivt) {
                red++;
            }
            if (white < red) {
                white = red;
            }
            // move blue
            while (blue >= 0 && nums[blue] > poivt) {
                blue--;
            }
            if (red == nums.length || white ==  nums.length || blue == 0) {
                return;
            }
            // System.out.println(red + "," + white + "," + blue);
            // while (white <= blue) {
            int temp;
            if (nums[white] < poivt) {
                temp = nums[white];
                nums[white] = nums[red];
                nums[red] = temp;
                // red++;
            } else if (nums[white] > poivt){
                temp = nums[white];
                nums[white] = nums[blue];
                nums[blue] = temp;
                // blue--;
            } else {
                white++;
            }
            // }
        }
    }
}
/**
 * 简直无语了，答案怎么可以这么简单！！！！
 */
class JiuzhangSortColors {
    public void sortColors(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        
        int pl = 0;
        int pr = a.length - 1;
        int i = 0;
        while (i <= pr) {
            if (a[i] == 0) {
                swap(a, pl, i);
                pl++;
                i++; // 这个地方i++真的没有问题吗？怎么可以保证被交换过来的前red
            } else if(a[i] == 1) {
                i++;
            } else {
                swap(a, pr, i);
                pr--;
            }
        }
    }
    
    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}