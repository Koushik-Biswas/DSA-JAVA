import java.util.*;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        
        while (top <= bottom && left <= right) {
            // left -> right
            for (int c = left; c <= right; c++) {
                res.add(matrix[top][c]);
            }
            top++;
            
            // top -> bottom
            for (int r = top; r <= bottom; r++) {
                res.add(matrix[r][right]);
            }
            right--;
            
            // right -> left (if row still exists)
            if (top <= bottom) {
                for (int c = right; c >= left; c--) {
                    res.add(matrix[bottom][c]);
                }
                bottom--;
            }
            
            // bottom -> top (if column still exists)
            if (left <= right) {
                for (int r = bottom; r >= top; r--) {
                    res.add(matrix[r][left]);
                }
                left++;
            }
        }
        
        return res;
    }
}