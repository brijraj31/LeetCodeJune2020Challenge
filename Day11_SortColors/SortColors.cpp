// Problem Link : https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3357/


// Sort Colors

/* Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
 */



class Solution {
    public:
        void sortColors(vector<int>& nums) {
            
            int len = nums.size();
            if (len == 0 || len == 1) return;
            int start = 0, end = len - 1, index = 0;
            
            while (index <= end && start < end) {
                if (nums[index] == 0) {
                    nums[index] = nums[start];
                    nums[start] = 0;
                    start ++;
                    index ++;
                    
                } else if (nums[index] == 2) {
                    nums[index] = nums[end];
                    nums[end] = 2;
                    end --;
                    
                } else index ++;
                
            }
        }
    };