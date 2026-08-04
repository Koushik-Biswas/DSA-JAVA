class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int total = 0;

        int curMax = 0, maxSum = Integer.MIN_VALUE;
        int curMin = 0, minSum = Integer.MAX_VALUE;

        for (int x : nums) {
            // Kadane for max subarray
            curMax = Math.max(x, curMax + x);
            maxSum = Math.max(maxSum, curMax);

            // Kadane for min subarray
            curMin = Math.min(x, curMin + x);
            minSum = Math.min(minSum, curMin);

            total += x;
        }

        // If all numbers are negative, total == minSum,
        // and wrapping would incorrectly give 0 (empty subarray), so return maxSum.
        if (maxSum < 0) return maxSum;

        // Best of non-wrapping vs wrapping
        return Math.max(maxSum, total - minSum);
    }
}