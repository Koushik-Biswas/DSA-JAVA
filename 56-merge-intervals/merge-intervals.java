import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int start = intervals[0][0], end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int s = intervals[i][0], e = intervals[i][1];

            if (s <= end) { // overlap (including touching like [1,4] and [4,5])
                end = Math.max(end, e);
            } else {
                merged.add(new int[]{start, end});
                start = s;
                end = e;
            }
        }

        merged.add(new int[]{start, end});
        return merged.toArray(new int[merged.size()][]);
    }
}