package com.LC0296.RowMedian_ColMedian;

import java.util.*;

// 0296-best-meet-point
// Q: https://leetcode.com/problems/best-meeting-point/

class Solution {

    // 296 - Math + Sort Rows n Columns
    // Time: O(M * N * LogM * LogN)
    // Space: O(M * N)
    // Rank: 61.54%
    // Question: 0296，二维矩阵中有若干人，找出最佳聚会地点，使得所有人的行程和最短
    /* Hints
    创建两个链表，存储所有人行坐标和列坐标
    两个维度链表排序后，取中值
    遍历所有人，计算行程和
    */
    /* Steps:                       grid = [[1,0,0,0,1],
                                            [0,0,0,0,0],
                                            [0,0,1,0,0]]
            r = [0, 0, 2]
            c = [0, 2, 4], ans = (0, 2)
                        0   1   2
                        2   0   1
            (0, 2)      x              -> 1
            (1, 2)          x          -> 2
    */
    public int minTotalDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        List<Integer> rows = new ArrayList<>(), cols = new ArrayList<>();
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        Collections.sort(rows);
        Collections.sort(cols);
        // System.out.println(rows + " " + cols);
        int mid = rows.size() / 2, x = rows.get(mid), y = cols.get(mid);
        // System.out.println(x + " " + y);
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (grid[i][j] == 1) {
                    ans += Math.abs(i - x) + Math.abs(j - y);
                }
            }
        }
        return ans;
    }

    // O(M * N)
    public int minTotalDistance11(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        List<Integer>[] cords = new List[2];
        cords[0] = new ArrayList<>();
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (grid[i][j] == 1) {
                    cords[0].add(i);
                }
            }
        }

        cords[1] = new ArrayList<>();
        for (int j = 0; j < n; j ++) {
            for (int i = 0; i < m; i ++) {
                if (grid[i][j] == 1) {
                    cords[1].add(j);
                }
            }
        }

        for (List<Integer> cord : cords) {
            for (int i = 0, j = cord.size() - 1; i < j; i ++, j --) {
                ans += cord.get(j) - cord.get(i);
            }
        }
        return ans;
    }

    // S1: brute force
    // Time: O(N^3)

    // S2:
    // Rank: 99.87%
    /* Steps: rows = [2, 0, 1], cols = [1, 0, 1, 0, 1]
        i = 0, j = 2, k = 1, rows = [1, 0, 0], total += 2, j --
        i = 0, j = 1, k = 0, rows = [1, 0, 0], total += 0, j --
        i = 0, j = 4, k = 1, rows = [0, 0, 1, 0, 0], total += 4, i ++
        i = 1, j = 4, k = 0, rows = [0, 0, 1, 0, 0], total += 0, i ++
        i = 2, j = 4, k = 0, rows = [0, 0, 1, 0, 0], total += 0, j --
        i = 2, j = 3, k = 0, rows = [0, 0, 1, 0, 0], total += 0, j --, return total = 6
    */
    public int minTotalDistance21(int[][] grid) {
        int m = grid.length, n = grid[0].length, total = 0;
        int[] rows = new int[m], cols = new int[n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                rows[i] += grid[i][j];
                cols[j] += grid[i][j];
            }
        }

        for (int[] sum : new int[][] { rows, cols }) {
            for (int i=0, j=sum.length-1; i<j; ) {
                int k = Math.min(sum[i], sum[j]);
                total += k * (j - i);
                sum[i] -= k;
                sum[j] -= k;
                if (0 == sum[i]) {
                    i ++;
                } else {
                    j --;
                }
            }
        }
        return total;
    }

    // S3
    // 92.72%
    public int minTotalDistance31(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        List<Integer> I = new ArrayList<>();
        List<Integer> J = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    I.add(i);
                }
            }
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) {
                    J.add(j);
                }
            }
        }

        return min(I) + min(J);
    }

    private int min(List<Integer> list) {
        int i = 0, j = list.size() - 1;
        int sum = 0;
        while (i < j) {
            sum += list.get(j--) - list.get(i++);
        }
        return sum;
    }

    // S4: sort
    // Rank: 35.38
    public int minTotalDistance41(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<Integer> rows = new ArrayList(), cols = new ArrayList();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (1 == grid[i][j]) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        int dist = 0;
        for (List<Integer> list : Arrays.asList(rows, cols)) {
            Collections.sort(list);
            for (int i=0, j=list.size()-1; i<j; ) {
                dist += list.get(j--) - list.get(i++);
            }
        }
        return dist;
    }
}

class Solution0ms {
    public int minTotalDistance(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] row = new int[n];
        int[] col = new int[m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    row[i] += 1;
                    col[j] += 1;
                }
            }
        }

        int minDistanceRow = minDistance(row);
        int minDistanceCol = minDistance(col);

        //System.out.print("\nValues: ");
        //System.out.print(minDistanceRow+" "+minDistanceCol);
        return minDistanceRow + minDistanceCol;
    }

    /* Steps: rows = [2, 0, 1], cols = [1, 0, 1, 0, 1], left = 0, right = 0
        i = -1, j = 3, dist += 0, left = 0, right = 1
        i = -1, j = 2, dist += 0, left = 2, right = 1
        i = 0, j = 2, dist += 1, left = 2, right = 1
        i = 0, j = 1, dist += 1, left = 2, right = 3, -> i = 0, j = 0

        i = -1, j = 5, dist += 0, left = 0, right = 1
        i = -1, j = 4, dist += 0, left = 1, right = 1
        i = 0, j = 4, dist += 1, left = 1, right = 1
        i = 0, j = 3, dist += 1, left = 1, right = 2
        i = 0, j = 2, dist += 1, left = 1, right = 2
        i = 1, j = 2, dist += 1, left = 2, righ = 2
        i = 2, j = 2 -> return dist = 6
    */
    private int minDistance(int[] r) {
        int i = -1;
        int j = r.length;
        int left = 0;
        int right = 0;

        int dist = 0;
        while(i < j) {
            if(left < right) {
                dist += left;
                left += r[++i];
            }
            else {
                dist += right;
                right += r[--j];
            }
        }
        return dist;
    }
}

// 61.61%
class SolutionSort {
    public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        List<Integer> I = new ArrayList<>(m);
        List<Integer> J = new ArrayList<>(n);

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    I.add(i);
                    J.add(j);
                }
            }
        }

        return getMin(I) + getMin(J);
    }

    private int getMin(List<Integer> list){
        int ret = 0;

        Collections.sort(list);

        int i = 0;
        int j = list.size() - 1;
        while(i < j){
            ret += list.get(j--) - list.get(i++);
        }

        return ret;
    }
}