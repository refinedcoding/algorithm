package com.LT0520.RandomSwap_GlobalIndex_TreeMapCeiling;

// lintcode-520-consistent-hash-ii
// Q: https://www.lintcode.com/problem/520/

import java.util.*;

// PreAllocate Random Swap + GlobalIndex + TreeMap Ceiling
// Time: O(N)
// Space: O(N)
// Rank: 90.25%
public class Solution {

    int[] vnodes = null;
    int k = 0, index = 0;
    TreeMap<Integer, Integer> ring = new TreeMap<>();

    // n virtual nodes (2^64), k nodes per machine
    public static Solution create(int n, int k) {
        return new Solution(n, k);
    }

    public Solution(int n, int k) {
        this.k = k;
        vnodes = new int[n];
        Random rand = new Random();
        for (int i = 1; i < n; i ++) {
            vnodes[i] = i;
            int r = rand.nextInt(i);
            vnodes[r] ^= vnodes[i] ^ (vnodes[i] = vnodes[r]);
        }
        // System.out.println(Arrays.toString(vnodes));
    }

    // return virtual nodes
    public List<Integer> addMachine(int machine_id) {
        List<Integer> ans = new ArrayList<>();
        for (int n = vnodes.length, i = 0; i < k; i ++, index = ++ index % n) {
            ring.put(vnodes[index], machine_id);
            ans.add(vnodes[index]);
        }
        return ans;
    }

    // hashcode between [0, n)
    public int getMachineIdByHashCode(int hashcode) {
        Integer ceiling = ring.ceilingKey(hashcode);
        return ceiling == null ? ring.firstEntry().getValue() : ring.get(ceiling);
    }

    public static void main(String[] args) {
        Solution s = new Solution(1<<7, 10);
        System.out.println("0: " + s.addMachine(0));
        System.out.println("1: " + s.addMachine(1));
        System.out.println("2: " + s.addMachine(2));
        System.out.println("3: " + s.addMachine(3));
        System.out.println("4: " + s.addMachine(4));
        System.out.println("50: " + s.getMachineIdByHashCode(50));
        System.out.println("100: " + s.getMachineIdByHashCode(100));
    }
}
