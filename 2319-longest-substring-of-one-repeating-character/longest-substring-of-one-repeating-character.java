class Solution {
    static class SegmentTree {
        int n;
        int[] maxLen;
        int[] prefixLen;
        int[] suffixLen;
        char[] leftChar;
        char[] rightChar;

        public SegmentTree(String s) {
            this.n = s.length();
            int size = 4 * n;
            maxLen = new int[size];
            prefixLen = new int[size];
            suffixLen = new int[size];
            leftChar = new char[size];
            rightChar = new char[size];
            build(1, 0, n - 1, s);
        }

        private void merge(int node, int leftChild, int rightChild, int lLen, int rLen) {
            leftChar[node] = leftChar[leftChild];
            rightChar[node] = rightChar[rightChild];

            // Calculate prefix length
            prefixLen[node] = prefixLen[leftChild];
            if (prefixLen[leftChild] == lLen && rightChar[leftChild] == leftChar[rightChild]) {
                prefixLen[node] = lLen + prefixLen[rightChild];
            }

            // Calculate suffix length
            suffixLen[node] = suffixLen[rightChild];
            if (suffixLen[rightChild] == rLen && rightChar[leftChild] == leftChar[rightChild]) {
                suffixLen[node] = rLen + suffixLen[leftChild];
            }

            // Calculate max length
            maxLen[node] = Math.max(maxLen[leftChild], maxLen[rightChild]);
            if (rightChar[leftChild] == leftChar[rightChild]) {
                maxLen[node] = Math.max(maxLen[node], suffixLen[leftChild] + prefixLen[rightChild]);
            }
        }

        private void build(int node, int l, int r, String s) {
            if (l == r) {
                char ch = s.charAt(l);
                leftChar[node] = ch;
                rightChar[node] = ch;
                prefixLen[node] = 1;
                suffixLen[node] = 1;
                maxLen[node] = 1;
                return;
            }
            int mid = l + (r - l) / 2;
            int leftChild = 2 * node;
            int rightChild = 2 * node + 1;

            build(leftChild, l, mid, s);
            build(rightChild, mid + 1, r, s);

            merge(node, leftChild, rightChild, mid - l + 1, r - mid);
        }

        public void update(int node, int l, int r, int idx, char ch) {
            if (l == r) {
                leftChar[node] = ch;
                rightChar[node] = ch;
                prefixLen[node] = 1;
                suffixLen[node] = 1;
                maxLen[node] = 1;
                return;
            }
            int mid = l + (r - l) / 2;
            int leftChild = 2 * node;
            int rightChild = 2 * node + 1;

            if (idx <= mid) {
                update(leftChild, l, mid, idx, ch);
            } else {
                update(rightChild, mid + 1, r, idx, ch);
            }

            merge(node, leftChild, rightChild, mid - l + 1, r - mid);
        }

        public int getMaxLen() {
            return maxLen[1];
        }
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int k = queryIndices.length;
        int[] ans = new int[k];
        SegmentTree tree = new SegmentTree(s);

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            tree.update(1, 0, tree.n - 1, idx, ch);
            ans[i] = tree.getMaxLen();
        }

        return ans;
    }
}