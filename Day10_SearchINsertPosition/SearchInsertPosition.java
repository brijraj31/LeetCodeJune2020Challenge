// Problem Link : https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3356/

// Search Insert Position


/* Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:

Input: [1,3,5,6], 5
Output: 2
Example 2:

Input: [1,3,5,6], 2
Output: 1
Example 3:

Input: [1,3,5,6], 7
Output: 4
Example 4:

Input: [1,3,5,6], 0
Output: 0 */





package Day10_SearchINsertPosition;

class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = left + ((right - left) >> 1);   
            if(nums[mid] == target) return mid;
            if (nums[mid] < target) left = mid + 1;  
            else right = mid - 1;
        }
        return left;
    }
}