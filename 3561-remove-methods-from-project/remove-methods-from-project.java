import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        for (int[] e : invocations) g.get(e[0]).add(e[1]);

        // 1) BFS from k to collect suspicious methods
        boolean[] inS = new boolean[n];
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        inS[k] = true;
        dq.add(k);
        while (!dq.isEmpty()) {
            int u = dq.poll();
            for (int v : g.get(u)) {
                if (!inS[v]) {
                    inS[v] = true;
                    dq.add(v);
                }
            }
        }

        // 2) If any outside method invokes a suspicious one, remove nothing
        for (int[] e : invocations) {
            if (!inS[e[0]] && inS[e[1]]) {
                List<Integer> all = new ArrayList<>(n);
                for (int i = 0; i < n; i++) all.add(i);
                return all;
            }
        }

        // 3) Otherwise return the non-suspicious methods
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) if (!inS[i]) res.add(i);
        return res;
    }
}