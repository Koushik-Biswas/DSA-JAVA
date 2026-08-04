class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) return false;

        // dp[j] = using s1[0..i-1] and s2[0..j-1], can we form s3[0..i+j-1]?
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        // First row: i = 0
        for (int j = 1; j <= n; j++) {
            dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= m; i++) {
            // First column: j = 0
            dp[0] = dp[0] && s1.charAt(i - 1) == s3.charAt(i - 1);

            for (int j = 1; j <= n; j++) {
                char c = s3.charAt(i + j - 1);

                boolean takeS1 = dp[j] && s1.charAt(i - 1) == c;     // old dp[j] => previous row
                boolean takeS2 = dp[j - 1] && s2.charAt(j - 1) == c; // current row, left cell
                dp[j] = takeS1 || takeS2;
            }
        }

        return dp[n];
    }
}
