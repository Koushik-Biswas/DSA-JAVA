class Trie {
    private static class Node {
        Node[] next = new Node[26];
        boolean isWord;
    }

    private final Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node cur = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (cur.next[idx] == null) cur.next[idx] = new Node();
            cur = cur.next[idx];
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        Node node = traverse(word);
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix) {
        return traverse(prefix) != null;
    }

    private Node traverse(String s) {
        Node cur = root;
        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            if (cur.next[idx] == null) return null;
            cur = cur.next[idx];
        }
        return cur;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */