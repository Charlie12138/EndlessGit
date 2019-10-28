package leetcode.dfs;

/**
 * @author clear
 * @since 2019/10/25
 */
public class MaxDepth104 {
	public int maxDepth(TreeNode root) {
		int depth = 0;
		int leftDepth = 0;
		int rightDepth = 0;
		if (root == null) {
			return depth;
		}
		if (root.getLeft() != null) {
			leftDepth = maxDepth(root.getLeft());
		}
		if (root.getRight() != null) {
			rightDepth = maxDepth(root.getRight());
		}
		depth += 1 + (leftDepth > rightDepth ? leftDepth : rightDepth);
		return depth;
	}

	public static void main(String[] args) {
		/*
		 *     3
		 *    / \
		 *   9  20
		 *     /  \
		 *    15   7
		 */
		TreeNode p1 = new TreeNode(3);
		TreeNode p21 = new TreeNode(9);
		TreeNode p22 = new TreeNode(20);
		TreeNode p31 = new TreeNode(15);
		TreeNode p32 = new TreeNode(7);
		p1.setLeft(p21);
		p1.setRight(p22);
		p22.setLeft(p31);
		p22.setRight(p32);

		MaxDepth104 maxDepth104 = new MaxDepth104();
		System.out.println(maxDepth104.maxDepth(p21));
	}
}
