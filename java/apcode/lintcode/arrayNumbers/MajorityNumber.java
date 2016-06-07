package lintcode.arrayNumbers;

import java.util.ArrayList;

public class MajorityNumber {
    /**
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        // equal to or less than half, return what?
        if (nums == null || nums.size() == 0) {
            return -1;
        }
        int candidate = -1;
        int count = 0;
        for (int i = 0, size = nums.size(); i < size; i++) {
            if (count == 0) {
                candidate = nums.get(i);
                count = 1;
            } else if (candidate == nums.get(i)) {
                count++;
            } else {
                count--;
            }
        }
        count = 0;
        // check if candidate is majority number
        for (int i = 0, size = nums.size(); i < size; i++) {
            if (candidate == nums.get(i)) {
                count++;
            }
        }
        if (count > nums.size() / 2) {
            return candidate;
        }
        return -1;
    }
}
