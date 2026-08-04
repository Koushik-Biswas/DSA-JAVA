class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> missing = new ArrayList<>();
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        // Find range boundaries
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        // Put all existing numbers in a set for O(1) lookup
        Set<Integer> present = new HashSet<>();
        for (int num : nums) {
            present.add(num);
        }
        
        // Check every number in [min, max]
        for (int i = min; i <= max; i++) {
            if (!present.contains(i)) {
                missing.add(i);
            }
        }
        
        return missing;
    }
}