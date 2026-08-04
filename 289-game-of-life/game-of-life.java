class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;

        // Directions for 8 neighbors
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        // 1st pass: mark transitions using temporary states
        // 0 -> dead stays dead
        // 1 -> live stays live
        // 2 -> live -> dead
        // 3 -> dead -> live
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int liveNeighbors = 0;

                for (int k = 0; k < 8; k++) {
                    int nr = r + dr[k], nc = c + dc[k];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                        // original live cells are 1 or 2
                        if (board[nr][nc] == 1 || board[nr][nc] == 2) {
                            liveNeighbors++;
                        }
                    }
                }

                if (board[r][c] == 1) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        board[r][c] = 2; // live -> dead
                    }
                } else { // board[r][c] == 0
                    if (liveNeighbors == 3) {
                        board[r][c] = 3; // dead -> live
                    }
                }
            }
        }

        // 2nd pass: finalize states
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == 2) board[r][c] = 0;
                else if (board[r][c] == 3) board[r][c] = 1;
            }
        }
    }
}