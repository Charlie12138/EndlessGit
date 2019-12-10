package leetcode.tree;

public class DOBT543 {

	private int result = 1;

	public int depth(TreeNode root) {
		if (root == null) return 0;
		int L = depth(root.getLeft());
		int R = depth(root.getRight());
		result = Math.max(result, L + R + 1);
		return Math.max(L, R) + 1;
	}

	public int diameterOfBinaryTree(TreeNode root) {
		result = 0;
		depth(root);
		return result - 1;
	}

	public static void main(String[] args) {

	}
}
