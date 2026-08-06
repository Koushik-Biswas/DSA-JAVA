class Solution {
    public int smallestNumber(int n, int t) {
        int x = n;
        while (true) {
            if (digitProduct(x) % t == 0) return x;
            x++;
        }
    }

    private int digitProduct(int num) {
        int product = 1;
        while (num > 0) {
            product *= (num % 10);
            num /= 10;
        }
        return product;
    }
}