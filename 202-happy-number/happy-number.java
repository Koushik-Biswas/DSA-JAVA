class Solution {
    public boolean isHappy(int n) {
        int slow = n, fast = n;

        do {
            slow = next(slow);           // move 1 step
            fast = next(next(fast));     // move 2 steps
        } while (slow != fast);

        return slow == 1;
    }

    private int next(int x) {
        int sum = 0;
        while (x > 0) {
            int d = x % 10;
            sum += d * d;
            x /= 10;
        }
        return sum;
    }
}