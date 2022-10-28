package com.LC2344.GCD_Sort_Divisible;

import java.util.*;

// 2344-minimum-deletions-to-make-array-divisible
// Q: https://leetcode.com/problems/minimum-deletions-to-make-array-divisible/

class Solution {

    // 2344 - GCD + Sort + Divisible
    // Time: O(N*LogN)
    // Space: O(1)
    // Rank: 52.45%
    // Question: 从数组A中删除最少的元素，使得最小的元素能整除数组B中所有的元素
    /* Hints:
    先求出数组B的GCD
    然后对数组A排序
    遍历数组A，找出能整除GCD的最小元素
    */
    public int minOperations(int[] nums, int[] numsDivide) {
        int n = nums.length, f = 0;
        for (int v : numsDivide) {
            f = gcd(f, v);
        }

        Arrays.sort(nums);
        // System.out.println(f + " " + Arrays.toString(nums));
        for (int i = 0; i < n; i ++) {
            if (f % nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    int gcd(int a, int b) {
        return a == 0 ? b : gcd(b%a, a);
    }

    public int minOperations11(int[] nums, int[] numsDivide) {
        int n = nums.length, m = numsDivide.length, g = numsDivide[0];
        for (int v : numsDivide) {
            g = GCD(g, v);
        }

        int ans = 0;
        Arrays.sort(nums);
        // System.out.println(g + " " + Arrays.toString(nums));
        for (int i = 0; i < n && nums[i] <= g; i ++) {
            if ( g % nums[i] == 0) {
                return ans;
            }
            ans ++;
        }
        return -1;
    }

    int GCD(int a, int b) {
        return a == 0 ? b : GCD(b % a, a);
    }
}

/*
[2,3,2,4,3]
[9,6,9,3,15]
[4,3,6]
[8,2,6,10]
[3,2,6,2,35,5,35,2,5,8,7,3,4]
[105,70,70,175,105,105,105]
*/