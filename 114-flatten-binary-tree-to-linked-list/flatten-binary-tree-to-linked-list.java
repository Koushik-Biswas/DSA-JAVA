/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void flatten(TreeNode root) {
        TreeNode curr = root;

        while (curr != null) {

            if (curr.left != null) {

                // Find the rightmost node of left subtree
                TreeNode temp = curr.left;

                while (temp.right != null) {
                    temp = temp.right;
                }

                // Attach original right subtree
                temp.right = curr.right;

                // Move left subtree to right
                curr.right = curr.left;

                // Remove left pointer
                curr.left = null;
            }

            // Move to next node
            curr = curr.right;
        }
    }
}