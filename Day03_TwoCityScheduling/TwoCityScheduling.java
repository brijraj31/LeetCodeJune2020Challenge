package Day03_TwoCityScheduling;

import java.util.Arrays;
import java.util.Comparator;



class Solution {
    
    private int twoCitySchedCostRecHelper(final int[][] costs, final int a, final int b) {
        if (a == 0 && b == 0)
            return 0;

        if (a < 0 || b < 0)
            return Integer.MAX_VALUE;

        int temp1 = twoCitySchedCostRecHelper(costs, a - 1, b);
        int temp2 = twoCitySchedCostRecHelper(costs, a, b - 1);

        temp1 = (temp1 == Integer.MAX_VALUE ? temp1 : temp1 + costs[a - 1 + b][0]);
        temp2 = (temp2 == Integer.MAX_VALUE ? temp2 : temp2 + costs[a + b - 1][1]);
        return Integer.min(temp1, temp2);
    }

    private int twoCitySchedCostRec(final int[][] costs) {
        final int n = costs.length;
        final int a = n >> 1;
        final int b = a;
        return twoCitySchedCostRecHelper(costs, a, b);
    }

    int twoCitySchedCostBottomUpHelper(final int[][] costs, final int[][] dp, final int a, final int b) {
        if (a == 0 && b == 0) {
            return dp[a][b] = 0;
        }
        if (a < 0 || b < 0)
            return Integer.MAX_VALUE;

        if (dp[a][b] != -1)
            return dp[a][b];

        int temp1 = twoCitySchedCostBottomUpHelper(costs, dp, a - 1, b);
        int temp2 = twoCitySchedCostBottomUpHelper(costs, dp, a, b - 1);

        temp1 = (temp1 == Integer.MAX_VALUE ? temp1 : temp1 + costs[a - 1 + b][0]);
        temp2 = (temp2 == Integer.MAX_VALUE ? temp2 : temp2 + costs[a + b - 1][1]);

        return dp[a][b] = Integer.min(temp1, temp2);
    }

    private int twoCitySchedCostBottomUp(final int[][] costs) {
        final int n = costs.length;
        final int a = n >> 1;
        final int b = a;
        final int[][] dp = new int[a + 1][b + 1];

        for (final int[] x : dp) {
            Arrays.fill(x, -1);
        }
        twoCitySchedCostBottomUpHelper(costs, dp, a, b);
        return dp[a][b];
    }
    
    private int twoCitySchedCostTopDown(final int[][] costs) {
        final int n = costs.length;
        final int a = n >> 1;
        final int b = a;
        final int[][] dp = new int[a + 1][b + 1];

        for (int i = 1; i <= a; ++i) {
            dp[0][i] = dp[0][i - 1] + costs[i - 1][1];
        }
        for (int i = 1; i <= b; ++i) {
            dp[i][0] = dp[i - 1][0] + costs[i - 1][0];
        }

        for (int i = 1; i <= a; ++i) {
            for (int j = 1; j <= b; ++j) {
                dp[i][j] = Integer.min(dp[i - 1][j] + costs[i - 1 + j][0], dp[i][j - 1] + costs[i + j - 1][1]);
            }
        }

        return dp[a][b];
    }

    private int twoCitySchedCostSortingSolution(int[][] costs) {
        int c = 0;

        Arrays.sort(costs, Comparator.comparingInt(o -> o[0] - o[1]));

        for (int i = 0, j = costs.length - 1; i < j; ++i, --j) {
            c += costs[i][0];
            c += costs[j][1];
        }

        return c;
    }
    public int twoCitySchedCost(final int[][] costs) {
        // return twoCitySchedCostRec(costs);
        // return twoCitySchedCostBottomUp(costs);
        return twoCitySchedCostTopDown(costs);

    }
}

class TowCityScheduling {
    public static void main(final String[] args) {
        
        final int[][] cities = {{259,770},{448,54},{926,667},{184,139},{840,118},{577,469}};

        System.out.println(new Solution().twoCitySchedCost(cities));
    }
}
