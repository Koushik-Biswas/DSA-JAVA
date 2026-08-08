class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        // dp[i] stores the length of the longest suffix of word2 
        // that exists as a subsequence in word1[i...n-1]
        int[] dp = new int[n + 1];
        
        for (int i = n - 1; i >= 0; i--) {
            // Check if the current character in word1 matches the needed character in word2's suffix
            if (dp[i + 1] < m && word1.charAt(i) == word2.charAt(m - 1 - dp[i + 1])) {
                dp[i] = dp[i + 1] + 1;
            } else {
                dp[i] = dp[i + 1];
            }
        }
        
        int[] ans = new int[m];
        int j = 0;
        boolean skipped = false;
        
        // Greedily pick indices
        for (int i = 0; i < n && j < m; i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                // Exact Match
                ans[j++] = i;
            } else if (!skipped && dp[i + 1] >= m - 1 - j) {
                // Mismatch, but we can exactly match the rest of word2 using the remaining word1
                ans[j++] = i;
                skipped = true; // Our 1 allowed mismatch is now used up
            }
        }
        
        // If we successfully mapped all 'm' characters
        if (j == m) {
            return ans;
        }
        
        // No valid sequence exists
        return new int[0];
    }
}