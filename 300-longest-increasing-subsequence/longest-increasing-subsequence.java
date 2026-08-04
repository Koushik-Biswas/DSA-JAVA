import java.util.*;

class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;

        for (int x : nums) {
            int i = 0, j = size;
            // lower_bound on tails for x
            while (i < j) {
                int m = i + (j - i) / 2;
                if (tails[m] < x) i = m + 1;
                else j = m;
            }

            tails[i] = x;
            if (i == size) size++;
        }

        return size;
    }
}