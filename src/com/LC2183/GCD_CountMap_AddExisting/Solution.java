package com.LC2183.GCD_CountMap_AddExisting;

import java.util.*;

// 2183-count-array-pairs-divisible-by-k
// Q: https://leetcode.com/problems/count-array-pairs-divisible-by-k/

class Solution {

    // 2183: GCD + CountMap + AddExisting
    /* Hints:
     遍历nums里的每个数v
     计算当前g=GCD(v, k), 假设v=g*h, 可以忽略h,因为h对整除k没有帮助
     检查之前所有gi*g是否能被k整除
     如果整除,答案就加gi之前出现的次数
     最后更新当前g出现的次数
    */
    /*
    Input:
    [8,10,2,5,9,6,3,8,2]
    6
    Output:
    8
    Expected:
    18

    v = 8, f = 2, factors = [2], count = {2=1}, ans = 0
    v = 10, f = 2, factors = [2], count = {2=2}, ans = 0
    v = 2, f = 2, factors = [2], count = {2=3}, ans = 0
    v = 5, f = 1, factors = [2, 1], count = {1=1, 2=3}, ans = 0
    v = 9, f = 3, factors = [2, 1, 3], count = {1=1, 2=3, 3=1}, ans = 3
    v = 6, f = 6, factors = [2, 1, 3, 6], count = {1=1, 2=3, 3=1, 6=1}, ans = 8
    v = 3, f = 3, factors = [2, 1, 3, 6], count = {1=1, 2=3, 3=2, 6=1}, ans = 12
    v = 8, f = 2, factors = [2, 1, 3, 6], count = {1=1, 2=4, 3=2, 6=1}, ans = 15
    v = 2, f = 2, factors = [2, 1, 3, 6], count = {1=1, 2=5, 3=2, 6=1}, ans = 18

    */
    public long countPairs(int[] nums, int k) {
        long ans = 0;
        Map<Long, Integer> count = new HashMap<>();
        for (int v : nums) {
            long f = GCD(v, k);
            for (long d : count.keySet()) {
                if (d * f % k == 0) {
                    ans += count.get(d);
                }
            }
            count.put(f, count.getOrDefault(f, 0) + 1);
            // System.out.printf("v = %d, f = %d, factors = %s, count = %s, ans = %d\n", v, f, factors, count, ans);
        }
        return ans;
    }

    public long countPairs11(int[] nums, int k) {
        long ans = 0;
        List<Long> factors = new ArrayList<>();
        Map<Long, Integer> count = new HashMap<>();
        for (int v : nums) {
            long f = GCD(v, k);
            for (long d : factors) {
                if (d * f % k == 0) {
                    ans += count.get(d);
                }
            }
            if (!count.containsKey(f)) {
                factors.add(f);
            }
            count.put(f, count.getOrDefault(f, 0) + 1);
            // System.out.printf("v = %d, f = %d, factors = %s, count = %s, ans = %d\n", v, f, factors, count, ans);
        }
        return ans;
    }

    public long countPairs21(int[] nums, int k) {
        long ans = 0;
        List<Integer> factors = new ArrayList<>();
        int[] count = new int[100_001];
        for (int v : nums) {
            int f = GCD(v, k);
            for (int d : factors) {
                if (1L * d * f % k == 0) {
                    ans += count[d];
                }
            }
            if (count[f] == 0) {
                factors.add(f);
            }
            count[f] ++;
        }
        return ans;
    }

    int GCD(int a, int b) {
        return a == 0 ? b : GCD(b % a, a);
    }

    public static void main(String[] args) {
        System.out.println(new com.LC2183.GCD_Multiplication.Solution().countPairs(new int[]{1, 2, 3, 4, 5}, 2));
    }
}
