package Day3;


import java.util.Set;
import java.util.TreeSet;





class Solution {
    // Pair class
    private class Pair<F, S> {
        private final F first; //first member of pair
        private final S second; //second member of pair

        Pair(final F first, final S second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass())
                return false;

            final Pair<?, ?> pair = (Pair<?, ?>) o;
            if (!first.equals(pair.first))
                return false;
            return second.equals(pair.second);
        }

        @Override
        public int hashCode() {
            return 31 * first.hashCode() + second.hashCode();
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }

        public F getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }
    }

    public int twoCitySchedCost(final int[][] costs) {
        final Set<Pair<Integer, Integer>> set = new TreeSet<Pair<Integer, Integer>>(
                (final Pair<Integer, Integer> p1, final Pair<Integer, Integer> p2) -> {
                    final int diff = Integer.compare(p1.getFirst(), p2.getFirst());
                    if (diff == 0) {
                        return Integer.compare(p2.getSecond(), p1.getSecond());
                    }
                    return diff;
                });

        for (final int[] x : costs) {
            set.add(new Pair<Integer, Integer>(x[0], x[1]));
        }
        int cost = 0;
        int count = 0;
        for (final Pair<Integer, Integer> p : set) {
            if (count < (costs.length >> 1))
                cost += p.getFirst();
            else
                cost += p.getSecond();
            count++;
        }
        return cost;
    }
}

class TowCityScheduling {
    public static void main(final String[] args) {
        final Solution s = new Solution();
        final int[][] cities = {{259,770},{448,54},{926,667},{184,139},{840,118},{577,469}};

        System.out.println(s.twoCitySchedCost(cities));
        

    }
}
