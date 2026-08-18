import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int largestInteger(int[] nums, int k) {
        Map<Integer, Integer> subarrayCount = new HashMap<>();

        // Iterate through all subarrays of size k
        for (int i = 0; i <= nums.length - k; i++) {
            Set<Integer> uniqueInSubarray = new HashSet<>();
            
            // Collect unique elements in the current subarray
            for (int j = i; j < i + k; j++) {
                uniqueInSubarray.add(nums[j]);
            }
            
            // Increment the subarray appearance count for each unique number
            for (int num : uniqueInSubarray) {
                subarrayCount.put(num, subarrayCount.getOrDefault(num, 0) + 1);
            }
        }

        // Find the maximum number that appears in exactly 1 subarray
        int maxAlmostMissing = -1;
        for (Map.Entry<Integer, Integer> entry : subarrayCount.entrySet()) {
            if (entry.getValue() == 1) {
                maxAlmostMissing = Math.max(maxAlmostMissing, entry.getKey());
            }
        }

        return maxAlmostMissing;
    }
}