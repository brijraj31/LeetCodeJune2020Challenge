// Problem Link : https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3360/

// Cheapest Flights Within K Stops


/* There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 

Constraints:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.
 */

package Day14_CheapestFlightsWithinKStops;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;


class Solution {
    private class Pair<F, S> {
        private final F first; //first member of pair
        private final S second; //second member of pair

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass())
                return false;

            Pair<?, ?> pair = (Pair<?, ?>) o;
            if(!first.equals(pair.first))
                return false;
            return second.equals(pair.second);
        }

        @Override
        public int hashCode() {
            return 31 * first.hashCode() + second.hashCode();
        }

        @Override
        public String toString() {
            return "(" + first +", "+ second +")";
        }
        public F getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }
    }
    class PairCompare implements Comparator<Pair<Integer, Integer>> {
        @Override
        public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
            int diff = Integer.compare(p1.first, p2.first);
            if(diff == 0) {
                return Integer.compare(p1.second, p2.second);
            }
            else return diff;
        }
    }

    private class City {
        int city, distFromSrc, costFromSrc;
    
        City(int city, int distFromSrc, int cost) {
            this.city = city;
            this.distFromSrc = distFromSrc;
            this.costFromSrc = cost;
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // DFS

        if (n <= 0 || flights == null || flights.length == 0 || k < 0) return -1;

        List<List<Pair<Integer, Integer>>> graph = new ArrayList<List<Pair<Integer, Integer>>>();
        this.buildGraph(graph, n, flights);

        Queue<City> pQueue = new PriorityQueue<City>((c1, c2) -> Integer.compare(c1.costFromSrc, c2.costFromSrc));

        pQueue.offer(new City(src, 0, 0));

        while(!pQueue.isEmpty()) {
            City top = pQueue.poll();

            if (top.city == dst) return top.costFromSrc;
            if (top.distFromSrc > k) continue;

            List<Pair<Integer,Integer>> neigb = graph.get(top.city);


            for (Pair<Integer, Integer> ngb : neigb) 
                pQueue.offer(new City(ngb.getFirst(), top.distFromSrc + 1, top.costFromSrc + ngb.getSecond()));

        }

        return -1;

    }

    private void buildGraph(List<List<Pair<Integer, Integer>>> graph, int n, int[][] flights) {
        for (int i = 0; i < n; ++i) graph.add(new ArrayList<Pair<Integer, Integer>>());

        for (int[] flight : flights) {
            graph.get(flight[0]).add(new Pair<Integer, Integer>(flight[1], flight[2]));
        }
    }
}