class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];

        // Base: one pile left
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        // len = current interval length
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                // If current player takes left pile, opponent gets dp[i+1][j]
                // Net gain = piles[i] - dp[i+1][j]
                int takeLeft = piles[i] - dp[i + 1][j];
                // If current player takes right pile
                int takeRight = piles[j] - dp[i][j - 1];
                dp[i][j] = Math.max(takeLeft, takeRight);
            }
        }

        // Positive difference means Alice (first player) wins
        return dp[0][n - 1] > 0;
    }
}