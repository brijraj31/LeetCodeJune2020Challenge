// Problem Link : https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/539/week-1-june-1st-june-7th/3349/

// Two City Scheduling

/* There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].

Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.

 

Example 1:

Input: [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation: 
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 

Note:

1 <= costs.length <= 100
It is guaranteed that costs.length is even.
1 <= costs[i][0], costs[i][1] <= 1000 */


#include <iostream>
#include <bits/stdc++.h>

using namespace std;


class Solution {
    int twoCitySchedCostRec(vector<vector<int>>& costs, int n, int a, int b) {
        if (a == 0 && b == 0 && n == 0) return 0;

        if (a < 0 || b < 0) return INT_MAX;

        int temp1 = twoCitySchedCostRec(costs, n - 1, a - 1, b);
        int temp2 = twoCitySchedCostRec(costs, n - 1, a, b - 1);

        temp1 = (temp1 == INT_MAX ? INT_MAX : temp1 + costs[n - 1][0]);
        temp2 = (temp2 == INT_MAX ? INT_MAX : temp2 + costs[n - 1][1]);
        return min(temp1, temp2);
    }
    int twoCitySchedCostBottomUp(vector<vector<int>>& costs, vector<vector<int>>& dp, int n, int a, int b) {
        if (a == 0 && b == 0 && n == 0) {
            return dp[a][b] = 0;
        }
        if (a < 0 || b < 0) return INT_MAX;

        if (dp[a][b] != -1) return dp[a][b];

        int temp1 = twoCitySchedCostBottomUp(costs, dp, n - 1, a - 1, b);
        int temp2 = twoCitySchedCostBottomUp(costs, dp, n - 1, a, b - 1);

        temp1 = (temp1 == INT_MAX ? temp1 : temp1 + costs[n - 1][0]);
        temp2 = (temp2 == INT_MAX ? temp2 : temp2 + costs[n - 1][1]);

        return dp[a][b] = min(temp1, temp2);
    }
    int twoCitySchedCostBottomUp(vector<vector<int>>& costs, int n, int a, int b) {
        vector<vector<int>> dp;

        for (int i = 0; i <= a; ++i) {
            vector<int> temp;
            for (int j = 0; j <= b; ++j) {
                temp.push_back(-1);
            }
            dp.push_back(temp);
        }

        twoCitySchedCostBottomUp(costs, dp, n, a, b);
        return dp[a][b];
    }

    int twoCitySchedCostTopDown(vector<vector<int>>& costs) {
        vector<vector<int>> dp;

        int n = costs.size();
        int a = n >> 1;
        int b = a;
        for (int i = 0; i <= a; ++i) {
            vector<int> temp;
            for (int j = 0; j <= b; ++j) {
                temp.push_back(0);
            }
            dp.push_back(temp);
        }
        

        for (int i = 1; i <= a; ++i) {
            dp[0][i] = dp[0][i - 1] + costs[i - 1][1];
        }
        for (int i = 1; i <= b; ++i) {
            dp[i][0] = dp[i - 1][0] + costs[i - 1][0];
        }

        for (int i = 1; i <= a; ++i) {
            for (int j = 1; j <= b; ++j) {
                dp[i][j] = min(dp[i - 1][j] + costs[i - 1 + j][0], dp[i][j - 1] + costs[i + j - 1][1]);
            }
        }

        return dp[a][b];
    }
public:
    int twoCitySchedCost(vector<vector<int>>& costs) {
        // return twoCitySchedCostRec(costs, costs.size(), costs.size() >> 1, costs.size() >> 1);
        // return twoCitySchedCostBottomUp(costs, costs.size(), costs.size() >> 1, costs.size() >> 1);

        return twoCitySchedCostTopDown(costs);
    }
};



/* 
6
259 770
448 54
926 667
184 139
840 118
577 469

 */

int main() {
    Solution s;
    vector<vector<int>> cities;

    int n, a, b;
    cin >> n;

    for (int i = 0; i < n; ++i) {
        cin >> a >> b;

        vector<int> temp;
        temp.push_back(a);
        temp.push_back(b);

        cities.push_back(temp);
    }

    cout << s.twoCitySchedCost(cities);
    return 0;
}