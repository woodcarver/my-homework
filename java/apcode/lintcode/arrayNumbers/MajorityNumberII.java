package lintcode.arrayNumbers;

import java.util.ArrayList;

public class MajorityNumberII {
    /**
     * @param nums: A list of integers
     * @return: The majority number that occurs more than 1/3
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        // majority number more than 1/2, group by 2
        // majority number more than 1/3, group by 3
        // 消除的法则
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int candidate1 = -1;
        int candidate2 = -1;
        int count1 = 0;
        int count2 = 0;
        int len = nums.size();
        // candidate1 == nums.get(i) 必须在 count2 == 0前面，防止candidate1 == candidate2的情况
        // error case: [1,1,1,1,2,2,3,3,4,4,4]
        for (int i = 0; i < len; i++) {
            if (count1 == 0) {
                candidate1 = nums.get(i);
                count1 = 1;
            } else if (candidate1 == nums.get(i)) {
                count1++;
            } else if (count2 == 0) {
                candidate2 = nums.get(i);
                count2 = 1;
            } else if (candidate2 == nums.get(i)) {
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        // check
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (candidate1 == nums.get(i)) {
                count1++;
            } else if (candidate2 == nums.get(i)) {
                count2++;
            }
        }
        // System.out.println(candidate1 + "," + candidate2);
        // System.out.println(count1 + "," + count2 + "," + len);
        int result = count1 > count2 ? candidate1 : candidate2; // 挑出较大的那个
        if (count1 > len / 3 || count2 > len / 3) {
            return result;
        }
        return 0;
    }
}
