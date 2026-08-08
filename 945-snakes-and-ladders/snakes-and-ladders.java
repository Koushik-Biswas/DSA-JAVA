import java.util.*;

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int target = n * n;

        boolean[] visited = new boolean[target + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        visited[1] = true;

        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                if (curr == target) return moves;

                for (int d = 1; d <= 6 && curr + d <= target; d++) {
                    int next = curr + d;
                    int[] rc = getRowCol(next, n);
                    int r = rc[0], c = rc[1];

                    if (board[r][c] != -1) {
                        next = board[r][c]; // take snake or ladder once
                    }

                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
            moves++;
        }

        return -1;
    }

    // Convert square number (1..n^2) to board row/col in Boustrophedon order
    private int[] getRowCol(int num, int n) {
        int quot = (num - 1) / n;   // row index from bottom
        int rem = (num - 1) % n;    // col offset in that row
        int row = n - 1 - quot;     // actual matrix row

        int col;
        if (quot % 2 == 0) {        // left -> right
            col = rem;
        } else {                    // right -> left
            col = n - 1 - rem;
        }
        return new int[]{row, col};
    }
}