package leetcode.dfsbfs;

public class BanlaceBinaryTree110 {

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

	public boolean isBalanced(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		int leftDepth = maxDepth(left);
		int rightDepth = maxDepth(right);
		if (Math.abs(leftDepth - rightDepth) >= 2) {
			return false;
		}
		if (left == null) {
			return isBalanced(right.getLeft(), right.getRight());
		}
		if (right == null) {
			return isBalanced(left.getLeft(), left.getRight());
		}
		return isBalanced(left.getLeft(), left.getRight()) && isBalanced(right.getLeft(), right.getRight());
	}

	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		return isBalanced(root.getLeft(), root.getRight());
	}

	public static void main(String[] args) {
		TreeNode node = new TreeNode(1);
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(1);
		TreeNode node3 = new TreeNode(1);
		TreeNode node4 = new TreeNode(1);
		TreeNode node5 = new TreeNode(1);
		TreeNode node6 = new TreeNode(1);

		node.setLeft(node1);
		node.setRight(node2);
		node1.setLeft(node2);
		node2.setLeft(node3);
//		node1.setLeft(node3);
//		node1.setRight(node4);
//		node3.setLeft(node5);
//		node3.setRight(node6);

		BanlaceBinaryTree110 banlaceBinaryTree = new BanlaceBinaryTree110();
		System.out.println(banlaceBinaryTree.isBalanced(node));
	}
}
