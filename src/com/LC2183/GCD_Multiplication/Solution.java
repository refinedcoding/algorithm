package com.LC2183.GCD_Multiplication;

import java.util.*;

public class Solution {

    /* Hints:
    遍历nums里的每个v, 计算g=GCD(v, k)
    假设v=g*h, 可以忽略h, 然后更新count[g]
    两层遍历count所有键，g和h
    如果g==h, ans+=count[g]*(count[g]-1)/2
    如果g<h, ans+=count[g]*count[h]
    */
    /* Steps:            nums = [1,2,3,4,5], k = 2
                          gcd = [1,2,1,2,1]
                        count = { 1=3, 2=2}
                          ans = 2 * 3 + 2 * (2 - 1) = 7

                         nums = [4, 8, 9, 10, 21], k = 6
                         10 * 21 / 6 = 0
                         2 * 3 / 6 = 0
                          gcd = [2, 2, 3, 2, 3]
                          count = { 2: 3, 3: 2 }
                          g = 2, h = 2, ans += 2 * 1
                          g = 2, h = 3, ans += 6
                          g = 3, h = 2, ans += 6
                          g = 3, h = 3, ans + 6
                          ans /= 2 -> 10
     */
    public long countPairs(int[] nums, int k) {
        Map<Long, Integer> count = new HashMap<>();
        for (int v : nums) {
            long gcd = GCD(v, k);
            count.put(gcd, count.getOrDefault(gcd, 0) + 1);
        }

        long ans = 0;
        for (long g : count.keySet()) {
            for (long h : count.keySet()) {
                if (g * h % k == 0) {
                    long a = count.get(g), b = count.get(h);
                    ans += g == h ? a * (a - 1) : a * b;
                }
            }
        }
        return ans / 2;
    }

    long GCD(long a, long b) {
        return a == 0 ? b : GCD(b % a, a);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countPairs(new int[]{1, 2, 3, 4, 5}, 2));
    }

}