class WordDictionary {
    private static class Node {
        Node[] next = new Node[26];
        boolean isWord;
    }

    private final Node root;

    public WordDictionary() {
        root = new Node();
    }
    
    public void addWord(String word) {
        Node cur = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (cur.next[idx] == null) cur.next[idx] = new Node();
            cur = cur.next[idx];
        }
        cur.isWord = true;
    }
    
    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int i, Node node) {
        if (node == null) return false;
        if (i == word.length()) return node.isWord;

        char ch = word.charAt(i);
        if (ch == '.') {
            for (Node child : node.next) {
                if (child != null && dfs(word, i + 1, child)) return true;
            }
            return false;
        } else {
            return dfs(word, i + 1, node.next[ch - 'a']);
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */