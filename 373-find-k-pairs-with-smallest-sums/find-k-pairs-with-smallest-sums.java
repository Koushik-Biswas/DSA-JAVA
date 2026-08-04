import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return ans;

        // min-heap by pair sum: [i, j]
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(
                (long) nums1[a[0]] + nums2[a[1]],
                (long) nums1[b[0]] + nums2[b[1]]
            )
        );

        // Start with (i, 0) for first min(k, nums1.length) rows
        int rows = Math.min(k, nums1.length);
        for (int i = 0; i < rows; i++) {
            pq.offer(new int[]{i, 0});
        }

        while (k-- > 0 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            int i = cur[0], j = cur[1];

            ans.add(Arrays.asList(nums1[i], nums2[j]));

            // Next in same row: (i, j+1)
            if (j + 1 < nums2.length) {
                pq.offer(new int[]{i, j + 1});
            }
        }

        return ans;
    }
}