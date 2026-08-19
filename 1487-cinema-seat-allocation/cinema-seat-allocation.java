import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> reservedMap = new HashMap<>();

        // Build bitmask for each row with reserved seats in range [2, 9]
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col >= 2 && col <= 9) {
                reservedMap.put(row, reservedMap.getOrDefault(row, 0) | (1 << col));
            }
        }

        // Each completely empty row can accommodate 2 four-person groups
        int ans = (n - reservedMap.size()) * 2;

        // Bitmasks for the 3 valid block options
        int leftMask   = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);   // Seats 2, 3, 4, 5
        int middleMask = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);   // Seats 4, 5, 6, 7
        int rightMask  = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);   // Seats 6, 7, 8, 9

        // Process only rows that have reservations
        for (int mask : reservedMap.values()) {
            boolean canLeft = (mask & leftMask) == 0;
            boolean canRight = (mask & rightMask) == 0;
            boolean canMiddle = (mask & middleMask) == 0;

            if (canLeft && canRight) {
                ans += 2;
            } else if (canLeft || canRight || canMiddle) {
                ans += 1;
            }
        }

        return ans;
    }
}