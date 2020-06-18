// Problem Link : https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3359/



// Largest Divisible Subset

/* Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:

Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

Input: [1,2,3]
Output: [1,2] (of course, [1,3] will also be ok)
Example 2:

Input: [1,2,4,8]
Output: [1,2,4,8] */

package Day13_LargestDivisibleSubset;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        
        List<Integer> ans = new ArrayList<Integer>();
        
        if (n == 0) return ans;
        
        Arrays.sort(nums);
        
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        int[] prev_ind = new int[n];
        Arrays.fill(prev_ind, -1);
        
        int max_ind = 0;
        
        
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev_ind[i] = j;
                }
            }
            if (dp[i] > dp[max_ind]) max_ind = i;
        }
        
        while(max_ind >= 0) {
            ans.add(nums[max_ind]);
            max_ind = prev_ind[max_ind];
        }
        
        return ans;
    }
}