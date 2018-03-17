package com.qunar.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 18/03/12
 * User: lvshi
 */
public class TwoNum {
    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer second = map.get(target - nums[i]);
            if (second != null) {
                return i < second ? new int[]{i, second} : new int[]{second, i};
            }
            map.put(nums[i], i);
        }
        return new int[2];
    }
}
