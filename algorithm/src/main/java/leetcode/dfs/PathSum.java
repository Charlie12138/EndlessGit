package leetcode.dfs;

public class PathSum {
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		if (sum - root.getVal() == 0 && root.getLeft() == null && root.getRight() == null) {
			return true;
		}

		return hasPathSum(root.getLeft(), sum - root.getVal())
				|| hasPathSum(root.getRight(), sum - root.getVal());
	}

	public static void main(String[] args) {
		TreeNode node = new TreeNode(-2);
		TreeNode node1 = new TreeNode(-3);
		TreeNode node2 = new TreeNode(8);
		TreeNode node3 = new TreeNode(11);
		TreeNode node4 = new TreeNode(13);
		TreeNode node5 = new TreeNode(4);
		TreeNode node6 = new TreeNode(7);
		TreeNode node7 = new TreeNode(3);
		TreeNode node8 = new TreeNode(1);

		node.setLeft(node1);
//		node.setRight(node2);
//
//		node1.setLeft(node3);
//
//		node2.setLeft(node4);
//		node2.setRight(node5);
//
//		node3.setLeft(node6);
//		node3.setRight(node7);
//
//		node5.setRight(node8);

		PathSum pathSum = new PathSum();
		System.out.println(pathSum.hasPathSum(node, -5));
	}
}
