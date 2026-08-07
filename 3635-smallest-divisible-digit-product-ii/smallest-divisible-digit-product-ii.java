class Solution {
    private static final int[] PR = {2, 3, 5, 7};

    private static long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    // exact minimum number of digits (1-9) whose product is divisible by r
    private int minDigits(long r) {
        int a = 0, b = 0, c = 0, d = 0;
        while (r % 2 == 0) { r /= 2; a++; }
        while (r % 3 == 0) { r /= 3; b++; }
        while (r % 5 == 0) { r /= 5; c++; }
        while (r % 7 == 0) { r /= 7; d++; }

        int best = Integer.MAX_VALUE;
        int lim = Math.min(a, b);
        for (int z = 0; z <= lim; z++) {          // z = how many 6's we use
            int cost = z + (a - z + 2) / 3 + (b - z + 1) / 2;
            if (cost < best) best = cost;
        }
        return best + c + d;                      // 5's and 7's need own digits
    }

    // smallest length-`len` zero-free string whose digit product covers `rem`
    private String build(long rem, int len) {
        char[] res = new char[len];
        java.util.Arrays.fill(res, '1');
        int start = len - minDigits(rem);
        for (int pos = start; pos < len; pos++) {
            for (int dg = 1; dg <= 9; dg++) {
                long nr = rem / gcd(rem, dg);
                if (minDigits(nr) <= len - pos - 1) {
                    res[pos] = (char) ('0' + dg);
                    rem = nr;
                    break;
                }
            }
        }
        return new String(res);
    }

    public String smallestNumber(String num, long t) {
        long tt = t;
        for (int p : PR) {
            while (tt % p == 0) tt /= p;
        }
        if (tt > 1) return "-1";

        int n = num.length();
        long[] pre = new long[n + 1];
        pre[0] = t;
        int z = n;                                 // index of first '0'
        for (int i = 0; i < n; i++) {
            int dg = num.charAt(i) - '0';
            if (dg == 0) { z = i; break; }
            pre[i + 1] = pre[i] / gcd(pre[i], dg);
        }

        if (z == n && pre[n] == 1) return num;

        for (int i = Math.min(z, n - 1); i >= 0; i--) {
            int cur = num.charAt(i) - '0';
            int left = n - 1 - i;
            for (int dg = cur + 1; dg <= 9; dg++) {
                long nr = pre[i] / gcd(pre[i], dg);
                if (minDigits(nr) <= left) {
                    return new StringBuilder(n)
                        .append(num, 0, i)
                        .append((char) ('0' + dg))
                        .append(build(nr, left))
                        .toString();
                }
            }
        }

        return build(t, Math.max(n + 1, minDigits(t)));
    }
}