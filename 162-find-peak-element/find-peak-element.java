class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // If the right neighbor is higher, a peak must exist to the right
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else { // Otherwise, a peak exists at mid or to the left
                right = mid;
            }
        }
        
        return left;
    }
}