package lintcode;

import java.util.TreeMap;
import java.util.Arrays;
import java.util.Comparator;

public class SubarraySumClosest {
	// error case: [15,0]
    public int[] subarraySumClosest1(int[] nums) {
        // 思路：方法一 —— 暴力，O(n^2) --- 超时!!!
        //      方法二 —— prefixSum，怎么样是O(n^2)呢？？
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            return result; // At least contain one element?
        }

        // 果然不能用此数据结构，因为Map不容易一个key对应多个值
        TreeMap<Integer, Integer> prefixSums =
            new TreeMap<Integer, Integer>();
            
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            System.out.println("prefix:" + sum + "," + i);
            prefixSums.put(sum, i);
        }
        int closestSum = Integer.MAX_VALUE;
        java.util.Map.Entry<Integer, Integer> prev = null;
        for (java.util.Map.Entry<Integer, Integer> item : prefixSums.entrySet()) {
            System.out.println(item.getKey() + "," + item.getValue());
            if (prev == null) {// bug!!! == 写成了 =
                prev = item;
                continue;
            }
            if (Math.abs(item.getKey() - prev.getKey()) < Math.abs(closestSum)) {
                closestSum = item.getKey() - prev.getKey();
                result[0] = prev.getValue() + 1;
                result[1] = item.getValue();
            }
        }
        return result;
    }
    public int[] subarraySumClosest(int[] nums) {
        // 思路：方法一 —— 暴力，O(n^2) --- 超时!!!
        //      方法二 —— prefixSum，怎么样是O(n^2)呢？？
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            return result; // At least contain one element?
        }

        // 果然不能用此数据结构，因为Map不容易一个key对应多个值
        Pair[] prefixSums =
            new Pair[nums.length + 1];
        prefixSums[0] = new Pair(0, -1);
        int sum = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum += nums[i - 1];
            // System.out.println("prefix:" + sum + "," + (i));
            prefixSums[i] = new Pair(sum, i - 1);
        }
        Arrays.sort(prefixSums, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.sum - b.sum;
            } 
        });
        int closestSum = Integer.MAX_VALUE;
        Pair prev = prefixSums[0];
        for (int i = 1; i <= nums.length; i++) {
            Pair item = prefixSums[i];
            // System.out.println(item.sum + "," + item.index + ",closest:" + closestSum);
            if (Math.abs(item.sum - prev.sum) <= Math.abs(closestSum)) {
            // if (item.sum - prev.sum < closestSum) {
                closestSum = item.sum - prev.sum;
                // 这个地方够一定注意，因为sort之后重排顺序了
                int prevIndex = Math.min(prev.index, item.index);
                int postIndex = Math.max(prev.index, item.index);
                result[0] = prevIndex + 1;
                result[1] = postIndex;
            }
            prev = item;
        }
        return result;
    }
    class Pair {
        int sum;
        int index;
        public Pair(int sum, int index) {
            this.sum = sum;
            this.index = index;
        }
    }
}

