import java.util.*;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);

        int best = 0;

        for (int n : set) {
            // Start counting only if n is the start of a sequence
            if (!set.contains(n - 1)) {
                int curr = n;
                int len = 1;

                while (set.contains(curr + 1)) {
                    curr++;
                    len++;
                }

                best = Math.max(best, len);
            }
        }

        return best;
    }
}